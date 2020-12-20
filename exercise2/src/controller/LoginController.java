package controller;

import dao.UserDao;
import tools.JdbcUtil;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset = UTF-8");

        HttpSession session = request.getSession();

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        String verifyCode = request.getParameter("verify");
        String verify_code = (String) session.getAttribute("verityCode");
        String isLogin = request.getParameter("isLogin");

        String errorMsg = "";
        User user = new UserDao().get(userName);


        if (verify_code.equalsIgnoreCase(verifyCode)) {
            if(userName.equals(user.getUserName())){
                if(password.equals(user.getPassword())){
                    if(isLogin != null){
                        Cookie cookie1 = new Cookie("userName", userName);
                        cookie1.setMaxAge(60*60*24*7);
                        response.addCookie(cookie1);
                    }else {
                        Cookie cookie1 = new Cookie("userName", null);
                        cookie1.setMaxAge(0);
                        response.addCookie(cookie1);
                    }
                    String chrName = user.getChrName();
                    String role = user.getRole();
                    session.setAttribute("chrName", chrName);
                    session.setAttribute("role", role);
                    request.getRequestDispatcher("/main.jsp").forward(request, response);
                }else {
                    errorMsg += "抱歉，您输入的密码不正确！";
                    session.setAttribute("errorMsg", errorMsg);
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            }else {
                errorMsg += "抱歉，您输入的用户名不存在！";
                session.setAttribute("errorMsg", errorMsg);
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        } else {
            errorMsg += "抱歉，您输入的验证码不正确！";
            session.setAttribute("errorMsg", errorMsg);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
