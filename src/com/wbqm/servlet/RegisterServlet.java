package com.wbqm.servlet;

import com.wbqm.module.User;
import com.wbqm.service.IUserService;
import com.wbqm.service.ServiceFactory;
import com.wbqm.util.EmailUtil;
import com.wbqm.util.MD5Util;
import com.wbqm.util.SendEmail;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;

import java.io.IOException;

@WebServlet(urlPatterns = {
        "/register.do"
})
public class RegisterServlet extends HttpServlet {

    private static final String REGISTER_FORM = "0";
    private static final String REGISTER_STEP_1 = "1";
    private static final String REGISTER_STEP_2 = "2";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String method = request.getParameter("method");
        if (null != method)
            switch (method) {
                case REGISTER_FORM : toRegister(request, response);
                break;

                case REGISTER_STEP_1 : register1(request, response);
                break;

                case REGISTER_STEP_2 : register2(request, response);
                break;

                default: break;
            }

    }

    private void toRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.getRequestDispatcher("/WEB-INF/view/user/register_form.jsp").forward(request, response);
    }

    private void register1(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        System.out.println("进行第二步验证");
         User user = new User();
         try {
              user.setEmail(request.getParameter("email"));
              user.setNickName(request.getParameter("nickname"));
              user.setPassword(MD5Util.encode(request.getParameter("password")));
              user.setEmailVerified(false);
              user.setLastLoginTime(System.currentTimeMillis());
              user.setLastLoginIp(request.getRemoteAddr());
              user.setUserIntegral(10);
//              String verifyCode = StringUtil.generated(user.getEmail());
              String verifyCode = EmailUtil.create();
              System.out.println(verifyCode);
              SendEmail.send(user.getEmail(), "这是一封验证邮件", verifyCode);
              user.setEmailVerifyCode(verifyCode);
             ServiceFactory.getUserService().addUser(user);
             request.getSession().setAttribute("s_user", user);

             request.getRequestDispatcher("/WEB-INF/view/user/verify_form.jsp").forward(request, response);
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    private void register2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");

        String email = ((User) request.getSession().getAttribute("s_user")).getEmail();

        try {
            IUserService userService = ServiceFactory.getUserService();
            System.out.println("EMail is: " + email);
            User user = userService.findByEMail(email);
            if (null != user && code.trim().equalsIgnoreCase(user.getEmailVerifyCode())) {
                user.setEmailVerified(true);
                userService.updateUser(user);
                request.getRequestDispatcher("/WEB-INF/view/user/register_ok.jsp").forward(request, response);
            } else {
                System.out.println("邮箱验证错误，请重新验证！");
                request.setAttribute("verify_err", "邮箱验证错误，请重新验证！");
                request.getRequestDispatcher("/WEB-INF/view/user/register_form.jsp").forward(request, response);
//                request.getRequestDispatcher("/WEB-INF/view/user/register_error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
