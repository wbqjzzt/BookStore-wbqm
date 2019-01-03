package com.tiny.servlet;

import com.tiny.module.User;
import com.tiny.service.IUserService;
import com.tiny.service.ServiceFactory;
import com.tiny.util.MD5Util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/login.do")
public class LoginServlet  extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String email = request.getParameter("email");
        IUserService userService = ServiceFactory.getUserService();
        User user = userService.findByEMail(email);

        if (null != user && user.getPassword().equals(MD5Util.encode(request.getParameter
                ("password"))) && user.getEmailVerified()) {
            user.setLastLoginIp(request.getRemoteAddr());
            user.setLastLoginTime(System.currentTimeMillis());
            userService.updateUser(user);
            request.getSession().setAttribute("s_user", user);
//            request.getRequestDispatcher("/WEB-INF/view/home/main.jsp").forward(request, response);
            response.sendRedirect("/main.do");
//            request.getRequestDispatcher("/main.do").forward(request, response);
        } else if (null != user && !user.getEmailVerified()) {
            request.getRequestDispatcher("/WEB-INF/view/user/register_form.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/view/user/login_form.jsp").forward(request, response);
        }

    }
}
