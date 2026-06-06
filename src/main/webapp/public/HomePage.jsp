<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>NewLife Hospital</title>

<link rel="stylesheet" href="../css/pages/homepage.css">
<link rel="stylesheet" href="../css/layout/header.css">
<link rel="stylesheet" href="../css/layout/footer.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>

<body>

	<jsp:include page="../components/header.jsp" />

	<!-- HERO SLIDER -->
	<div class="slider">
		<!-- Input elements (radio buttons) for each slide -->
		<input type="radio" name="slider" id="slide1" checked> <input
			type="radio" name="slider" id="slide2"> <input type="radio"
			name="slider" id="slide3">

		<!-- Slider images -->
		<div class="slides">

			<div class="slide">

				<img src="../images/Gemini_Generated_Image_4u9lta4u9lta4u9l.png"
					alt="Image 1"> ChatGPT Image Mar 12, 2026, 01_48_57 AM.png


			</div>
			<div class="slide">
				<img src="../images/Gemini_Generated_Image_femqdifemqdifemq.png"
					alt="images 2">
			</div>
			<div class="slide">
				<img src="../images/upscalemedia-transformed.png" alt="images 3">


			</div>
		</div>

		Navigation buttons (dots)
		<div class="nav-dots">
			<label for="slide1" class="dot"></label> <label for="slide2"
				class="dot"></label> <label for="slide3" class="dot"></label>
		</div>
	</div>

	<!-- 	Hero Section
 -->
	<section class="hero">
		<div class="hero-content">
			<h2>Compassionate Care, Exceptional Treatment</h2>
			<p>Providing world-class healthcare with a personal touch</p>
			<a href="${pageContext.request.contextPath}/auth/Signup.jsp"
				class="cta-btn">Book an Appointment</a>


		</div>
	</section>


	<!-- SERVICES -->

	<section class="services reveal">

		<h2>Our Services</h2>

		<div class="card-grid">

			<div class="service-card">
				<i class="fa-solid fa-truck-medical"></i>
				<h3>Emergency Care</h3>
				<p>Immediate care when you need it most.</p>
			</div>

			<div class="service-card">
				<i class="fa-solid fa-heart-pulse"></i>
				<h3>Cardiology</h3>
				<p>Advanced heart treatment.</p>
			</div>

			<div class="service-card">
				<i class="fa-solid fa-bone"></i>
				<h3>Orthopedics</h3>
				<p>Expert bone treatment.</p>
			</div>

			<div class="service-card">
				<i class="fa-solid fa-brain"></i>
				<h3>Neurology</h3>
				<p>Advanced brain care.</p>
			</div>

		</div>

	</section>

	<!-- DOCTORS -->

	<section class="doctors reveal">

		<h2>Meet Our Specialists</h2>

		<div class="doctor-grid">

			<div class="doctor-card">

				<img src="../images/ChatGPT Image Mar 12, 2026, 12_46_53 AM.png">

				<h3>Dr Sharma</h3>

				<p>Cardiologist</p>

			</div>

			<div class="doctor-card">

				<img src="../images/ChatGPT Image Mar 12, 2026, 12_46_53 AM.png">

				<h3>Dr Mehta</h3>

				<p>Orthopedic</p>

			</div>

			<div class="doctor-card">

				<img src="../images/ChatGPT Image Mar 12, 2026, 12_46_53 AM.png">

				<h3>Dr Singh</h3>

				<p>Neurologist</p>

			</div>

		</div>

	</section>

	<!-- HOSPITAL STATS -->

	<section class="stats reveal">

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
				<h2>24/7</h2>
				<p>Emergency</p>
			</div>

		</div>

	</section>

	<!-- TESTIMONIAL -->

	<section class="testimonial reveal">

		<h2>Patient Reviews</h2>

		<div class="testimonial-card">

			<p>“Excellent treatment and very caring doctors. Highly
				recommended hospital.”</p>

			<h4>- Rahul</h4>

		</div>

	</section>

	<!-- CONTACT -->

	<section class="contact reveal">

		<h2>Contact Us</h2>

		<p>
			<strong>Phone:</strong> +91 7725045623
		</p>

		<p>
			<strong>Email:</strong> newjivancare@hospital.com
		</p>

		<p>
			<strong>Address:</strong> Vijaynagar Indore
		</p>

	</section>

	<!-- WHATSAPP BUTTON -->

	<a href="#" class="chat-button"> <i class="fa-brands fa-whatsapp"></i>
	</a>

	<jsp:include page="../components/footer.jsp" />

	<script>
		window.addEventListener("scroll", function() {

			let reveals = document.querySelectorAll(".reveal");

			for (let i = 0; i < reveals.length; i++) {

				let windowHeight = window.innerHeight;

				let elementTop = reveals[i].getBoundingClientRect().top;

				let elementVisible = 100;

				if (elementTop < windowHeight - elementVisible) {

					reveals[i].classList.add("active");

				}

			}

		});
	</script>

	<script>
		let index = 0;

		function autoSlide() {

			let slides = document.querySelectorAll(".slide");

			for (let i = 0; i < slides.length; i++) {
				slides[i].style.display = "none";
			}

			index++;

			if (index > slides.length) {
				index = 1
			}

			slides[index - 1].style.display = "block";

			setTimeout(autoSlide, 4000);

		}

		autoSlide();
	</script>

</body>

</html>