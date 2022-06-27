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


@WebServlet("/SignupController")
public class SignupController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("Hello from SignupController.");
	
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");

		
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(email);
		System.out.println(password);
		System.out.println(gender);
		
		boolean isError = false;

		if(firstName == null || firstName.trim().length() == 0) {
			isError = true;
			request.setAttribute("firstNameError", "*Please Enter First Name");
		}
		else{
			request.setAttribute("firstNameValue", firstName);
		}
		if(lastName == null || lastName.trim().length() == 0) {
			isError = true;
			request.setAttribute("lastNameError", "*Please Enter Last Name");
		}
		else {
			request.setAttribute("lastNameValue", lastName);
		}
		if(email == null || email.trim().length() == 0) {
			isError = true;
			request.setAttribute("emailError", "*Please Enter Email");
		}
		else {
			request.setAttribute("emailValue", email);
		}
		if(password == null || password.trim().length() == 0) {
			isError = true;
			request.setAttribute("passwordError", "*Please Enter Password");
		}
		else {
			request.setAttribute("passwordValue", password);
		}
		if(gender==null) {
			isError = true;
			request.setAttribute("genderError", "*Please Enter Gender");
		}
		else {
			request.setAttribute("genderValue", gender);
		}
		
		RequestDispatcher rd = null;
		if(isError == true) {
			rd = request.getRequestDispatcher("SignUp.jsp");
		}
		else {
			
			
			UserDao userDao = new UserDao();
			
			UserBean userBean = new UserBean();
			
			userBean.setFirstName(firstName);
			userBean.setLastName(lastName);
			userBean.setEmail(email);
			userBean.setPassword(password);
			userBean.setGender(gender);
			userBean.setUserType("customer");
			//Now signup page is always for customers and not for admin. So fixed "customer"
			
			
			userDao.insertUser(userBean);
			
			//if signup is successful then customer will be dispatched to login.jsp with msg, signup done
			request.setAttribute("signupMsg", "Sign Up Successful");
			rd = request.getRequestDispatcher("Login.jsp");
			
		}
		rd.forward(request, response);
//		request.setAttribute("firstName", firstName);
//		request.setAttribute("lastName", lastName);
//		request.setAttribute("email", email);
//		request.setAttribute("password", password);
//		request.setAttribute("gender", gender);
// 
//		RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
//		rd.forward(request, response);
		
		
	}
	
	
	
}
