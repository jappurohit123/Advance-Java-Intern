package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.UserBean;
import com.dao.UserDao;

//Steps to follow
//1) class will extend HttpServlet
//2) instead of main class we have protected void service
//3) @WebServlet("\LoginController")


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//get parameter will get email and password from our login form in web page 
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDao userDao = new UserDao();// creating object of UserDao
		
		UserBean user = userDao.login(email, password); //passing login method to user object of userbean
		
		RequestDispatcher rd = null;
		if(user == null) {
			// in setAtrritbute(String: variable name will be declared by us, as String is a class so 2nd argument is object(in any terms) we pass)
			request.setAttribute("errorMsg", "Invalid Credentials");
			//if user null then getRequestDispatcher will go to Login.jsp
			rd = request.getRequestDispatcher("Login.jsp");
		}
		else {
			if(user.getUserType().equals("customer")) {
				// customer on successful login will be dispatched to home page
				rd = request.getRequestDispatcher("Home.jsp");
			}
			else if(user.getUserType().equals("admin")) {
				rd = request.getRequestDispatcher("Dashboard.jsp");
			}
			else {
				rd = request.getRequestDispatcher("Hacker.jsp");
			}
		}
		rd.forward(request, response);// whichever rd will be fetched will be forwarded
	}
}
