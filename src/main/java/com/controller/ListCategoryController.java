package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.CategoryBean;
import com.dao.CategoryDao;

@WebServlet("/class ListCategoryController")
public class ListCategoryController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		CategoryDao categoryDao = new CategoryDao();
		ArrayList<CategoryBean> cList = categoryDao.getAllCategory();
		
		request.setAttribute("category_list", cList);
		
		RequestDispatcher rd = request.getRequestDispatcher("ListCategory.jsp");
		rd.forward(request, response);
	}
}
