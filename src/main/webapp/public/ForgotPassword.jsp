<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/UserServlet" method="post">
    
    <input type="hidden" name="task" value="forgotPassword"/>

    <h2>Forgot Password</h2>

    <input type="text" name="identifier" placeholder="Enter Email or Mobile" required/>

    <button type="submit">Send OTP</button>

    <p style="color:red;">
        <%= request.getAttribute("errorMsg") != null ? request.getAttribute("errorMsg") : "" %>
    </p>

</form>
</body>
</html> --%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Forgot Password</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/forgotPassword.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="/components/header.jsp"/>

<section class="forgot-section">

<div class="forgot-card">

    <h2>Forgot Password</h2>
    <p class="subtitle">Enter your email or mobile to receive OTP</p>

    <form action="<%=request.getContextPath()%>/UserServlet" method="post">

        <input type="hidden" name="task" value="forgotPassword"/>

        <div class="input-group">
            <input type="text" name="identifier" placeholder="Email or Mobile" required/>
        </div>

        <button type="submit" class="forgot-btn">
            Send OTP
        </button>

        <% if(request.getAttribute("errorMsg") != null){ %>
            <div class="error-box">
                <%= request.getAttribute("errorMsg") %>
            </div>
        <% } %>

    </form>

</div>

</section>

<jsp:include page="/components/footer.jsp"/>

</body>
</html>
