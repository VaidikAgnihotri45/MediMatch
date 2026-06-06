<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>




<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>About Us</title>

<link rel="stylesheet" href="../css/main.css">
<link rel="stylesheet" href="../css/layout/header.css">
<link rel="stylesheet" href="../css/layout/footer.css">
<link rel="stylesheet" href="../css/pages/about.css">


</head>

<body>

<jsp:include page="../components/header.jsp"/>


<!-- ABOUT HERO -->

<section class="about-hero">

  <h1>About NewLife Hospital</h1>

  <p>
    Providing compassionate care with advanced medical technology
    and experienced doctors dedicated to your health.
  </p>

</section>


<!-- ABOUT SECTION -->

<section class="about-section reveal">

  <div class="about-container">

    <div class="about-text">
      <h2>Who We Are</h2>

      <p>
        NewLife Hospital is committed to providing world-class healthcare services.
        Our experienced doctors, modern facilities, and patient-first approach ensure
        the best treatment for every patient.
      </p>

      <p>
        We believe healthcare should be accessible, compassionate,
        and driven by innovation. Our mission is to improve lives
        through quality medical care and advanced treatment.
      </p>
    </div>

    <div class="about-image">
      <img src="../images/ChatGPT Image Mar 11, 2026, 10_08_24 PM.png">
    </div>

  </div>
  
   </section>
  
  
  
<!--    <br style="background:white;">
 -->   
   
	  
  <section class="about-section reveal">

  <div class="about-container reverse">

    <div class="about-image">
      <img src="../images/op2.jpg">
    </div>

    <div class="about-text">
      <h2>Our Facilities</h2>

      <p>
        Our hospital is equipped with state-of-the-art infrastructure,
        modern operation theatres, advanced diagnostic labs,
        and comfortable patient rooms.
      </p>

      <p>
        We ensure a safe, clean, and technologically advanced environment
        to deliver the best healthcare experience.
      </p>
    </div>

  </div>

</section>




<!-- 🔥 OUR MISSION -->
<section class="about-section reveal">

  <div class="about-container">

    <!-- TEXT LEFT -->
    <div class="about-text">
      <h2>Our Mission</h2>

      <p>
        Our mission is to provide high-quality healthcare services 
        with compassion, innovation, and excellence.
      </p>

      <p>
        We aim to improve lives by combining medical expertise 
        with modern technology and patient-centered care.
      </p>
    </div>

    <!-- IMAGE RIGHT -->
    <div class="about-image">
      <img src="../images/hand.jpg" alt="Doctor caring patient">
    </div>

  </div>

</section>

<!-- WHY -->

<section class="why-section reveal">

  <h2>Why Choose Us</h2>

  <div class="why-grid">

    <div class="why-card">
      <i class="fa-solid fa-user-doctor"></i>
      <h3>Expert Doctors</h3>
      <p>Highly experienced specialists in every department.</p>
    </div>

    <div class="why-card">
      <i class="fa-solid fa-microscope"></i>
      <h3>Advanced Technology</h3>
      <p>Modern equipment for accurate diagnosis and treatment.</p>
    </div>

    <div class="why-card">
      <i class="fa-solid fa-truck-medical"></i>
      <h3>24/7 Emergency</h3>
      <p>Round-the-clock emergency services for critical care.</p>
    </div>

  </div>

</section>


<!-- STATS -->

<section class="stats-section">

  <div class="stats-grid">

    <div class="stat">
      <h2>120+</h2>
      <p>Doctors</p>
    </div>

    <div class="stat">
      <h2>5000+</h2>
      <p>Patients</p>
    </div>

    <div class="stat">
      <h2>15+</h2>
      <p>Departments</p>
    </div>

    <div class="stat">
      <h1>24/7</h1>
      <p>Emergency</p>
    </div>

  </div>

</section>


<!-- 🔥 ANIMATION SCRIPT -->

<script>
/* scroll reveal */
window.addEventListener("scroll", function(){
  let reveals = document.querySelectorAll(".reveal");

  reveals.forEach(el => {
    let top = el.getBoundingClientRect().top;
    let windowHeight = window.innerHeight;

    if(top < windowHeight - 100){
      el.classList.add("active");
    }
  });
});

/* counter animation */
let counters = document.querySelectorAll(".stat h2");

counters.forEach(counter => {
  let target = +counter.innerText.replace("+","");
  let count = 0;

  let update = () => {
    let speed = target / 80;

    if(count < target){
      count += speed;
      counter.innerText = Math.ceil(count) + "+";
      setTimeout(update, 30);
    } else {
      counter.innerText = target + "+";
    }
  };

  update();
});
</script>

<jsp:include page="../components/footer.jsp"/>

</body>
</html>