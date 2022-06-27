package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;


//DeleteController?userid=<%=user.getUserId()%>
@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid = Integer.parseInt(request.getParameter("userid"));
		UserDao userDao = new UserDao();
		if(userDao.deleteUser(userid)) {
			request.setAttribute("message", "Deleted User Successfully");
		}else {
			request.setAttribute("message", "Some error Occured in Dao");
		}
		request.getRequestDispatcher("ListUserController").forward(request, response);	
	}
}
