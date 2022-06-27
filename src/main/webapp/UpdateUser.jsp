<%@page import="com.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert Data</title>
<style type="text/css">
	.error{
		color: red; 
	}
</style>

</head>
<body>
	<%UserBean user = (UserBean)request.getAttribute("user");
		String genderValue = user.getGender();  
	%>
	<h2>Update User</h2>
	<form method="post" action="UpdateUserController">
	<input type="hidden" value="${user.userId }" name="userId">
	First Name: <input type="text" placeholder="First Name" name="firstName"
				value="${user.firstName}" />${firstNameError}<br><br>
	Last Name: <input type="text" placeholder="Last Name" name="lastName" 
				value="${user.lastName}" />${lastNameError}<br><br>
	Email: <input type="email" placeholder="Email Id" name="email" 
			value="${user.email}" disabled />${emailError}<br><br>
	Password: <input type="password" placeholder="Password" name="password" 
				value="${user.password}" />${passwordError}<br><br>
	Gender: Male<input type="radio"  name="gender" value="male" 
			<%= genderValue!=null&&genderValue.equals("male")?"checked":"" %> />
			Female<input type="radio"  name="gender" value="female"
			<%= genderValue!=null&&genderValue.equals("female")?"checked":"" %> /><br><br>
			<input type="submit" value="Update User"/>
			
	</form>
</body>
</html>