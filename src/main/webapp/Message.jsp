<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/reports.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">
<body>


<h1><%=(String) request.getAttribute("status")%></h1>

	<h4><%=(String) request.getAttribute("Message")%></h4>

	<a href="<%=(String) request.getAttribute("redirectUrl")%>">Click
		Here To Redirect</a><br><br>
		<footer>
		<p>&copy; 2024 NewLife Hospital | All rights reserved</p>
		<ul>
			<li><a href="#">Privacy Policy</a></li>
			<li><a href="#">Terms of Service</a></li>
		</ul>
	</footer>

</body>
</html>