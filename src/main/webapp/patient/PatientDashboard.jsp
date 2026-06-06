<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<title>Patient Dashboard</title>
<link rel="stylesheet" href="../css/layout/header.css">
<link rel="stylesheet" href="../css/layout/footer.css">



<link rel="stylesheet" href="../css/pages/PatientDashboard.css">


<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<div class="dashboard-container">

<div class="welcome-box">
<h2>Welcome Patient 👋</h2>
<p>Manage your appointments and health records easily</p>
</div>

<div class="card-grid">

<div class="dashboard-card">
<i class="fa-solid fa-calendar-check"></i>
<h3>Book Appointment</h3>
<a href="<%=request.getContextPath()%>/AppointmentServlet">Open</a>
</div>

<div class="dashboard-card">
<i class="fa-solid fa-clock-rotate-left"></i>
<h3>Appointment History</h3>

<a href="AppointmentHistory.jsp">View</a>
</div>

<div class="dashboard-card">
<i class="fa-solid fa-user-doctor"></i>
<h3>Doctor List</h3>
<a href="DoctorList.jsp">View</a>
</div>

<div class="dashboard-card">
<i class="fa-solid fa-credit-card"></i>
<h3>Payment</h3>
<a href="Payment.jsp">Pay</a>
</div>

<div class="dashboard-card">
<i class="fa-solid fa-user"></i>
<h3>Profile</h3>
<a href="Profile.jsp">Open</a>
</div>

</div>

</div>

<jsp:include page="../components/footer.jsp"/>

</body>
</html>