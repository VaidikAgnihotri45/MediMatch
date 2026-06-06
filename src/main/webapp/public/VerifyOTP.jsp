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

    <input type="hidden" name="task" value="verifyOTP"/>
    <input type="hidden" name="userId" value="<%= request.getAttribute("userId") %>"/>

    <h2>Verify OTP</h2>

    <input type="text" name="otp" placeholder="Enter OTP" required/>

    <button type="submit">Verify</button>

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
<title>Verify OTP</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/verifyOTP.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="/components/header.jsp"/>

<section class="otp-section">

<div class="otp-card">

    <h2>Verify OTP</h2>
    <p class="subtitle">Enter the OTP sent to your email/mobile</p>

    <form action="<%=request.getContextPath()%>/UserServlet" method="post">

        <input type="hidden" name="task" value="verifyOTP"/>
        <input type="hidden" name="userId" value="<%= request.getAttribute("userId") %>"/>

        <div class="input-group">
            <input type="text" name="otp" placeholder="Enter OTP" required/>
        </div>

        <button type="submit" class="otp-btn">
            Verify OTP
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