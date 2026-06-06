	<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ page import="java.util.List"%>
	<%@ page import="com.amstech.hms.model.DoctorDTO"%>
	
	<!DOCTYPE html>
	<html>
	<head>
	
	<title>Doctor List</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/DoctorList.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">
	
	
	
	</head>
	
	<body>
	<jsp:include page="../components/header.jsp"/>
	
	
	<h2 class="title">Our Doctors</h2>
	
	<div class="doctor-container">
	
	<%
	List<DoctorDTO> doctorList = (List<DoctorDTO>) request.getAttribute("doctorList");
	
	
	if(doctorList != null && !doctorList.isEmpty()){
	
	for(DoctorDTO d : doctorList){
	%>
	
	<div class="doctor-card">
	
	 <h3>
	Dr. <%=d.getFirst_name()%> <%=d.getEmail()%>
	</h3>
	
	<p class="spec">
	Specialization : <%= d.getSpecialization() != null ? d.getSpecialization() : "Not Updated" %>
	</p>
	
	<p class="shift">
	Shift : <%= d.getShift_time() != null ? d.getShift_time() : "Not Set" %>
	</p>
	
	<a class="book-btn"
	
	
	COMMENT H Yaha
	href="<%=request.getContextPath()%>/public/BookAppointment.jsp?doctorId=<%=d.getUser_id()%>">
	
	 gg
	href="<%=request.getContextPath()%>/public/BookAppointment.jsp?doctorId=<%=d.getId()%>">
	Book Appointment
	</a>
	
	</div>
	
	<%
	}
	}
	%>
	
	</div>
	
	<jsp:include page="../components/footer.jsp"/>
	
	</body>
	
	</html> --%>
	
	
	
	<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.amstech.hms.model.DoctorDTO"%>

<!DOCTYPE html>
<html>
<head>

<title>Doctor List</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/DoctorList.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<h2 class="title">Our Doctors</h2>

<div class="doctor-wrapper">

<div class="doctor-container">

<%
List<DoctorDTO> doctorList = (List<DoctorDTO>) request.getAttribute("doctorList");

if(doctorList != null && !doctorList.isEmpty()){
for(DoctorDTO d : doctorList){
%>

<div class="doctor-card">

    <img src="<%=request.getContextPath()%>/images/doctorAV.jpg" class="doctor-img">

    <div class="doctor-header">

        <div class="avatar">
            <%= d.getFirst_name().charAt(0) %>
        </div>

        <div>
            <h3>Dr. <%=d.getFirst_name()%></h3>
            <p class="email"><%=d.getEmail()%></p>
        </div>

    </div>

    <div class="doctor-body">

        <p>
            <span><i class="fa-solid fa-user-doctor"></i> Specialization</span>
            <strong>
                <%= d.getSpecialization() != null ? d.getSpecialization() : "Not Updated" %>
            </strong>
        </p>

        <p>
            <span><i class="fa-solid fa-clock"></i> Shift</span>
            <strong>
                <%= d.getShift_time() != null ? d.getShift_time() : "Not Set" %>
            </strong>
        </p>

    </div>

    <a class="book-btn"
    href="<%=request.getContextPath()%>/public/BookAppointment.jsp?doctorId=<%=d.getId()%>">
        <i class="fa-solid fa-calendar-check"></i>
        Book Appointment
    </a>

</div>

<%
}
}
%>

</div>

</div>

<jsp:include page="../components/footer.jsp"/>

</body>
</html>