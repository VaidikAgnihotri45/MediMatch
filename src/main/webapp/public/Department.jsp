<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Departments</title>

<link rel="stylesheet" href="../css/pages/departments.css">
<link rel="stylesheet" href="../css/layout/header.css">
<link rel="stylesheet" href="../css/layout/footer.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<section class="department-section">

  <h1>Our Departments</h1>
  <p class="dept-sub">Specialized healthcare with expert doctors</p>

  <div class="department-container">

    <!-- CARD 1 -->
    <div class="department-card reveal">
      <img src="../images/c3.jpg">

      <div class="overlay">
        <h3>Cardiology</h3>
        <p>Advanced heart care specialists</p>
<!--         <a href="BookAppointment.jsp">Book Now</a>
 -->        <a href="<%=request.getContextPath()%>/AppointmentServlet">Book Now</a>
      </div>
    </div>

    <!-- CARD 2 -->
    <div class="department-card reveal">
      <img src="../images/b2.jpg">

      <div class="overlay">
        <h3>Neurology</h3>
        <p>Brain & nervous system treatment</p>
        <a href="BookAppointment.jsp">Book Now</a>
      </div>
    </div>

    <!-- CARD 3 -->
    <div class="department-card reveal">
      <img src="../images/o2.jpg">

      <div class="overlay">
        <h3>Orthopedics</h3>
        <p>Bones & joints specialists</p>
        <a href="BookAppointment.jsp">Book Now</a>
      </div>
    </div>

    <!-- CARD 4 -->
    <div class="department-card reveal">
      <img src="../images/pp.jpg">

      <div class="overlay">
        <h3>Pediatrics</h3>
        <p>Child healthcare experts</p>
        <a href="BookAppointment.jsp">Book Now</a>
      </div>
    </div>

    <!-- CARD 5 -->
    <div class="department-card reveal">
      <img src="../images/e2.jpg">

      <div class="overlay">
        <h3>Ophthalmology</h3>
        <p>Advanced eye care</p>
        <a href="BookAppointment.jsp">Book Now</a>
      </div>
    </div>

    <!-- CARD 6 -->
    <div class="department-card reveal">
      <img src="../images/o1.jpg">

      <div class="overlay">
        <h3>Radiology</h3>
        <p>Modern imaging services</p>
        <a href="BookAppointment.jsp">Book Now</a>
      </div>
    </div>

  </div>

</section>

<script>
window.addEventListener("load", function(){

  let cards = document.querySelectorAll(".reveal");

  cards.forEach((card, index) => {
    setTimeout(() => {
      card.classList.add("active");
    }, index * 150); // ek-ek karke aayega
  });

});
</script>
<jsp:include page="../components/footer.jsp"/>

</body>
</html>