<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Our Services</title>

<link rel="stylesheet" href="../css/main.css">
<link rel="stylesheet" href="../css/pages/services.css">
<link rel="stylesheet" href="../css/layout/header.css">
<link rel="stylesheet" href="../css/layout/footer.css">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>

<body>	

<jsp:include page="../components/header.jsp"/>

<section class="services-page">

  <h1>Our Services</h1>
  <p class="subtitle">
    Compassionate care with advanced medical excellence.
  </p>

  <!-- 🔥 FEATURED -->
  <div class="featured-service">
    <img src="../images/team.jpg">

    <div class="featured-text">
      <h2>Advanced Medical Care</h2>
      <p>State-of-the-art facilities with expert doctors.</p>
    </div>
  </div>

  <!-- 🔥 GRID -->
  <div class="services-grid">

    <div class="service-card">
      <img src="../images/em2.jpg">
      <h3>Emergency Care</h3>
      <p>24/7 emergency services.</p>
    </div>

    <div class="service-card">
      <img src="../images/icu.jpg">
      <h3>ICU & Critical Care</h3>
      <p>Advanced ICU monitoring.</p>
    </div>

    <div class="service-card">
      <img src="../images/dg2.jpg">
      <h3>Diagnostic Imaging</h3>
      <p>Accurate imaging solutions.</p>
    </div>

    <div class="service-card">
      <img src="../images/ms.jpg">
      <h3>Maternity Care</h3>
      <p>Complete maternity services.</p>
    </div>

    <div class="service-card">	
      <img src="../images/lb.jpg">
      <h3>Laboratory</h3>
      <p>Reliable lab testing.</p>
    </div>

    <div class="service-card">
      <img src="../images/sg.jpg">
      <h3>Surgery</h3>
      <p>Advanced surgical procedures.</p>
    </div>

  </div>

</section>

<jsp:include page="../components/footer.jsp"/>

</body>
</html>