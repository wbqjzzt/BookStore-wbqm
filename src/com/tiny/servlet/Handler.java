package com.tiny.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import java.util.List;

import com.tiny.module.User;
import com.tiny.service.ServiceFactory;

@WebServlet(urlPatterns = {
		"/listUser.do"
})
public class Handler extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<User> users;
		try {
			users = ServiceFactory.getUserService().find();
			request.setAttribute("users", users);
			request.getRequestDispatcher("/WEB-INF/view/users.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
