<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ page language="java" contentType="text/html; charset=UTF-8"
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
%>

<!DOCTYPE html>
<html>

<head>

<title>Doctor Appointments</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/doctorAppointment.css">

</head>

<body>

<h2 style="text-align:center;">Doctor Appointment Panel</h2>

<div class="container">

<table class="appointment-table">

<tr>
<th>ID</th>
<th>Patient ID</th>
<th>Date</th>
<th>Time</th>
<th>Status</th>
<th>Action</th>
</tr>

<%
List<AppointmentDTO> list = (List<AppointmentDTO>)request.getAttribute("appointmentList");

if(list != null){

for(AppointmentDTO a : list){
%>

<tr>

<td><%=a.getId()%></td>
<td><%=a.getPatient_id()%></td>
<td><%=a.getAppointment_date()%></td>
<td><%=a.getAppointment_time()%></td>

<td>
<span class="status <%=a.getStatus()%>">
<%=a.getStatus()%>
</span>
</td>

<td>

<% if("Pending".equals(a.getStatus())){ %>

<a class="approve"
href="<%=request.getContextPath()%>/AdminAppointmentServlet?action=update&id=<%=a.getId()%>&status=Approved">
Approve
</a>

<a class="cancel"
href="<%=request.getContextPath()%>/AdminAppointmentServlet?action=update&id=<%=a.getId()%>&status=Cancelled">
Cancel
</a>

<% } else { %>

<span class="done">No Action</span>

<% } %>

</td>

</tr>

<%
}
}
%>

</table>

</div>

</body>

</html>

</html> --%>


<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="com.amstech.hms.model.UserDTO"%>

<%
UserDTO user = (UserDTO)session.getAttribute("activeUserDTO");

if(user == null || user.getRole_id() != 2){
response.sendRedirect("../auth/Login.jsp");
return;
}
%><%@ page import="com.amstech.hms.model.DoctorDTO"%>

<%
DoctorDTO d = (DoctorDTO) request.getAttribute("doctor");
%>

<!DOCTYPE html>
<html>

<head>
<title>Doctor Dashboard</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/doctorDashboard.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">
</head>

<body>
<jsp:include page="../components/header.jsp"/>

<div class="dashboard">

<!-- HEADER -->
<div class="header">
<h2>Welcome Dr. <%=user.getFirst_name()%></h2>
<p>Manage your appointments efficiently</p>
</div>

<!-- CARDS -->
<div class="card-container">

<div class="card blue">
<h3>Total Appointments</h3>
<p>${totalAppointments}</p>
</div>

<div class="card orange">
<h3>Pending</h3>
<p>${pendingAppointments}</p>
</div>

<div class="card green">
<h3>Manage</h3>
<a href="<%=request.getContextPath()%>/DoctorServlet?page=appointments">
Go →
</a>
</div>


<% if(d != null){ %>

<p>Specialization: <%= d.getSpecialization() %></p>
<p>Status: <%= d.getStatus() %></p>

<% } %>
</div>

</div>
<jsp:include page="../components/footer.jsp"/>

</body>
</html>	 --%>



	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ page import="com.amstech.hms.model.UserDTO"%>
	<%@ page import="com.amstech.hms.model.DoctorDTO"%>
	
	<%
	UserDTO user = (UserDTO)session.getAttribute("activeUserDTO");
	
	if(user == null || user.getRole_id() != 2){
	    response.sendRedirect("../auth/Login.jsp");
	    return;
	}
	
	DoctorDTO d = (DoctorDTO) request.getAttribute("doctor");
	%>
	
	<!DOCTYPE html>
	<html>
	
	<head>
	<title>Doctor Dashboard</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/doctorDashboard.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">
	</head>
	
	<body>
	
	<jsp:include page="../components/header.jsp"/>
	
	<div class="dashboard">
	
	    <!-- HEADER -->
	    <div class="header">
	        <h2>Welcome Dr. <%=user.getFirst_name()%></h2>
	        <p>Manage your appointments efficiently</p>
	    </div>
	
	    <!-- CARDS -->
	    <div class="card-container">
	
	        <div class="card blue">
	            <h3>Total Appointments</h3>
	            <p>${totalAppointments}</p>
	        </div>
	
	        <div class="card orange">
	            <h3>Pending</h3>
	            <p>${pendingAppointments}</p>
	        </div>
	
	        <div class="card green">
	            <h3>Manage</h3>
	             <a href="<%=request.getContextPath()%>/DoctorServlet?page=appointments">
	                Go →
	            </a>
	         <%--    <a href="<%=request.getContextPath()%>/AdminAppointmentServlet" >   Go →
	            </a> --%>
	        </div>
	
	    </div>
	
	    <!-- DOCTOR PROFILE INFO -->
	    <% if(d != null){ %>
	
	   <%--  <div class="doctor-info">
	
	        <h3>Your Profile</h3>
	
	        <p>
	            <strong>Specialization:</strong>
	            <%= d.getSpecialization() != null ? d.getSpecialization() : "Not Updated" %>
	        </p>
	
	        <p>
	            <strong>Status:</strong>
	            <%= d.getStatus() != null ? d.getStatus() : "Pending" %>
	        </p>
	
	        <!-- PROFILE INCOMPLETE WARNING -->
	        <% if(d.getSpecialization() == null){ %>
	            <p style="color:red;">
	                ⚠ Complete your profile to activate your account
	            </p>
	
	            <a href="<%=request.getContextPath()%>/doctor/profile.jsp">
	                Complete Profile
	            </a>
	        <% } %>
	
	    </div>
	 --%>
	 
	 
	 
	 
	 
	 <div class="profile-card">
	
	    <div class="profile-header">
	        <div class="avatar">
	            <%= user.getFirst_name().charAt(0) %>
	        </div>
	        <div>
	            <h3>Dr. <%= user.getFirst_name() %></h3>
	            <span class="badge <%= d.getStatus().equals("ACTIVE") ? "active" : "inactive" %>">
	                <%= d.getStatus() %>
	            </span>
	        </div>
	    </div>

    <div class="profile-details">

        <div class="info-box">
            <span>Specialization</span>
            <p><%= d.getSpecialization() != null ? d.getSpecialization() : "Not Updated" %></p>
        </div>

        <div class="info-box">
            <span>Email</span>
            <p><%= user.getEmail() %></p>
        </div>

        <div class="info-box">
            <span>Mobile</span>
            <p><%= user.getMobile_no() %></p>
        </div>

    </div>

    <% if(d.getSpecialization() == null){ %>
        <div class="warning">
            ⚠ Complete your profile to activate your account
            <a href="<%=request.getContextPath()%>/doctor/profile.jsp">Complete Now</a>
        </div>
    <% } %>

</div>
 
 
 
 
 
 
 
 
    <% } %>

</div>

<jsp:include page="../components/footer.jsp"/>

</body>
</html>








