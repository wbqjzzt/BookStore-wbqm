package com.tiny.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;
import org.json.JSONObject;
import org.json.JSONException;

@WebServlet("/validateCheckCode.do")
public class ValidateCheckCode extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String checkCode = request.getParameter("checkCode");
        boolean flag = null != checkCode && checkCode.equalsIgnoreCase(request.getSession().getAttribute("verifyCode")
                .toString());

        System.out.println(flag + " verify code");
        JSONObject json = new JSONObject();
        try {
            if (flag) json.put("result", true); else json.put("result", false);

        } catch(JSONException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = response.getWriter();
        printWriter.print(json.toString());
//        printWriter.flush();
        printWriter.close();

    }
}
