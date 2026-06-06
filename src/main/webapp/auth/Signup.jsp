<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Signup</title>

<jsp:include page="../components/header.jsp"/>

<link rel="stylesheet" href="../css/main.css">
<link rel="stylesheet" href="../css/forms/Signup.css">
<link rel="stylesheet" href="../css/layout/header.css">
<link rel="stylesheet" href="../css/layout/footer.css">

</head>

<body>

<section class="signup-section">

<div class="signup-card">

<h2>Create Account</h2>
<form action="<%=request.getContextPath()%>/UserServlet" method="post">

<input type="hidden" name="task" value="signup">

<div class="form-row">

<input type="text" name="first_name" placeholder="First Name" required>

<input type="text" name="last_name" placeholder="Last Name" required>

</div>


<div class="form-row">

<input type="email" name="email" placeholder="Email Address" required>

<input type="text" name="mobile_no" placeholder="Mobile Number">

</div>


<div class="form-row">

<input type="password" name="password" placeholder="Password" required>

<input type="text" name="age" placeholder="Age">

</div>


<div class="form-row">

<input type="text" name="address" placeholder="Address">

</div>


<div class="gender">

<label>Gender</label>

<div class="gender-options">

<label><input type="radio" name="gender" value="male"> Male</label>

<label><input type="radio" name="gender" value="female"> Female</label>

<label><input type="radio" name="gender" value="other"> Other</label>

</div>

</div>


<select name="hospital_id" class="input-select">

<option value="">Select Hospital</option>

<option value="1">New Life Hospital</option>

</select>


<select name="role_id" class="input-select">

<option value="0">Select Role</option>

<option value="1">Patient</option>

<option value="2">Doctor</option>

<option value="3">Admin</option>

</select>


<button type="submit" class="signup-btn">

Create Account

</button>

</form>

</div>

</section>

<jsp:include page="../components/footer.jsp"/>

</body>
</html> --%>






<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Signup</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/forms/Signup.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">
</head>

<body>
<jsp:include page="/components/header.jsp"/>
<section class="signup-section">

<div class="signup-card">

<h2>Create Account</h2>
<%
String error = (String) request.getAttribute("errorMsg");
if(error != null){
%>
<div style="color:red; text-align:center; margin-bottom:10px;">
    <%= error %>
</div>
<%
}
%>

<form action="<%=request.getContextPath()%>/UserServlet" method="post">

<input type="hidden" name="task" value="signup">

<div class="form-row">

<input type="text" name="first_name" placeholder="First Name" required>

<input type="text" name="last_name" placeholder="Last Name" required>

</div>


<div class="form-row">

<input type="email" name="email" placeholder="Email Address" required>

<input type="text" name="mobile_no" placeholder="Mobile Number"required>

</div>


<div class="form-row">

<input type="password" name="password" placeholder="Password" required>

<input type="text" name="age" placeholder="Age" required >

</div>


<div class="form-row">

<input type="text" name="address" placeholder="Address" required>

</div>


<div class="gender" >

<label>Gender</label>

<div class="gender-options">

<label><input type="radio" name="gender" required value="male"> Male</label>

<label><input type="radio" name="gender" required value="female"> Female</label>

<label><input type="radio" name="gender"  required value="other"> Other</label>

</div>

</div>


<select name="hospital_id" class="input-select">

<option value="">Select Hospital</option>

<option value="1">New Life Hospital</option>

</select>


<select name="role_id" class="input-select">

<option value="0">Select Role</option>

<option value="1">Patient</option>

<option value="2">Doctor</option>

<option value="3">Admin</option>

</select>


<button type="submit" class="signup-btn">

Create Account

</button>

</form>

</div>

</section>

<jsp:include page="/components/footer.jsp"/>
<!-- 🔥 VALIDATION SCRIPT -->
<script>
document.querySelector("form").addEventListener("submit", function(e){

    let firstName = document.querySelector("[name='first_name']").value.trim();
    let email = document.querySelector("[name='email']").value.trim();
    let mobile = document.querySelector("[name='mobile_no']").value.trim();
    let password = document.querySelector("[name='password']").value.trim();
    let age = document.querySelector("[name='age']").value.trim();
    let role = document.querySelector("[name='role_id']").value;

    // First Name
    if(firstName.length < 2){
        alert("First name must be at least 2 characters");
        e.preventDefault();
        return;
    }

    // Email
    let emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
    if(!emailPattern.test(email)){
        alert("Enter valid email");
        e.preventDefault();
        return;
    }

    // Mobile
    if(mobile !== "" && !/^[0-9]{10}$/.test(mobile)){
        alert("Enter valid 10 digit mobile number");
        e.preventDefault();
        return;
    }

    // Password
    if(password.length < 6){
        alert("Password must be at least 6 characters");
        e.preventDefault();
        return;
    }

    // Age
    if(age !== "" && (isNaN(age) || age < 1 || age > 120)){
        alert("Enter valid age");
        e.preventDefault();
        return;
    }

    // Role
    if(role == "0"){
        alert("Please select role");
        e.preventDefault();
        return;
    }

});
</script>	
<script>
document.addEventListener("DOMContentLoaded", function(){

    console.log("Validation JS Loaded");

    let emailInput = document.querySelector("input[name='email']");
    <small id="emailError" style="color:red;"></small>
    if(!emailInput){
        console.log("Email input not found ❌");
        return;
    }

    emailInput.addEventListener("input", function(){

        let email = this.value.trim();

        let pattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;

        if(email === ""){
            emailError.innerText = "";
        }
        else if(!pattern.test(email)){
            emailError.innerText = "Invalid email format";
        } 
        else{
            emailError.innerText = "";
        }

    });

});
</script>
</body>
</html>