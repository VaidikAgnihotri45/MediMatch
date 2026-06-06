






<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.amstech.hms.model.AppointmentDTO"%>

<!DOCTYPE html>
<html>

<head>

<title>Manage Appointments</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/pages/adminAppointment.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

	<jsp:include page="../components/header.jsp" />

	<div class="admin-container">

		<h1>Manage Appointments</h1>

		<table class="appointment-table">

			<tr>
				<th>ID</th>
				<th>Doctor ID</th>
				<th>Patient ID</th>
				<th>Date</th>
				<th>Time</th>
				<th>Reason</th>
				<th>Status</th>
				<th>Action</th>
			</tr>

			<%
			List<AppointmentDTO> list = (List<AppointmentDTO>) request.getAttribute("appointmentList");

			if (list != null) {

				for (AppointmentDTO a : list) {
			%>

			<tr>

				<td><%=a.getId()%></td>
				<td><%=a.getDoctor_id()%></td>
				<td><%=a.getPatient_id()%></td>
				<td><%=a.getAppointment_date()%></td>
				<td><%=a.getAppointment_time()%></td>
				<td><%=a.getReason()%></td>

				<td>
					<%
					String status = a.getStatus();

					if ("Approved".equals(status)) {
					%> <span style="color: green; font-weight: bold;">Approved</span> <%
 } else if ("Cancelled".equals(status)) {
 %> <span style="color: red; font-weight: bold;">Cancelled</span> <%
 } else {
 %> <span style="color: orange; font-weight: bold;">Pending</span> <%
 }
 %>

				</td>

				<td>
					<%
					if ("PENDING".equalsIgnoreCase(status)) {
					%> <a class="approve"
					href="<%=request.getContextPath()%>/AdminAppointmentServlet?action=update&id=<%=a.getId()%>&status=Approved">

						Approve </a> <a class="cancel"
					href="<%=request.getContextPath()%>/AdminAppointmentServlet?action=update&id=<%=a.getId()%>&status=Cancelled">

						Cancel </a> <%
 } else {
 %> <span style="color: gray; font-weight: bold;">No Action</span> <%
 }
 %>

				</td>

			</tr>

			<%
			}
			}
			%>

		</table>

	</div>

	<jsp:include page="../components/footer.jsp" />

</body>

</html>

 --%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.amstech.hms.model.AppointmentDTO"%>

<!DOCTYPE html>
<html>

<head>
<title>Manage Appointments</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/adminAppointment.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<div class="admin-container">

<h1>Manage Appointments</h1>

<%
List<AppointmentDTO> list = (List<AppointmentDTO>)request.getAttribute("appointmentList");

if(list == null || list.isEmpty()){
%>

<div class="empty">
    <h3>No Appointments Found</h3>
</div>

<%
} else {
%>

<div class="card-container">

<% for(AppointmentDTO a : list){ 
    String status = a.getStatus();
%>

<div class="appointment-card">

    <div class="card-header">
        <h3>Appointment #<%=a.getId()%></h3>
        <span class="badge <%=status.toLowerCase()%>"><%=status%></span>
    </div>

    <div class="card-body">
        <p><strong>Doctor Name :</strong> <%=a.getDoctorName()%></p>
        <p><strong>Patient Name :</strong> <%=a.getPatientName()%></p>
        <p><strong>Date:</strong> <%=a.getAppointment_date()%></p>
        <p><strong>Time:</strong> <%=a.getAppointment_time()%></p>
        <p><strong>Reason:</strong> <%=a.getReason()%></p>
    </div>

    <div class="card-actions">

<% if("PENDING".equalsIgnoreCase(status)){ %>

    <a class="btn approve"
    href="<%=request.getContextPath()%>/AdminAppointmentServlet?action=update&id=<%=a.getId()%>&status=APPROVED">
    Approve
    </a>

    <a class="btn cancel"
    href="<%=request.getContextPath()%>/AdminAppointmentServlet?action=update&id=<%=a.getId()%>&status=CANCELLED">
    Cancel
    </a>

<% } else { %>

    <span class="no-action">No Action</span>

<% } %>
    </div>

</div>

<% } %>

</div>

<% } %>

</div>

<jsp:include page="../components/footer.jsp"/>

</body>
</html>


