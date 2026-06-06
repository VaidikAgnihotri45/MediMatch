	
	<%--  <%@page import="com.amstech.hms.model.UserDTO"%>
	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<!DOCTYPE html>
	<html>
	<head>
	
	<title>Edit Profile</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/PatientManage.css">
	
	</head>
	
	<body>
	
	<jsp:include page="../components/header.jsp"/>
	
	<%
	if (request.getAttribute("userDTOEdit") != null) {
	UserDTO userDTOEdit = (UserDTO) request.getAttribute("userDTOEdit");
	%>
	
	<div class="profile-card">
	
	<h2>Edit Profile</h2>
	
	<form action="<%=request.getContextPath()%>/UserServlet" method="post">
	
	<input type="hidden" name="task" value="updateById">
	<input type="hidden" name="userId" value="<%=userDTOEdit.getId()%>">
	
	<div class="form-group">
	<label>First Name</label>
	<input type="text" name="first_name" value="<%=userDTOEdit.getFirst_name()%>">
	</div>
	
	<div class="form-group">
	<label>Last Name</label>
	<input type="text" name="last_name" value="<%=userDTOEdit.getLast_name()%>">
	</div>
	
	<div class="form-group">
	<label>Email</label>
	<input type="text" name="email" value="<%=userDTOEdit.getEmail()%>">
	</div>
	
	<div class="form-group">
	<label>Mobile Number</label>
	<input type="text" name="mobile_no" value="<%=userDTOEdit.getMobile_no()%>">
	</div>
	
	<div class="form-group">
	<label>Address</label>
	<input type="text" name="address" value="<%=userDTOEdit.getAddress()%>">
	</div>
	
	<div class="form-group">
	<label>Gender</label>
	<input type="text" name="gender" value="<%=userDTOEdit.getGender()%>">
	</div>
	
	<div class="form-group">
	<label>Age</label>
	<input type="text" name="age" value="<%=userDTOEdit.getAge()%>">
	</div>
	
	<div class="btn-group">
	<button type="submit" class="update-btn">Update Profile</button>
	<button type="reset" class="reset-btn">Reset</button>
	</div>
	
	</form>
	
	</div>
	
	<%
	}
	%>
	
	<jsp:include page="../components/footer.jsp"/>
	
	</body>
	</html> --%>
	
	
	
	
	<%-- <%@page import="com.amstech.hms.model.UserDTO"%>
	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<!DOCTYPE html>
	<html>
	<head>
	<title>Edit Profile</title>
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/PatientManage.css">
	
	</head>
	
	<body>
	
	<jsp:include page="../components/header.jsp"/>
	
	<%
	UserDTO userDTOEdit = (UserDTO) request.getAttribute("userDTOEdit");
	if (userDTOEdit != null) {
	
	String emailError = (String) request.getAttribute("emailError");
	String mobileError = (String) request.getAttribute("mobileError");
	%>
	
	<div class="profile-card">
	
	    <h2>Edit Profile</h2>
	
	    <!-- 🔥 TOP ERROR -->
	    <% if (emailError != null || mobileError != null) { %>
	        <div class="error-box">
	            <%= emailError != null ? emailError : mobileError %>
	        </div>
	    <% } %>
	
	    <form action="<%=request.getContextPath()%>/UserServlet" method="post">
	
	        <input type="hidden" name="task" value="updateById">
	        <input type="hidden" name="userId" value="<%=userDTOEdit.getId()%>">
	
	        <div class="form-group">
	            <label>First Name</label>
	            <input type="text" name="first_name" value="<%=userDTOEdit.getFirst_name()%>">
	        </div>
	
	        <div class="form-group">
	            <label>Last Name</label>
	            <input type="text" name="last_name" value="<%=userDTOEdit.getLast_name()%>">
	        </div>
	
	        <div class="form-group">
	            <label>Email</label>
	            <input type="text" name="email" value="<%=userDTOEdit.getEmail()%>">
	        </div>
	
	        <div class="form-group">
	            <label>Mobile Number</label>
	            <input type="text" name="mobile_no" value="<%=userDTOEdit.getMobile_no()%>">
	        </div>
	
	        <div class="form-group">
	            <label>Address</label>
	            <input type="text" name="address" value="<%=userDTOEdit.getAddress()%>">
	        </div>
	
	        <div class="form-group">
	            <label>Gender</label>
	            <input type="text" name="gender" value="<%=userDTOEdit.getGender()%>">
	        </div>
	
	        <div class="form-group">
	            <label>Age</label>
	            <input type="text" name="age" value="<%=userDTOEdit.getAge()%>">
	        </div>
	
	        <div class="btn-group">
	            <button type="submit" class="update-btn">Update Profile</button>
	            <button type="reset" class="reset-btn">Reset</button>
	        </div>
	
	    </form>
	
	</div>
	
	<%
	}
	%>
	
	<jsp:include page="../components/footer.jsp"/>
	
	</body>
	</html>
	 --%>
	
	
	
	
		<%@page import="com.amstech.hms.model.UserDTO"%>
		<%@ page language="java" contentType="text/html; charset=UTF-8"
			pageEncoding="UTF-8"%>
		
		<!DOCTYPE html>
		<html>
		<head>
		<title>Edit Profile</title>
		
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/layout/header.css">
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/layout/footer.css">
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/css/pages/PatientManage.css">
		
		</head>
		
		<body>
		
			<jsp:include page="../components/header.jsp" />
		
			<%
			UserDTO userDTOEdit = (UserDTO) request.getAttribute("userDTOEdit");
		
			String emailError = (String) request.getAttribute("emailError");
			String mobileError = (String) request.getAttribute("mobileError");
			String errorMsg = (String) request.getAttribute("errorMsg");
		
			String success = request.getParameter("success");
			%>
		
			<div class="profile-card">
		
				<h2>Edit Profile </h2>
		
				<!-- ✅ SUCCESS -->
				<%
				if ("updated".equals(success)) {
				%>
				
		<div id="toast" class="toast show">
		    ✔ Profile updated successfully
		</div>
				<%
				}
				%>
		
				<!-- ❌ ERROR -->
				<%
				if (errorMsg != null || emailError != null || mobileError != null) {
				%>
				<%-- <div class="error-box">
					<%=errorMsg != null ? errorMsg : (emailError != null ? emailError : mobileError)%>
				</div> --%>
				<%
				}
				%>
		
				<%
				if (userDTOEdit != null) {
				%>
		
				<form action="<%=request.getContextPath()%>/UserServlet" method="post"
					id="editForm">
		
					<input type="hidden" name="task" value="updateById"> <input
						type="hidden" name="userId" value="<%=userDTOEdit.getId()%>">
		
					<div class="form-group">
						<label>First Name</label> <input type="text" name="first_name"
							value="<%=userDTOEdit.getFirst_name()%>" required>
					</div>
		
					<div class="form-group">
						<label>Last Name</label> <input type="text" name="last_name"
							value="<%=userDTOEdit.getLast_name()%>" required>
					</div>
		
					<div class="form-group">
						<label>Email</label> <input type="email" name="email"
							value="<%=userDTOEdit.getEmail()%>" required>
		
						<%
						if (emailError != null) {
						%>
						<span class="field-error"><%=emailError%></span>
						<%
						}
						%>
					</div>
					<div class="form-group">
						<label>Mobile Number</label> <input type="text" name="mobile_no"
							value="<%=userDTOEdit.getMobile_no()%>" required>
		
						<%
						if (mobileError != null) {
						%>
						<span class="field-error"><%=mobileError%></span>
						<%
						}
						%>
					</div>
					<div class="form-group">
						<label>Address</label> <input type="text" name="address"
							value="<%=userDTOEdit.getAddress()%>" required>
					</div>
		
					<div class="form-group">
						<label>Gender</label> <select name="gender">
							<option
								<%="Male".equals(userDTOEdit.getGender()) ? "selected" : ""%>>Male</option>
							<option
								<%="Female".equals(userDTOEdit.getGender()) ? "selected" : ""%>>Female</option>
						</select>
					</div>
						
		
					<div class="form-group">
						<label>Age</label> <input type="number" name="age"
							value="<%=userDTOEdit.getAge()%>" min="1" max="120" required>
					</div>
		 	<button type="submit" id="updateBtn" class="update-btn" disabled>
		    <span id="btnText">Update Profile</span>
		    <span id="loader" style="display:none;">⏳</span>
		</button>
						<button type="reset" class="reset-btn">Reset</button>
				</form>
		
		</div>
				<%
				
				}
				
				%>
		
		
