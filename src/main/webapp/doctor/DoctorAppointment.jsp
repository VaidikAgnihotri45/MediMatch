<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.amstech.hms.model.AppointmentDTO"%>
<%@ page import="com.amstech.hms.model.UserDTO"%>

<%
UserDTO user = (UserDTO)session.getAttribute("activeUserDTO");

if(user == null || user.getRole_id() != 2){
    response.sendRedirect("../auth/Login.jsp");
    return;
}

List<AppointmentDTO> list = (List<AppointmentDTO>)request.getAttribute("appointmentList");
%>

<!DOCTYPE html>
<html>

<head>
<title>Doctor Appointments</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

<style>
table {
    width: 100%;
    border-collapse: collapse;
}
th, td {
    padding: 10px;
    text-align: center;
}
th {
    background: #333;
    color: white;
}
.pending { color: orange; font-weight: bold; }
.approved { color: green; font-weight: bold; }
.cancelled { color: red; font-weight: bold; }
</style>

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<h2 style="text-align:center;">Doctor Appointments</h2>

<% if(list != null && !list.isEmpty()){ %>

<table border="1">

<tr>
<th>ID</th>
<th>Patient</th>
<th>Date</th>
<th>Status</th>
<th>Mobile</th>
<th>Email</th>
</tr>

<% for(AppointmentDTO a : list){ %>

<tr>
<td><%=a.getId()%></td>

<td>
<%= a.getPatientName() != null ? a.getPatientName() : "Unknown" %>
</td>

<td>
<%= a.getAppointment_date() != null ? a.getAppointment_date() : "-" %>
</td>

<td>
<%
String status = a.getStatus();

if("Pending".equalsIgnoreCase(status)){
%>
<span class="pending">Pending</span>
<%
} else if("Approved".equalsIgnoreCase(status)){
%>
<a href="<%=request.getContextPath()%>/DoctorServlet?action=updateAppointment&id=<%=a.getId()%>&status=APPROVED">
Approverrrr
</a>
<span class="approved">Approved</span>
<%
} else {
%>
<span class="cancelled">Cancelled</span>
<%
}
%>
</td>

<td>
<%= a.getMobile_no() != null ? a.getMobile_no() : "-" %>
</td>

<td>
<%= a.getEmail() != null ? a.getEmail() : "-" %>
</td>

</tr>

<% } %>

</table>

<% } else { %>

<p style="text-align:center;">No appointments found</p>

<% } %>

<jsp:include page="../components/footer.jsp"/>

</body>
</html> --%>




<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.amstech.hms.model.AppointmentDTO"%>
<%@ page import="com.amstech.hms.model.UserDTO"%>

<%
UserDTO user = (UserDTO)session.getAttribute("activeUserDTO");

if(user == null || user.getRole_id() != 2){
    response.sendRedirect("../auth/Login.jsp");
    return;
}

List<AppointmentDTO> list = (List<AppointmentDTO>)request.getAttribute("appointmentList");
%>

<!DOCTYPE html>
<html>

<head>
<title>Doctor Appointments</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

