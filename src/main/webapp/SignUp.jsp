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
	<%String genderValue = (String)request.getAttribute("genderValue");
	  String genderError = (String)request.getAttribute("genderError");
	%>
	<h2>Signup</h2>
	<form method="post" action="SignupController">
	First Name: <input type="text" placeholder="First Name" name="firstName"
				value="${firstNameValue}" />${firstNameError}<br><br>
	Last Name: <input type="text" placeholder="Last Name" name="lastName" 
				value="${lastNameValue}" />${lastNameError}<br><br>
	Email: <input type="email" placeholder="Email Id" name="email" 
			value="${emailValue}" />${emailError}<br><br>
	Password: <input type="password" placeholder="Password" name="password" 
				value="${passwordValue}" />${passwordError}<br><br>
	Gender: Male<input type="radio"  name="gender" value="male" 
			<%= genderValue!=null&&genderValue.equals("male")?"checked":"" %> />
			Female<input type="radio"  name="gender" value="female"
			<%= genderValue!=null&&genderValue.equals("female")?"checked":"" %> /><br><br>
			<input type="submit" value="Signup"/>
			
	<span class="error"><%= genderError == null?"":genderError %> </span>
	</form>
</body>
</html>