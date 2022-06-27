package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.UserBean;
import com.dao.UserDao;

@WebServlet("/UpdateUserFormController")
public class UpdateUserFormController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID = Integer.parseInt(request.getParameter("userid"));
		UserDao userDao = new UserDao();
		UserBean user = userDao.getUserByUserID(userID);
		request.setAttribute("user", user);
		request.getRequestDispatcher("UpdateUser.jsp").forward(request, response);
	}
}
