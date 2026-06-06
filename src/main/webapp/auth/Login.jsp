<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="../components/header.jsp"/>
<link rel="stylesheet" href="../css/main.css">
<link rel="stylesheet" href="../css/forms/Login.css">
<link rel="stylesheet" href="../css/layout/header.css">
<link rel="stylesheet" href="../css/layout/footer.css">
<link rel="stylesheet" href="../">

<body>
</head>

<body>

	<div class="login-container">
		<div class="login-card">

			<h2>Login to Your Account</h2>
			
<!-- 			<form method="POST" action="UserServlet">
 -->			<form action="${pageContext.request.contextPath}/UserServlet" method="post">
				<input type="hidden" name="task" value="Login">

				<div class="input-group">
					<label for="username">Username</label> <input type="text"
						id="username" name="username" required>
				</div>
				<div class="input-group">
					<label for="password">Password</label> <input type="password"
						id="password" name="password" required>
				</div>
				<div class="input-group">
					<button type="submit" class="btn">Login</button>
				</div>
			</form>
			<div class="forgot-password">
				<a href="#">Forgot Password?</a>
		<br>
		<br>
		                <p>you don't have an  account. ? <a href="Signup.jsp">signup</a></p>
		
			</div>
		</div>
	</div>
			<jsp:include page="../components/footer.jsp"/>

</body>
</html> --%>






<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Login</title>

<link rel="stylesheet" href="../css/main.css">
<link rel="stylesheet" href="../css/layout/header.css">
<link rel="stylesheet" href="../css/forms/Login.css">

<link rel="stylesheet" href="../css/layout/footer.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<section class="login-section">

<div class="login-card">

<h2>Welcome Back</h2>
<p class="login-sub">Login to access your account</p>

<%-- <form action="${pageContext.request.contextPath}/auth/UserServlet" method="post">
 --%>
 <form action="<%=request.getContextPath()%>/UserServlet" method="post">
<%--  <form action="${pageContext.request.contextPath}/UserServlet" method="post">
 --%><input type="hidden" name="task" value="Login">

<div class="input-group">
<i class="fa-solid fa-user"></i>

<input type="text" name="username" placeholder="Username" required>
</div>

<div class="input-group">
<i class="fa-solid fa-lock"></i>
<input type="password" name="password" placeholder="Password" required>
</div>

<button class="login-btn">Login</button>

<p class="forgot">
<!-- <a href="#">Forgot Password?</a>
 -->
 <a href="<%=request.getContextPath()%>/public/ForgotPassword.jsp">Forgot Password?</a>
 </p>

<p class="signup">
Don't have an account?
<a href="${pageContext.request.contextPath}/auth/Signup.jsp">Signup</a>
</p>

</form>

</div>

</section>

<jsp:include page="../components/footer.jsp"/>

</body>
</html>