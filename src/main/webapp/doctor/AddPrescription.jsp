<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Add Prescription</title>

<style>
body {
	font-family: Arial;
	background: #f4f6f9;
}

.container {
	width: 400px;
	margin: 80px auto;
	padding: 20px;
	background: white;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

textarea {
	width: 100%;
	height: 160px;
	padding: 10px;
	margin-top: 10px;
	    resize: none;
	
	border-radius: 5px;
}

button {
	margin-top: 15px;
	padding: 10px;
	width: 100%;
	background: #28a745;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}
</style>
</head>

<body>

	<div class="container">

		<h2>Add Prescription</h2>

		<form action="<%=request.getContextPath()%>/PrescriptionServlet"
			method="post">

			<input type="hidden" name="appointmentId"
				value="<%=request.getAttribute("appointmentId")%>" /> <label>Medicines:</label>
<!-- 			<textarea name="medicines" required></textarea>
 -->
 <textarea 
    name="medicines"
    required
    placeholder="Dolo 650 - 1 Tablet - Morning&#10;Azithromycin - 1 Tablet - Night&#10;HP-33 - 2 Tablet - Evening">
</textarea>
			<label>Notes:</label>
<!-- 			<textarea name="notes" required></textarea>
 -->
  <textarea 
    name="notes"
    required
    placeholder="Take medicines after meal.
Drink plenty of water." >
</textarea>
			<button type="submit">Save Prescription</button>

		</form>

	</div>

</body>
</html>