<%-- <%@page import="com.amstech.hms.model.UserDTO"%>
<%@page import="com.amstech.hms.model.PrescriptionDTO"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<%
List<PrescriptionDTO> list =
(List<PrescriptionDTO>)request.getAttribute("prescriptionList");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Prescriptions</title>
</head>
<body>
<%
UserDTO user = (UserDTO) session.getAttribute("activeUserDTO");

if(user == null || user.getRole_id() != 1){
    response.sendRedirect("../auth/Login.jsp");
    return;
}


%>
<h2>My Prescriptions</h2>

<% if(list != null && !list.isEmpty()){ %>

    <% for(PrescriptionDTO p : list){ %>

        <div class="card">
            <p><strong>Doctor:</strong> <%=p.getDoctorName()%></p>
            <p><strong>Date:</strong> <%=p.getAppointmentDate()%></p>
            <p><strong>Medicines:</strong> <%=p.getMedicines()%></p>
            <p><strong>Notes:</strong> <%=p.getNotes()%></p>
            
        </div>
        
<p>DEBUG ID: <%=p.getId()%></p>

<a href="<%=request.getContextPath()%>/PrescriptionServlet?task=downloadPDF&id=<%=p.getId()%>">
    Download PDF
</a>

<a href="<%=request.getContextPath()%>/PrescriptionServlet?task=downloadPDF&id=1">    Download PDF
</a>
    <% } %>
    

<% } else { %>

    <p>No prescriptions found</p>

<% } %>

</body>
</html> --%>











<%@page import="com.amstech.hms.model.UserDTO"%>
<%@page import="com.amstech.hms.model.PrescriptionDTO"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
List<PrescriptionDTO> list =
(List<PrescriptionDTO>)request.getAttribute("prescriptionList");

UserDTO user = (UserDTO) session.getAttribute("activeUserDTO");

if(user == null || user.getRole_id() != 1){
    response.sendRedirect("../auth/Login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Prescriptions</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/prescription.css">

</head>
<body>

<div class="container">

    <h2>My Prescriptions</h2>

    <% if(list != null && !list.isEmpty()){ %>

        <div class="card-container">

        <% for(PrescriptionDTO p : list){ %>

            <div class="card">

                <div class="card-header">
                    <h3>Dr. <%=p.getDoctorName()%></h3>
                    <span class="date"><%=p.getAppointmentDate()%></span>
                </div>

                <div class="card-body">
                    <p><strong>Medicines:</strong></p>
                    <p class="text"><%=p.getMedicines()%></p>

                    <p><strong>Notes:</strong></p>
                    <p class="text"><%=p.getNotes()%></p>
                </div>

                <div class="card-footer">
                    <a class="btn"
                       href="<%=request.getContextPath()%>/PrescriptionServlet?task=downloadPDF&id=<%=p.getId()%>">
                        Download PDF
                    </a>
                </div>

            </div>

        <% } %>

        </div>

    <% } else { %>

        <div class="empty">
            <p>No prescriptions found</p>
        </div>

    <% } %>

</div>

</body>
</html>