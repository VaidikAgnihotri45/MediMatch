<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.amstech.hms.model.DoctorDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pending Doctors</title>
</head>

<body>

<h2>Pending Doctors</h2>

<%
List<DoctorDTO> list = (List<DoctorDTO>) request.getAttribute("doctorList");
%>

<% if(list != null && !list.isEmpty()){ %>

<table border="1">

<tr>
<th>Name</th>
<th>Email</th>
<th>Specialization</th>
<th>Status</th>
<th>Action</th>
</tr>

<% for(DoctorDTO d : list){ %>

<tr>

<td><%= d.getFirst_name() %></td>
<td><%= d.getEmail() %></td>
<td><%= d.getSpecialization() %></td>
<td><%= d.getStatus() %></td>

<td>

<!-- ✅ APPROVE -->
<a href="<%=request.getContextPath()%>/AdminAppointmentServlet?action=approveDoctor&id=<%= d.getId() %>">
Approve
</a>

&nbsp; | &nbsp;

<!-- ✅ REJECT -->
<a href="<%=request.getContextPath()%>/AdminAppointmentServlet?action=rejectDoctor&id=<%= d.getId() %>">
Reject
</a>

</td>

</tr>

<% } %>

</table>

<% } else { %>

<p>No pending doctors</p>

<% } %>

</body>
</html> --%>






<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.amstech.hms.model.DoctorDTO"%>

<%
List<DoctorDTO> list = (List<DoctorDTO>) request.getAttribute("doctorList");
String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin • Pending Doctors</title>

<link rel="stylesheet" href="<%=ctx%>/css/pages/DoctorManage.css">
<link rel="stylesheet" href="<%=ctx%>/css/layout/header.css">
<link rel="stylesheet" href="<%=ctx%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<div class="admin-wrapper">

    <div class="top-bar">
        <h2>Pending Doctors</h2>
        <input type="text" id="search" placeholder="Search doctor..." />
    </div>

<% if(list != null && !list.isEmpty()){ %>

<div class="grid" id="doctorContainer">

<% for(DoctorDTO d : list){ %>

<div class="card">

    <!-- HEADER -->
    <div class="card-top">
        <div class="avatar">
            <%= (d.getFirst_name()!=null && !d.getFirst_name().isEmpty())
                ? d.getFirst_name().charAt(0)
                : "?" %>
        </div>

        <div class="info">
            <h3><%=d.getFirst_name()%></h3>
            <span class="badge pending"><%=d.getStatus()%></span>
        </div>
    </div>

    <!-- BODY -->
    <div class="card-body">
        <p><span>Email:</span> <%=d.getEmail()%></p>
        <p><span>Specialization:</span> <%=d.getSpecialization()%></p>
    </div>

    <!-- ACTIONS -->
    <div class="actions">

        <a class="btn success"
        href="<%=ctx%>/AdminAppointmentServlet?action=approveDoctor&id=<%= d.getId() %>">
        Approve
        </a>

        <a class="btn danger"
        href="<%=ctx%>/AdminAppointmentServlet?action=rejectDoctor&id=<%= d.getId() %>">
        Reject
        </a>

    </div>

</div>

<% } %>

</div>

<% } else { %>

<div class="empty-state">
    <h3>No Pending Doctors</h3>
    <p>All doctors are verified</p>
</div>

<% } %>

</div>

<jsp:include page="../components/footer.jsp"/>

<script>
document.getElementById("search").addEventListener("keyup", function(){
    let value = this.value.toLowerCase();
    let cards = document.querySelectorAll(".card");

    cards.forEach(card => {
        let text = card.innerText.toLowerCase();
        card.style.display = text.includes(value) ? "flex" : "none";
    });
});
</script>

</body>
</html>