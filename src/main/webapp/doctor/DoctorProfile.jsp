<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="com.amstech.hms.model.UserDTO"%>

<%
UserDTO user = (UserDTO)session.getAttribute("activeUserDTO");

if(user == null || user.getRole_id() != 2){
    response.sendRedirect("../auth/Login.jsp");
    return;
}
%>

<h2>Complete Your Doctor Profile</h2>

<form action="<%=request.getContextPath()%>/DoctorProfileServlet" method="post">

    <label>Specialization:</label><br>
    <input type="text" name="specialization" required><br><br>

    <label>Shift Time:</label><br>
    <input type="text" name="shift_time" required><br><br>

    <button type="submit">Save Profile</button>
    
    
    

</form>	
</body>
</html> --%>




<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.amstech.hms.model.UserDTO"%>

<%
UserDTO user = (UserDTO)session.getAttribute("activeUserDTO");

if(user == null || user.getRole_id() != 2){
    response.sendRedirect("../auth/Login.jsp");
    return;
}

String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Doctor Profile Setup</title>

<link rel="stylesheet" href="<%=ctx%>/css/pages/DoctorProfile.css">
<link rel="stylesheet" href="<%=ctx%>/css/layout/header.css">
<link rel="stylesheet" href="<%=ctx%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<div class="profile-wrapper">

    <div class="profile-card">

        <h2>Complete Your Doctor Profile</h2>
        <p class="subtitle">Add your professional details</p>

        <form action="<%=ctx%>/DoctorProfileServlet" method="post">

            <!-- SPECIALIZATION -->
            <div class="form-group">
                <label>Specialization</label>
                <input type="text" name="specialization" 
                placeholder="e.g. Cardiologist, Dentist..." required>
            </div>

            <!-- SHIFT -->
            <div class="form-group">
                <label>Shift Time</label>
                <input type="text" name="shift_time" 
                placeholder="e.g. 9 AM - 5 PM" required>
            </div>

            <!-- BUTTON -->
            <button type="submit" class="btn-primary">
                Save Profile
            </button>

        </form>

    </div>

</div>

<jsp:include page="../components/footer.jsp"/>

</body>
</html>