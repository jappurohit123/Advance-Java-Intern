package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.catalina.User;

import com.util.DbConnection;
import com.bean.UserBean;

//steps to follow
//1) connect database as Connection con = DbConnection.getConnection();
//2) use preparedStatement pstmt = con.preparedStatement("query") for fetching query
//3) set datatypes using 
public class UserDao {
	public UserBean getUserByUserID(int userID) {
		UserBean user = null;
		try(Connection con = DbConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement("select * from users where userid=?");
				){
			psmt.setInt(1, userID);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				user= new UserBean();
				user.setEmail(rs.getString("email"));
					user.setFirstName(rs.getString("firstname"));
					user.setLastName(rs.getString("lastname"));
					user.setGender(rs.getString("gender"));
					user.setPassword(rs.getString("password"));
					user.setUserId(userID);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public boolean updateUser(UserBean user) {
		boolean flag = false;
		try(Connection con = DbConnection.getConnection();
			PreparedStatement psmt = con.prepareStatement("update users set firstname = ?, lastname=?,gender=?,password=? where userid=?");
			)
		{
			psmt.setString(1, user.getFirstName());
			psmt.setString(2, user.getLastName());
			psmt.setString(3, user.getGender());
			psmt.setString(4, user.getPassword());
			psmt.setInt(5, user.getUserId());
			int updateCount = psmt.executeUpdate();
			if(updateCount == 1) {
				flag = true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	public boolean deleteUser(int userID) {
		boolean flag = false;
		try(
				Connection con = DbConnection.getConnection();
				PreparedStatement psmt = con.prepareStatement("delete from users where userid = ?");
				){
			psmt.setInt(1, userID);
			int deleteRows = psmt.executeUpdate();
			if(deleteRows==1) {
				flag = true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	public UserBean login(String email, String password) {
		UserBean user = null;
		try {
			Connection con = DbConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement("Select * from users where email = ? and password = ?");
			//above is new pstmt for login and dont cofuse with other pstmt in diff functions
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new UserBean();
				//all below for welcoming a user on success login
				user.setUserId(rs.getInt("userid"));
				// from rs cursor we will get col userid's nth index and set into setUserId
				user.setFirstName(rs.getString("firstname"));
				//same comment as above
				user.setUserType(rs.getString("usertype"));
			}
			con.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println("Something went wrong in login DAO");
			e.printStackTrace();
		}
		return user;
	}
	
	public void insertUser(UserBean userBean) {
		
		try {
		Connection con = DbConnection.getConnection(); //we will call getconnection method of class Dbconnection
		
		PreparedStatement pstmt = con.prepareStatement(
				"insert into users (firstname,lastname,email,password,gender,usertype) values (?,?,?,?,?,?)");

		pstmt.setString(1, userBean.getFirstName());
		pstmt.setString(2, userBean.getLastName());
		pstmt.setString(3, userBean.getEmail());
		pstmt.setString(4, userBean.getPassword());
		pstmt.setString(5, userBean.getGender());
		pstmt.setString(6, userBean.getUserType());
		
		int records = pstmt.executeUpdate();

		System.out.println(records + " record(s) inserted.");
		} catch (Exception e) {
			System.out.println("Something went wrong in insertion");
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<UserBean> getAllUsers() {
		ArrayList<UserBean> userList = new ArrayList<UserBean>(); //will store userbean objects in arraylist 
		try {
			Connection con = DbConnection.getConnection();
			
			PreparedStatement pstmt = con.prepareStatement("select * from users");
			
			ResultSet rs = pstmt.executeQuery(); //select read only => query and will work as cursor in java
			
			while(rs.next() == true) { //rs.next() will return true if row=>present and false after last row
				int id = rs.getInt("userid");
				String fn = rs.getString("firstname");
				String ln = rs.getString("lastname");
				String em = rs.getString("email");
				String pass = rs.getString("password");
				String gen = rs.getString("gender");
				//after getting element from above rs(will have col names of users table)
				
				//here we will store data in user obj 
				UserBean user  = new UserBean();
				user.setUserId(id);
				user.setFirstName(fn);
				user.setLastName(ln);
				user.setEmail(em);
				user.setPassword(pass);
				user.setGender(gen);
				
				userList.add(user);//using this userlist add we will add above user data in the list
				
			}
//			for (int i = 0; i < userList.size(); i++) { 
//	            System.out.print(userList.get(i) + " ");
//			}
		} catch (Exception e) {
			System.out.println("Something went wrong in getAllUsers.");
			e.printStackTrace();
		}
		return userList;
	}
}
