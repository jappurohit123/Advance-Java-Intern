package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.CategoryBean;
import com.util.DbConnection;

public class CategoryDao {
	
	public void insertCategory(CategoryBean catehoryBean) {
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"insert into category (categoryname) values (?) ");
			pstmt.setString(1, catehoryBean.getCategoryName());
			
			int records = pstmt.executeUpdate();
			System.out.println(records + " record(s) inserted.");
			
		}catch (Exception e) {
			System.out.println("Something went wrong in insertion");
			e.printStackTrace();
		}
	}
	
	public ArrayList<CategoryBean> getAllCategory(){
		ArrayList<CategoryBean> categoryList = new ArrayList<CategoryBean>();
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("Select * from category");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()==true) {
				int id = rs.getInt("categoryid");
				String nm = rs.getString("categoryname");
				
				CategoryBean category = new CategoryBean();
				category.setCategoryId(id);
				category.setCategoryName(nm);
				
				categoryList.add(category);
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong in getAllCategory");
			e.printStackTrace();
		}
		return categoryList;
	}
	
}
