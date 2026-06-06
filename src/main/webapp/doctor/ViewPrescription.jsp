<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.amstech.hms.model.PrescriptionDTO"%>

<%
PrescriptionDTO p =
(PrescriptionDTO) request.getAttribute("prescription");
%>

<!DOCTYPE html>
<html>
<head>
<title>View Prescription</title>
</head>

<body>

<h1>Prescription Details</h1>

<h3>Patient: <%=p.getPatientName()%></h3>

<h3>Doctor: Dr. <%=p.getDoctorName()%></h3>

<h3>Date: <%=p.getAppointmentDate()%></h3>

<hr>

<h2>Medicines</h2>

<pre><%=p.getMedicines()%></pre>

<h2>Doctor Advice</h2>

<p><%=p.getNotes()%></p>

</body>
</html> --%>






<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.amstech.hms.model.PrescriptionDTO"%>

<%
PrescriptionDTO p =
(PrescriptionDTO) request.getAttribute("prescription");
%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Prescription Details</title>

<link rel="stylesheet"
href="<%=request.getContextPath()%>/css/pages/viewPrescription.css">

<link rel="stylesheet"
href="<%=request.getContextPath()%>/css/layout/header.css">

<link rel="stylesheet"
href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<div class="prescription-container">

    <!-- HEADER -->
    <div class="top-header">

        <div>
            <h1>Prescription Details</h1>
            <p>NewLife Hospital</p>
        </div>

       <!--  <div class="status-badge">
            IN_PROGRESS
        </div> -->
     
       <div class="status-badge <%=p.getStatus().toLowerCase()%>">
    <%=p.getStatus()%>
</div>     
    

    </div>

    <!-- PATIENT INFO -->
    <div class="info-card">

        <div class="info-box">
            <span>Patient Name</span>
            <h3><%=p.getPatientName()%></h3>
        </div>

        <div class="info-box">
            <span>Doctor</span>
            <h3>Dr. <%=p.getDoctorName()%></h3>
        </div>

        <div class="info-box">
            <span>Appointment Date</span>
            <h3><%=p.getAppointmentDate()%></h3>
        </div>

    </div>

    <!-- MEDICINES -->
    <div class="section">

        <h2>Medicines</h2>

        <table>

            <tr>
                <th>Sr No</th>
                <th>Medicine</th>
                <th>Dosage</th>
                <th>Timing</th>
            </tr>

<%
String[] medicineRows =
p.getMedicines().split("\n");

int count = 1;

for(String row : medicineRows){

String[] data = row.split("-");
%>

<tr>

<td><%=count++%></td>

<td>
<%=data.length > 0 ? data[0].trim() : ""%>
</td>

<td>
<%=data.length > 1 ? data[1].trim() : ""%>
</td>

<td>
<%=data.length > 2 ? data[2].trim() : ""%>
</td>

</tr>

<% } %>

        </table>

    </div>

    <!-- ADVICE -->
    <div class="section">

        <h2>Doctor Advice</h2>

        <div class="notes-box">
            <%=p.getNotes()%>
        </div>

    </div>

    <!-- ACTIONS -->
    <div class="action-buttons">

        <a class="btn download"
href="<%=request.getContextPath()%>/PrescriptionServlet?task=downloadPDF&id=<%=p.getAppointmentId()%>">
        Download PDF
        </a>

        <a class="btn back"
href="<%=request.getContextPath()%>/DoctorServlet?page=appointments">
        Back
        </a>

    </div>

</div>

<jsp:include page="../components/footer.jsp"/>

</body>
</html>