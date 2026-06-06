<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="UserServlet" method="post">

    <input type="hidden" name="task" value="resetPassword"/>
    <input type="hidden" name="userId" value="<%= request.getAttribute("userId") %>"/>

    <h2>Reset Password</h2>

    <input type="password" name="password" placeholder="Enter New Password" required/>

    <button type="submit">Reset Password</button>

</form>
</body>
</html> --%>




<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Reset Password</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/resetPassword.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="/components/header.jsp"/>

<section class="reset-section">

<div class="reset-card">

    <h2>Reset Your Password</h2>
    <p class="subtitle">Enter a new secure password</p>

    <form action="<%=request.getContextPath()%>/UserServlet" method="post">

        <input type="hidden" name="task" value="resetPassword"/>
        <input type="hidden" name="userId" value="<%= request.getAttribute("userId") %>"/>

        <div class="input-group">
            <input type="password" name="password" placeholder="New Password" required/>
        </div>

        <button type="submit" class="reset-btn">
            Update Password
        </button>

    </form>

</div>

</section>

<jsp:include page="/components/footer.jsp"/>

</body>
</html>