package controller;

import filter.OrdinaryFilter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/exit.do")
public class ExitController extends HttpServlet {
    private static final String exit = "exit";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset = UTF-8");

        String msg = request.getParameter("id");
        if(exit.equals(msg)){
            request.getSession().setAttribute("role",null);
            Cookie cookie = new Cookie("userName", null);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            new OrdinaryFilter().destroy();
            response.sendRedirect("login.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
        this.doGet(request,response);
    }
}
