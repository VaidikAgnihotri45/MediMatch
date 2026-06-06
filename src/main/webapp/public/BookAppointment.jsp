<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Contact Us</title>

<link rel="stylesheet" href="../css/main.css">


<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/DoctorList.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">



<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<form action="<%=request.getContextPath()%>/AppointmentServlet" method="post">

<input type="hidden" name="doctorId" value="<%=request.getParameter("doctorId")%>">

<label>Select Date</label>
<input type="date" name="date">

<label>Select Time</label>
<input type="time" name="time">

<button type="submit">Confirm Appointment</button>

</form>


<jsp:include page="../components/footer.jsp"/>

</body>
</html> --%>


<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<title>Book Appointment</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/BookAppointment.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<div class="appointment-container">

<div class="appointment-card">

<h2>Book Appointment</h2>
<form action="<%=request.getContextPath()%>/AppointmentServlet" method="post">
<%
String doctorId = request.getParameter("doctorId");
if(doctorId == null){
doctorId = "";
}
%>
<input type="hidden" name="doctorId" value="<%=request.getParameter("doctorId")%>">

<input type="date" name="date" required>

<input type="time" name="time" required>

<textarea name="reason"></textarea>

<button type="submit">Confirm Appointment</button>

</form>

</div>

</div>

<jsp:include page="../components/footer.jsp"/>

</body>

</html>
 --%>
 
 
 
 
<%--  <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<title>Book Appointment</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/BookAppointment.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<div class="appointment-container">

<div class="appointment-card">

<h2>Book Appointment</h2>

<%
String doctorId = request.getParameter("doctorId");
if(doctorId == null){
    doctorId = "";
}
%>

<form action="<%=request.getContextPath()%>/AppointmentServlet" method="post">

<input type="hidden" name="doctorId" value="<%=doctorId%>">

<input type="date" name="date" min="<%=java.time.LocalDate.now()%>" required>

<input type="time" name="time" required>

<textarea name="reason" placeholder="Reason for appointment"></textarea>

<button type="submit">Confirm Appointment</button>

</form>

</div>

</div>

<jsp:include page="../components/footer.jsp"/>

</body>

</html>
  --%>
  
  
  
  
  
  
 <%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<title>Book Appointment</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/BookAppointment.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<%
String doctorId = request.getParameter("doctorId");
if(doctorId == null){
doctorId = "";
}

String successMsg = (String) request.getAttribute("successMsg");
String errorMsg = (String) request.getAttribute("errorMsg");
%>

<div class="appointment-wrapper">

<div class="appointment-card">

<h2>Book Appointment</h2>

<!-- Success Message -->

<% if(successMsg != null){ %>

<div class="success-msg">
<%=successMsg %>
</div>
<% } %>

<!-- Error Message -->

<% if(errorMsg != null){ %>

<div class="error-msg">
<%=errorMsg %>
</div>
<% } %>

<form action="<%=request.getContextPath()%>/AppointmentServlet" method="post">

<input type="hidden" name="doctorId" value="<%=doctorId%>">

<div class="form-group">
<label>Select Date</label>
<input type="date" name="date" min="<%=java.time.LocalDate.now()%>" required>
</div>

<div class="form-group">
<label>Select Time</label>
<input type="time" name="time" required>
</div>

<!-- <div class="form-group">
<label>Reason</label>
<textarea name="reason" placeholder="Write reason for appointment..." maxlength="100"
 required></textarea>
</div>
 -->
 <div class="form-group">

    <label>Reason</label>

    <textarea 
        id="reason"
        name="reason"
        maxlength="100"
        placeholder="Write reason for appointment..."
        required></textarea>

    <div class="char-counter">
        <span id="charCount">0</span>/100
    </div>

</div>

<script>
document.addEventListener("DOMContentLoaded", function () {

    const textarea = document.getElementById("reason");
    const counter = document.getElementById("charCount");

    textarea.addEventListener("input", function () {

        let count = textarea.value.length;
        counter.textContent = count;

        // 🔥 limit highlight
        if(count >= 100){
            counter.style.color = "red";
        } else if(count >= 80){
            counter.style.color = "orange";
        } else {
            counter.style.color = "#888";
        }

    });

});
</script>
 
 
 
<button type="submit" class="book-btn">
Confirm Appointment
</button>

</form>

</div>

</div>

<jsp:include page="../components/footer.jsp"/>

</body>

</html>


  --%>
 
 
 
 
 
 
 
 <%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<title>Book Appointment</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/BookAppointment.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<%
String doctorId = request.getParameter("doctorId");

if(doctorId == null){
doctorId = "";
}

String successMsg = (String) request.getAttribute("successMsg");
String errorMsg = (String) request.getAttribute("errorMsg");
%>

<div class="appointment-wrapper">

<div class="appointment-card">

<h2>Book Appointment</h2>