<script>
document.addEventListener("DOMContentLoaded", function(){

    const form = document.getElementById("editForm");
    const btn = document.getElementById("updateBtn");

    if(!form) return;

    // 🔥 first load disabled
    btn.disabled = true;

    // 🔥 store initial values
    const initialValues = {};
    new FormData(form).forEach((v,k)=> initialValues[k]=v);

    // 🔥 detect changes (enable button)
    form.addEventListener("input", function(){
        let changed = false;
        new FormData(form).forEach((v,k)=>{
            if(v !== initialValues[k]) changed = true;
        });
        btn.disabled = !changed;
    });

    // 🔥 NORMAL SUBMIT (NO AJAX)
    form.addEventListener("submit", function(e){

        let email = form.querySelector("[name='email']").value.trim();
        let mobile = form.querySelector("[name='mobile_no']").value.trim();

        let emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;

        if(!emailPattern.test(email)){
            alert("Invalid email");
            e.preventDefault(); // ❌ रोक
            return;
        }

        if(!/^[0-9]{10}$/.test(mobile)){
            alert("Mobile must be 10 digits");
            e.preventDefault(); // ❌ रोक
            return;
        }

        // ✔️ valid → form submit hone de
        btn.disabled = true;
    });

});
</script>
		
		</body>
		</html>
		
