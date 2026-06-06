<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>
<%@ page import="java.util.List"%>
<%@ page import="com.amstech.hms.model.AppointmentDTO"%>

<h2>Pending Appointments</h2>

<table border="1">

<tr>
<th>ID</th>
<th>Patient</th>
<th>Date</th>
<th>Status</th>
<th>Action</th>
</tr>

<%
List<AppointmentDTO> list =
(List<AppointmentDTO>)request.getAttribute("appointmentList");

if(list != null){
for(AppointmentDTO a : list){
%>

<tr>

<td><%=a.getId()%></td>
<td><%=a.getPatientName()%></td>
<td><%=a.getAppointment_date()%></td>
<td><%=a.getStatus()%></td>

<td>

<a href="<%=request.getContextPath()%>/DoctorServlet?action=updateAppointment&id=<%=a.getId()%>&status=APPROVED">
Approve
</a>

<a href="<%=request.getContextPath()%>/DoctorServlet?action=updateAppointment&id=<%=a.getId()%>&status=REJECTED">
Reject
</a>

</td>

</tr>

<%
}
}
%>

</table>
</body>
</html>