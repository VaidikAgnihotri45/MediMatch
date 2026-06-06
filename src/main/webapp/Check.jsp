<%@page import="com.amstech.hms.model.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Header.css">
<link rel="stylesheet" href="Footer.css">
</head>
<body>
<header>
		<div class="logo">
			<h1>NewLife Hospital</h1>
		</div>
		<nav>
			<ul>
				<li><a href="HomePage.jsp">Home</a></li>
				<li><a href="About.jsp">About Us</a></li>
				<li><a href="Service.jsp">Services</a></li>
				<li><a href="Department.jsp">Departments</a></li>
				<li><a href="Contact.jsp">Contact</a></li>
				<li><a href="BookApo.jsp">Book Appointment</a></li>
				<li><a href="BookApo.jsp">Book Appointment</a></li>
			</ul>
		</nav>
	</header>
<%
	if (request.getAttribute("activeUserDTO") != null) {
		UserDTO activeUserDTO = (UserDTO) request.getAttribute("activeUserDTO");
	%>
	<h1>
		Welcome Back !
		<%=activeUserDTO.getFirst_name()%></h1>
	<%
	}

	%>
		<form action="UserServlet" method="get">
		<input type="hidden" name="task" value="findById" />
		<table>
			<tr>
				<td>Find by iD</td>
				<td><input type="text" name="userId" required="required" /></td>
			</tr>
			<tr>
				<td><input type="submit" name="Find" value="Find" /></td>
				<td><input type="reset" name="reset" /></td>
			</tr>
		</table>
	
	<%
	
	if (request.getAttribute("userDTOEdit") != null) {
		UserDTO userDTOEdit = (UserDTO) request.getAttribute("userDTOEdit");
	%>
	<h1>Edit Profile</h1>

	<form action="WWW" method="post">

		<input type="hidden" name="task" value="updateById" /> <input
			type="hidden" name="userId" value="<%=userDTOEdit.getId()%>" />
		<table>
			<tr>

				<td>Name</td>
				<td><input type="text" name="name"
					value="<%=userDTOEdit.getFirst_name()%>"></td>
			</tr>

			<tr>
				<td>Email</td>
				<td><input type="text" name="email_id"
					value="<%=userDTOEdit.getEmail()%>"></td>
			</tr>
			<tr>
				<td>Mobile Number</td>
				<td><input type="text" name="mobile_no"
					value="<%=userDTOEdit.getMobile_no()%>"></td>
			</tr>
			<tr>
				<td>Address</td>
				<td><input type="text" name="address"
					value="<%=userDTOEdit.getAddress()%>"></td>
			</tr>

			<tr>
				<td><input type="submit" name="submit"></td>
				<td><input type="reset" name="reset"></td>
			</tr>
		</table>
	</form>
	<%
	}
	%>
	<footer>
		<p>&copy; 2024 NewLife Hospital | All rights reserved</p>
		<ul>
			<li><a href="#">Privacy Policy</a></li>
			<li><a href="#">Terms of Service</a></li>
		</ul>
	</footer>
</body>
</html>