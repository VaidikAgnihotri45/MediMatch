<%@page import="com.amstech.hms.model.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%-- <%

if (user == null || user.getRole_id() != 1) {
    response.sendRedirect("auth/Login.jsp");
    return;
}
%> --%>

<title>Patient Dashboard</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/layout/footer.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/admin/pati.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>

<body>

	<jsp:include page="../components/header.jsp" />

	<%
	UserDTO activeUserDTO = (UserDTO) session.getAttribute("activeUserDTO");
	%>

	<div class="dashboard-container">

		<div class="welcome-box">

			<%
			if (activeUserDTO != null) {
			%>

			<h2>
				Welcome
				<%=activeUserDTO.getFirst_name()%>
				👋
			</h2>
			<p>Manage your appointments and health records easily</p>

			<%
			String success = request.getParameter("success");
			if ("updated".equals(success)) {
			%>
			<div class="success-msg">✅ Profile updated successfully!</div>
			<%
			}
			%>

			<%
			} else {
			%>

			<h2>Welcome Patient 👋</h2>
			<p>Manage your appointments and health records easily</p>

			<%
			}
			%>

		</div>

		<div class="card-grid">

			<!-- Book Appointment -->
			<div class="dashboard-card">
				<i class="fa-solid fa-calendar-check"></i>
				<h3>Book Appointment</h3>
				<a href="<%=request.getContextPath()%>/AppointmentServlet">Open</a>
			</div>

			<!-- Appointment History -->
			<div class="dashboard-card">
				<i class="fa-solid fa-clock-rotate-left"></i>
				<h3>Appointment History</h3>
				<a
					href="<%=request.getContextPath()%>/UserServlet?task=myAppointments">
					My Appointments </a>
			</div>

			<!-- Doctor List -->
			<div class="dashboard-card">
				<i class="fa-solid fa-user-doctor"></i>
				<h3>Doctor List</h3>
				<a href="<%=request.getContextPath()%>/DoctorServlet?task=findAll">
					View </a>
			</div>

			<!-- Payment -->
			<div class="dashboard-card">
				<i class="fa-solid fa-credit-card"></i>
				<h3>Payment</h3>
				<a href="PrescriptionHistory.jsp">Pay</a>
			</div>

			<div class="dashboard-card">
				<i class="fa-solid fa-credit-card"></i>
				<h3>Prescriptions</h3>
				<a
					href="<%=request.getContextPath()%>/UserServlet?task=prescriptionHistory">
					View Prescriptions </a>
			</div>

			<!-- Profile -->
			<div class="dashboard-card">
				<i class="fa-solid fa-user"></i>
				<h3>Profile</h3>

				<%
				if (activeUserDTO != null) {
				%>

				<form action="<%=request.getContextPath()%>/UserServlet"
					method="get">
					<input type="hidden" name="task" value="findById"> <input
						type="hidden" name="userId" value="<%=activeUserDTO.getId()%>">

					<button type="submit" class="card-btn">Edit</button>
				</form>

				<%
				} else {
				%>

				<button class="card-btn disabled-btn" disabled>Edit</button>

				<%
				}
				%>


		<%-- 		<a
					href="<%=request.getContextPath()%>/UserServlet?task=prescriptionHistory">
					View Prescriptions </a> --%>
			</div>

		</div>


	</div>

	<jsp:include page="../components/footer.jsp" />

</body>
</html>