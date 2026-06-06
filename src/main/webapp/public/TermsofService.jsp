<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<link rel="stylesheet" href="../css/main.css">
<link rel="stylesheet" href="../css/pages/homepage.css">
<link rel="stylesheet" href="../css/layout/header.css">
<link rel="stylesheet" href="../css/layout/footer.css">
<link rel="stylesheet" href="../">

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
				<li><a href="Contact.jsp">Contact</a></li>

				<li><a href="Department.jsp">Departments</a></li>
			</ul>
		</nav>
	</header>
<!-- 	<footer>
		<p>&copy; 2024 NewLife Hospital | All rights reserved</p>
		<ul>
			<li><a href="#">Privacy Policy</a></li>
			<li><a href="#">Terms of Service</a></li>
		</ul>
	</footer> -->
		<jsp:include page="../components/footer.jsp"/>
	
</body>
</html>