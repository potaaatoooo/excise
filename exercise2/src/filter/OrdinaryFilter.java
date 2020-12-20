package filter;

import dao.UserDao;
import vo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.geom.FlatteningPathIterator;
import java.io.IOException;


public class OrdinaryFilter implements Filter {
    private String notFilterPath;
    private String mustFilterPath;
    private int FLAG = 0;
    private String CUSTOMER = "普通用户";
    private String MANAGER = "管理员";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        notFilterPath = filterConfig.getInitParameter("notFilterUrl");
        mustFilterPath = filterConfig.getInitParameter("mustFilterUrl");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();


        String path = request.getServletPath();
        Cookie[] cookies = request.getCookies();

        User user = null;
        if(notFilterPath.indexOf(path) == -1){
            if(cookies != null){
                for(Cookie c :cookies){
                    user = new UserDao().get(c.getValue());
                    if(user.getUserName() != null) {
                        FLAG = 1;
                        break;
                    }
                }
            }
            if(FLAG == 1){
                if(user.getRole() == null){
                    FLAG = 0;
                    this.doFilter(servletRequest,servletResponse,filterChain);
                }
                if(user.getRole().equals(MANAGER)){
                    request.getSession().setAttribute("role",user.getRole());
                    filterChain.doFilter(servletRequest,servletResponse);
                }else{
                    if(mustFilterPath.contains(path)){
                        request.getSession().setAttribute("errorMsg","您没有权限访问该资源");
                        request.getRequestDispatcher("/powerError.jsp").forward(request,(HttpServletResponse)servletResponse);
                    }else {
                        request.getSession().setAttribute("role",user.getRole());
                        filterChain.doFilter(servletRequest,servletResponse);
                    }
                }
            }else if(request.getSession().getAttribute("role")!=null){
                if(request.getSession().getAttribute("role").equals(MANAGER)){
                    filterChain.doFilter(servletRequest,servletResponse);
                }else {
                    if(mustFilterPath.contains(path)){
                        request.getSession().setAttribute("errorMsg","您没有权限访问该资源");
                        request.getRequestDispatcher("/powerError.jsp").forward(request,(HttpServletResponse)servletResponse);
                    }else {
                        filterChain.doFilter(servletRequest,servletResponse);
                    }
                }
            }else{
                request.getSession().setAttribute("errorMsg","您尚未登录，无法访问该资源");
                request.getRequestDispatcher("/error.jsp").forward(request,(HttpServletResponse)servletResponse);

            }
        }else {
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {
        FLAG = 0;
    }
}
