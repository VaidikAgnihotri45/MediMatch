<%-- 

<%
UserDTO user = (UserDTO)session.getAttribute("activeUserDTO");

if(user == null || user.getRole_id() != 3){
response.sendRedirect("../auth/Login.jsp");
return;
}
%>





<%@ page import="com.amstech.hms.model.UserDTO"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<title>Admin Dashboard</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/adminDashboard.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<div class="admin-container">

<h1>Admin Dashboard</h1>

<div class="admin-grid">

<!-- STATISTICS -->



<a href="<%=request.getContextPath()%>/AdminAppointmentServlet" class="admin-card">
<h3>Total Appointments</h3>
<p><%=request.getAttribute("totalAppointments")%></p>
</a>


<!-- MANAGEMENT -->

<a href="<%=request.getContextPath()%>/AdminAppointmentServlet" class="admin-card">
<h3>Manage Doctors</h3>
<p>Add / Update / Remove Doctors</p>
</a>


<a href="<%=request.getContextPath()%>/AdminAppointmentServlet?action=pendingDoctors"
 class="admin-card">
<h3>Manage Doctors</h3>
<p>Add / Update / Remove Doctors</p>
</a>


<a href="PatientManage.jsp" class="admin-card">
<h3>Manage Patients</h3>
<p>View Patient Records</p>
</a>

<a href="<%=request.getContextPath()%>/AdminAppointmentServlet" class="admin-card">
<h3>Manage Appointments</h3>
<p>View & Update Appointments</p>
</a>

<a href="Reports.jsp" class="admin-card">
<h3>System Reports</h3>
<p>Hospital Statistics</p>
</a>

<a href="<%=request.getContextPath()%>/AdminAppointmentServlet?action=pendingDoctors">
Manage Doctors
</a>

</div>

</div>

<jsp:include page="../components/footer.jsp"/>

</body>

</html>





 --%>






<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.amstech.hms.model.UserDTO"%>
<%-- 
<%
UserDTO user = (UserDTO)session.getAttribute("activeUserDTO");

if(user == null || user.getRole_id() != 3){
    response.sendRedirect("../auth/Login.jsp");
    return;
}
%>
 --%>
<!DOCTYPE html>
<html>
<head>

<title>Admin Dashboard</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/pages/adminDashboard.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

	<jsp:include page="../components/header.jsp" />

	<div class="admin-container">

		<h1>Admin Dashboard</h1>

		<!-- STATS -->
		<div class="stats-grid">

			<div class="stat-card blue">
				<h3>Total Appointments</h3>
				<p><%=request.getAttribute("totalAppointments") != null ? request.getAttribute("totalAppointments") : 0%></p>
			</div>

			<div class="stat-card green">
				<h3>Total Doctors</h3>
				<p><%=request.getAttribute("totalDoctors") != null ? request.getAttribute("totalDoctors") : 0%></p>
			</div>

			<div class="stat-card orange">
				<h3>Total Patients</h3>
				<p><%=request.getAttribute("totalPatients") != null ? request.getAttribute("totalPatients") : 0%></p>
			</div>

		</div>

		<!-- ACTIONS -->
		<div class="admin-grid">
    <a href="<%=request.getContextPath()%>/AdminAppointmentServlet?action=pendingDoctors"
    class="admin-card">
        <h3>Manage Doctors</h3>
        <p>Add / Approve / Remove Doctors</p>
    </a> 

    <a href="<%=request.getContextPath()%>/AdminPatientServlet"
    class="admin-card">
        <h3>Manage Patients</h3>
        <p>View & Control Patient Accounts</p>
    </a>

    <a href="<%=request.getContextPath()%>/AdminAppointmentServlet"
    class="admin-card">
        <h3>Manage Appointments</h3>
        <p>View & Update Appointments</p>
    </a>



  

<a href="<%=request.getContextPath()%>/AdminAppointmentServlet?page=reports"
        class="admin-card">
    
        <h3>System Reports</h3>
        <p>Analytics & Insights</p>
    </a>

</div>

	</div>

	<jsp:include page="../components/footer.jsp" />

</body>
</html>










