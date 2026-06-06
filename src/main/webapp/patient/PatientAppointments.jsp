<%-- <%@ page import="java.util.*, com.amstech.hms.model.AppointmentDTO" %>

<html>
<head>
 <title>My Appointments</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">
</head>
<body>





<jsp:include page="../components/header.jsp"/>
<h2>My Appointments</h2>

<%
List<AppointmentDTO> list = (List<AppointmentDTO>) request.getAttribute("appointmentList");

if(list == null || list.isEmpty()){
%>

    <p>No appointments found.</p>

<%
} else {
%>

<table border="1" cellpadding="10">
<tr>
    <th>ID</th>
    <th>Doctor</th>
    <th>Specialization</th>
    <th>Date</th>
    <th>Time</th>
    <th>Status</th>
</tr>

<% for(AppointmentDTO a : list){ %>

<tr>
    <td><%=a.getId()%></td>
    <td><%=a.getDoctorName()%></td>
    <td><%=a.getSpecialization()%></td>
    <td><%=a.getAppointment_date()%></td>
    <td><%=a.getAppointment_time()%></td>

    <td>
        <%
        String status = a.getStatus();

        if("PENDING".equalsIgnoreCase(status)){
        %>
            <span style="color:orange;">Pending</span>
        <% } else if("APPROVED".equalsIgnoreCase(status)){ %>
            <span style="color:green;">Approved</span>
        <% } else if("COMPLETED".equalsIgnoreCase(status)){ %>
            <span style="color:blue;">Completed</span>
        <% } else { %>
            <span style="color:red;">Cancelled</span>
        <% } %>
    </td>

</tr>

<% } %>

</table>

<% } %>
<jsp:include page="../components/footer.jsp"/>

</body>
</html> --%>












<%@ page import="java.util.*, com.amstech.hms.model.AppointmentDTO" %>

<html>
<head>
<title>My Appointments</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/PatientAppointments.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">
</head>

<body>

<jsp:include page="../components/header.jsp"/>

<div class="container">

    <h2>My Appointments</h2>

<%
List<AppointmentDTO> list = (List<AppointmentDTO>) request.getAttribute("appointmentList");



if(list == null || list.isEmpty()){
%>

    <div class="empty-state">
        <h3>No appointments yet</h3>
        <p>Book your first appointment to get started</p>
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
                <h3>Dr. <%=a.getDoctorName()%></h3>
                <span class="badge <%=status.toLowerCase()%>">
                    <%=status%>
                </span>
            </div>

           <div class="card-body">

    <p><strong>Specialization:</strong> <%=a.getSpecialization()%></p>

    <p><strong>Date:</strong> <%=a.getAppointment_date()%></p>

    <p><strong>Time:</strong> <%=a.getAppointment_time()%></p>

</div>

<% if("COMPLETED".equalsIgnoreCase(status)
   || "IN_PROGRESS".equalsIgnoreCase(status)){ %>

<div class="card-actions">

    <a class="btn prescription"
    href="<%=request.getContextPath()%>/PrescriptionServlet?task=view&id=<%=a.getId()%>">
    
    View Prescription
    </a>

</div>

<% } %>

        </div>

    <% } %>

    </div>

<% } %>
</div>

<jsp:include page="../components/footer.jsp"/>

</body>
</html>