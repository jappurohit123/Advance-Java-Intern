<%@page import="com.bean.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user List</title>
<link rel="stylesheet" 
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" 
	crossorigin="anonymous">
</head>
<body>
	<%
		ArrayList<UserBean> uList = (ArrayList<UserBean>)request.getAttribute("user_list");  
	%>
	<div class="row">${message }
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<h2 align="center">Users List</h2>
				<table class ="table table-striped">
					<thead class="thead-dark">
					<tr>
						<th>UserId</th>
						<th>FirstName</th>
						<th>LastName</th>
						<th>Email</th>
						<th>Gender</th>
						<th>Action</th>
					</tr>
					</thead>
					<tbody>
					<%
						for (UserBean user : uList) {
					%>
					<tr>
						<td><%=user.getUserId()%></td>
						<td><%=user.getFirstName()%></td>
						<td><%=user.getLastName()%></td>
						<td><%=user.getEmail()%></td>
						<td><%=user.getGender()%></td>
						<td><a href="DeleteController?userid=<%=user.getUserId()%>">Delete</a> |
						<a href="UpdateUserFormController?userid=<%=user.getUserId() %>"> Update</a>
						 </td>
					</tr>
					<%
						}
					%>
					</tbody>
			
				</table>
			</div>
		</div>
	</div>
	
	
</body>
</html>