<style>
table { width: 100%; border-collapse: collapse; }
th, td { padding: 10px; text-align: center; }
th { background: #333; color: white; }

.pending { color: orange; font-weight: bold; }
.approved { color: green; font-weight: bold; }
.completed { color: blue; font-weight: bold; }
.cancelled { color: red; font-weight: bold; }

.action-btn { margin: 5px; padding: 5px 10px; text-decoration: none; }
.complete { background: green; color: white; }
.cancel { background: red; color: white; }
</style>

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<h2 style="text-align:center;">Doctor Appointments</h2>

<% if(list != null && !list.isEmpty()){ %>

<table border="1">

<tr>
<th>ID</th>
<th>Patient</th>
<th>Date</th>
<th>Status</th>
<th>Action</th>
<th>Mobile</th>
<th>Email</th>
</tr>

<% for(AppointmentDTO a : list){ %>

<tr>

<td><%=a.getId()%></td>

<td><%= a.getPatientName() != null ? a.getPatientName() : "Unknown" %></td>

<td><%= a.getAppointment_date() != null ? a.getAppointment_date() : "-" %></td>

<td>
<%
String status = a.getStatus();

if("PENDING".equalsIgnoreCase(status)){
%>
<span class="pending">Pending</span>

<% } else if("APPROVED".equalsIgnoreCase(status)){ %>

<span class="approved">Approved</span>

<% } else if("COMPLETED".equalsIgnoreCase(status)){ %>

<span class="completed">Completed</span>

<% } else if("CANCELLED".equalsIgnoreCase(status)){ %>

<span class="cancelled">Cancelled</span>

<% } %>
</td>	

<td>
<%
if("APPROVED".equalsIgnoreCase(status)){
%>

<a class="action-btn complete"
href="<%=request.getContextPath()%>/DoctorServlet?page=updateAppointment&id=<%=a.getId()%>&status=COMPLETED">
Complete
</a>

<a class="action-btn cancel"
href="<%=request.getContextPath()%>/DoctorServlet?page=updateAppointment&id=<%=a.getId()%>&status=CANCELLED">
Cancel
</a>

<%
} else {
%>

<span style="color:gray;">No Action</span>

<%
}
%>
</td>

<td><%= a.getMobile_no() != null ? a.getMobile_no() : "-" %></td>
<td><%= a.getEmail() != null ? a.getEmail() : "-" %></td>

</tr>

<% } %>

</table>

<% } else { %>

<p style="text-align:center;">No appointments found</p>

<% } %>

<jsp:include page="../components/footer.jsp"/>

</body>
</html>









 --%>
 
 
 
 
 
 
 
 
 
 
 
 
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List"%>
<%@ page import="com.amstech.hms.model.AppointmentDTO"%>
<%@ page import="com.amstech.hms.model.UserDTO"%>

<%
UserDTO user = (UserDTO)session.getAttribute("activeUserDTO");

if(user == null || user.getRole_id() != 2){
    response.sendRedirect("../auth/Login.jsp");
    return;
}

List<AppointmentDTO> list = (List<AppointmentDTO>)request.getAttribute("appointmentList");
%>

<!DOCTYPE html>
<html>
<head>
<title>Doctor Appointments</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/doctorAppointments.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<div class="container">

<h2>Doctor Appointments</h2>

<% if(list != null && !list.isEmpty()){ %>

<div class="card-container">

<% for(AppointmentDTO a : list){ 
   String status = a.getStatus();
%>

<div class="appointment-card">

    <div class="card-header">
        <h3><%= a.getPatientName() != null ? a.getPatientName() : "Unknown" %></h3>
        <span class="badge <%=status.toLowerCase()%>"><%=status%></span>
    </div>

    <div class="card-body">
        <p><strong>Date:</strong> <%=a.getAppointment_date()%></p>
        <p><strong>Mobile:</strong> <%=a.getMobile_no()%></p>
        <p><strong>Email:</strong> <%=a.getEmail()%></p>
    </div>

    <div class="card-actions">
   <%--    <a class="btn complete"
        href="<%=request.getContextPath()%>/DoctorServlet?page=updateAppointment&id=<%=a.getId()%>&status=COMPLETED">
        Complete
        </a> --%>
   <% if("APPROVED".equalsIgnoreCase(status)){ %>

   <a class="btn prescription"
href="<%=request.getContextPath()%>/PrescriptionServlet?action=add&appointmentId=<%=a.getId()%>">
Add Prescription
</a>
    
       <%--  <a class="btn complete"
        href="<%=request.getContextPath()%>/DoctorServlet?page=updateAppointment&id=<%=a.getId()%>&status=COMPLETED">
        Complete
        </a>	 --%>
    

    <a class="btn cancel"
    href="<%=request.getContextPath()%>/DoctorServlet?page=updateAppointment&id=<%=a.getId()%>&status=CANCELLED">
    Cancel
    </a>

<%-- <% } else if("COMPLETED".equalsIgnoreCase(status)){ %>

    <span class="done">Prescription Added</span> --%>
    
    <% } else if("IN_PROGRESS".equalsIgnoreCase(status)){ %>

<a class="btn details"
href="<%=request.getContextPath()%>/PrescriptionServlet?task=view&id=<%=a.getId()%>">
View Details</a>

    <a class="btn complete"
    href="<%=request.getContextPath()%>/DoctorServlet?page=updateAppointment&id=<%=a.getId()%>&status=COMPLETED">
    Mark Completed
    </a>

<% } else if("COMPLETED".equalsIgnoreCase(status)){ %>

    <span class="done">Treatment Completed</span>

<% } else { %>

    <span class="no-action">No Action</span>

<% } %>
    </div>

</div>

<% } %>

</div>

<% } else { %>

<div class="empty">
    <h3>No appointments found</h3>
    <p>Patients haven’t booked anything yet</p>
</div>

<% } %>

</div>

<jsp:include page="../components/footer.jsp"/>

</body>
</html>