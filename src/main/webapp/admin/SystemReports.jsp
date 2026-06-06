<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<title>System Reports</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/reports.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<div class="report-page">

<h1>System Reports</h1>

<!-- 🔥 SUMMARY -->
<div class="report-stats">

  <div class="stat">
    <h2><%=request.getAttribute("totalAppointments") != null ? request.getAttribute("totalAppointments") : 0%></h2>
    <p>Appointments</p>
  </div>

  <div class="stat">
    <h2><%=request.getAttribute("totalDoctors") != null ? request.getAttribute("totalDoctors") : 0%></h2>
    <p>Doctors</p>
  </div>

  <div class="stat">
    <h2><%=request.getAttribute("totalPatients") != null ? request.getAttribute("totalPatients") : 0%></h2>
    <p>Patients</p>
  </div>

</div>


<!-- 🔥 FILTER -->
<div class="report-filter">

<form method="get" action="<%=request.getContextPath()%>/AdminAppointmentServlet">

<input type="hidden" name="page" value="reports">

<input type="date" name="fromDate">
<input type="date" name="toDate">

<select name="status">
  <option value="">All Status</option>
  <option value="Approved">Approved</option>
  <option value="Pending">Pending</option>
  <option value="Cancelled">Cancelled</option>
</select>

<button type="submit">Filter</button>

</form>

</div>


<!-- 🔥 TABLE -->
<div class="report-table">

<table>

<tr>
  <th>ID</th>
  <th>Patient</th>
  <th>Doctor</th>
  <th>Date</th>
  <th>Status</th>
</tr>

<%
java.util.List<com.amstech.hms.model.AppointmentDTO> list =
(java.util.List<com.amstech.hms.model.AppointmentDTO>) request.getAttribute("reportList");

if(list != null){
for(com.amstech.hms.model.AppointmentDTO a : list){
%>

<tr>
  <td><%=a.getId()%></td>
  <td><%=a.getPatientName()%></td>
  <td><%=a.getDoctorName()%></td>
 <td><%=a.getAppointment_date()%></td>
 
<td class="
<%= "APPROVED".equalsIgnoreCase(a.getStatus()) ? "status-approved" : 
    "CANCELLED".equalsIgnoreCase(a.getStatus()) ? "status-cancelled" : 
    "status-pending" %>
">
    <%=a.getStatus()%>
</td></tr>

<%
}
}
%>

</table>

</div>

</div>

<jsp:include page="../components/footer.jsp"/>

</body>
</html>