<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Contact Us</title>

<link rel="stylesheet" href="../css/main.css">
<link rel="stylesheet" href="../css/pages/contact.css">
<link rel="stylesheet" href="../css/layout/header.css">
<link rel="stylesheet" href="../css/layout/footer.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>


<section class="contact-hero">

<h1>Contact NewLife Hospital</h1>
<p>We're here to help you with any medical needs or inquiries.</p>

</section>


<!-- CONTACT INFO -->

<section class="contact-info">

<div class="contact-cards">

<div class="card">
<i class="fa-solid fa-phone"></i>
<h3>Call Us</h3>
<p>+91 7725045623</p>
</div>

<div class="card">
<i class="fa-solid fa-envelope"></i>
<h3>Email</h3>
<p>newjivancare@hospital.com</p>
</div>

<div class="card">
<i class="fa-solid fa-location-dot"></i>
<h3>Location</h3>
<p>Vijay Nagar, Indore</p>
</div>

</div>

</section>


<!-- CONTACT FORM + MAP -->

<section class="contact-main">

<div class="contact-container">

<div class="contact-form">

<h2>Send Message</h2>

<form>

<input type="text" placeholder="Your Name" required>

<input type="email" placeholder="Your Email" required>

<textarea placeholder="Your Message"></textarea>

<button type="submit">Send Message</button>

</form>

</div>


<div class="contact-map">

<iframe
src="https://www.google.com/maps?q=Vijay%20Nagar%20Indore&output=embed"
width="100%"
height="350"
style="border:0;"
allowfullscreen=""
loading="lazy">
</iframe>

</div>

</div>

</section>


<jsp:include page="../components/footer.jsp"/>

</body>
</html> --%>







<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>


<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

if(session.getAttribute("activeUserDTO") == null){
    response.sendRedirect("../auth/Login.jsp");
    return;
}
%>



<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Contact Us</title>

<link rel="stylesheet" href="../css/main.css">
<link rel="stylesheet" href="../css/pages/contact.css">
<link rel="stylesheet" href="../css/layout/header.css">
<link rel="stylesheet" href="../css/layout/footer.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>
<div class="contact-page">  <!-- 🔥 ADD THIS -->

<!-- 🔥 HERO -->
<section class="contact-hero">

  <img src="../images/contact2.jpg" class="hero-img">

  <div class="hero-overlay">
    <h1>Contact NewLife Hospital</h1>
    <p>We're here to help you anytime</p>
  </div>

</section>


<!-- 🔥 INFO CARDS -->
<section class="contact-info">

<div class="contact-cards">

<div class="card">
<i class="fa-solid fa-phone"></i>
<h3>Call Us</h3>
<p>+91 7725045623</p>
</div>

<div class="card">
<i class="fa-solid fa-envelope"></i>
<h3>Email</h3>
<p>newjivancare@hospital.com</p>
</div>

<div class="card">
<i class="fa-solid fa-location-dot"></i>
<h3>Location</h3>
<p>Vijay Nagar, Indore</p>
</div>

</div>

</section>


<!-- 🔥 MAIN -->
<section class="contact-main">

<div class="contact-container">

<!-- FORM -->
<div class="contact-form">

<h2>Send Message</h2>

<form>

<input type="text" placeholder="Your Name" required>
<input type="email" placeholder="Your Email" required>
<textarea placeholder="Your Message"></textarea>

<button type="submit">Send Message</button>

</form>

</div>

<!-- MAP -->
<div class="contact-map">

<iframe
src="https://www.google.com/maps?q=Vijay%20Nagar%20Indore&output=embed"
width="100%"
height="350"
style="border:0;"
loading="lazy">
</iframe>

</div>

</div>

</section>	


</div>
<jsp:include page="../components/footer.jsp"/>

</body>
</html>