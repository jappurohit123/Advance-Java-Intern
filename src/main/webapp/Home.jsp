<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>

	<%
		String firstName = (String) request.getAttribute("firstNameValue");
		String lastName = (String) request.getAttribute("lastNameValue");
		String email = (String) request.getAttribute("emailValue");
		String gender = (String) request.getAttribute("genderValue");
	%>
	
	First Name: <%=firstName%>
	<br>
	Last Name: <%=lastName%>
	<br>
	Email : <%=email%>
	<br>
	Gender: <%=gender%>
	<br>
</body>
</html>