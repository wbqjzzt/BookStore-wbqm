package com.wbqm.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;

import com.wbqm.module.User;
import com.wbqm.service.ServiceFactory;
import org.json.JSONObject;
import org.json.JSONException;

@WebServlet("/validateEmail.do")
public class ValidateEmail extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        System.out.println("Hello");
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String email = request.getParameter("email");
        JSONObject json = new JSONObject();
        try {

            User user = ServiceFactory.getUserService().findByEMail(email);

            if (null != user) {
//                System.out.println("user is not NULL");
                json.put("result", true);
            } else {
//                System.out.println("user is NULL");
                json.put("result", false);
            }

        } catch(JSONException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.print(json.toString());

        printWriter.close();

    }
}