<!-- Success -->
<% if(successMsg != null){ %>
<div class="success-msg"><%=successMsg %></div>
<% } %>

<!-- Error -->
<% if(errorMsg != null){ %>
<div class="error-msg"><%=errorMsg %></div>
<% } %>

<form action="<%=request.getContextPath()%>/AppointmentServlet" method="post">

<input type="hidden" name="doctorId" value="<%=doctorId%>">

<!-- DATE -->
<div class="form-group">
<label>Date</label>
<input type="date" name="date" min="<%=java.time.LocalDate.now()%>" required>
</div>

<!-- TIME -->
<div class="form-group">
<label>Time</label>
<input type="time" name="time" required>
</div>

<!-- REASON -->
<div class="form-group">

<label>Reason</label>

<textarea 
id="reason"
name="reason"
maxlength="100"
placeholder="Write reason for appointment..."
required></textarea>

<div class="char-counter">
<span id="charCount">0</span>/100
</div>

</div>

<!-- SUBMIT -->
<button type="submit" class="book-btn" id="submitBtn">
Confirm Appointment
</button>

</form>

</div>

</div>

<jsp:include page="../components/footer.jsp"/>

<!-- JS -->
<script>
document.addEventListener("DOMContentLoaded", function () {

    const textarea = document.getElementById("reason");
    const counter = document.getElementById("charCount");
    const button = document.getElementById("submitBtn");

    textarea.addEventListener("input", function () {

        let count = textarea.value.length;
        counter.textContent = count;

        // color feedback
        if(count >= 100){
            counter.style.color = "red";
        } else if(count >= 80){
            counter.style.color = "orange";
        } else {
            counter.style.color = "#888";
        }

    });

});
</script>

</body>
</html>
 
 
  --%>
 
 
 
 
 
  <%@page import="com.amstech.hms.model.DoctorDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>






<%
String doctorId = request.getParameter("doctorId");
boolean isDoctorSelected = (doctorId != null && !doctorId.isEmpty());
%>
<!DOCTYPE html>
<html>

<head>

<title>Book Appointment</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/BookAppointment.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<%


if(doctorId == null){
doctorId = "";
}

String successMsg = (String) request.getAttribute("successMsg");
String errorMsg = (String) request.getAttribute("errorMsg");
%>

<div class="appointment-wrapper">

<div class="appointment-card">

<h2>Book Appointment</h2>

<!-- Success -->
<% if(successMsg != null){ %>
<div class="success-msg"><%=successMsg %></div>
<% } %>

<!-- Error -->
<% if(errorMsg != null){ %>
<div class="error-msg"><%=errorMsg %></div>
<% } %>

<form action="<%=request.getContextPath()%>/AppointmentServlet" method="post">

<% if(isDoctorSelected){ %>

    <!-- Hidden doctorId (Doctor page se aaya) -->
    <input type="hidden" name="doctorId" value="<%=doctorId%>">

<% } else { %>

    <!-- Dropdown (Home page se aaya) -->
    <div class="form-group">
        <label>Select Doctor</label>

        <select name="doctorId" required>
            <option value="">-- Select Doctor --</option>

            <%
            List<DoctorDTO> doctorList = (List<DoctorDTO>) request.getAttribute("doctorList");

            if(doctorList != null){
                for(DoctorDTO d : doctorList){
            %>

            <option value="<%=d.getId()%>">
                Dr. <%=d.getFirst_name()%>
            </option>

            <% } } %>

        </select>
    </div>

<% } %>





<!-- DATE -->
<div class="form-group">
<label>Date</label>
<input type="date" name="date" min="<%=java.time.LocalDate.now()%>" required>
</div>

<!-- TIME -->
<div class="form-group">
<label>Time</label>
<input type="time" name="time" required>
</div>

<!-- REASON -->
<div class="form-group">

<label>Reason</label>

<textarea 
id="reason"
name="reason"
maxlength="100"
placeholder="Write reason for appointment..."
required></textarea>

<div class="char-counter">
<span id="charCount">0</span>/100
</div>

</div>

<!-- SUBMIT -->
<button type="submit" class="book-btn" id="submitBtn">
Confirm Appointment
</button>

</form>

</div>

</div>

<jsp:include page="../components/footer.jsp"/>

<!-- JS -->
<script>
document.addEventListener("DOMContentLoaded", function () {

    const textarea = document.getElementById("reason");
    const counter = document.getElementById("charCount");
    const button = document.getElementById("submitBtn");

    textarea.addEventListener("input", function () {

        let count = textarea.value.length;
        counter.textContent = count;

        // color feedback
        if(count >= 100){
            counter.style.color = "red";
        } else if(count >= 80){
            counter.style.color = "orange";
        } else {
            counter.style.color = "#888";
        }

    });

});
</script>

</body>
</html>
 
 
 
 
 
 











