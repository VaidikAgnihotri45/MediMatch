<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Status</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/Messages.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<div class="status-page">

    <div class="status-card">

        <!-- STATUS -->
        <h1 class="
        <%= "SUCCESS".equalsIgnoreCase((String)request.getAttribute("status")) ? "success" : "error" %>
        ">
            <%= (String) request.getAttribute("status") %>
        </h1>

        <!-- MESSAGE -->
        <p class="message">
            <%= (String) request.getAttribute("Message") %>
        </p>

        <!-- BUTTON -->
        <a href="<%= (String) request.getAttribute("redirectUrl") %>" class="redirect-btn">
            Go Back
        </a>

    </div>

</div>

<jsp:include page="../components/footer.jsp"/>


<script>
setTimeout(function(){
    window.location.href = "<%= request.getAttribute("redirectUrl") %>";
}, 2000); // 2 sec बाद redirect
</script>
</body>
</html>