<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List"%>
<%@ page import="com.amstech.hms.model.PatientDTO"%>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

if(session.getAttribute("activeUserDTO") == null){
    response.sendRedirect("../auth/Login.jsp");
    return;
}
%>



<%
List<PatientDTO> list = (List<PatientDTO>) request.getAttribute("patientList");
%>

<!DOCTYPE html>
<html>
<head>

<title>Manage Patients</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/pages/patientManage.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/header.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<div class="container">

<h2>Manage Patients</h2>

<% if(list != null && !list.isEmpty()){ %>

<div class="card-container">

<% for(PatientDTO p : list){ %>

<div class="patient-card">

    <!-- HEADER -->
    <div class="card-header">
        <div class="avatar">
            <%= p.getFirst_name().charAt(0) %>
        </div>

        <div>
            <h3><%=p.getFirst_name()%></h3>

            <span class="status <%= "ACTIVE".equalsIgnoreCase(p.getStatus()) ? "active" : "inactive"%>">
                <%=p.getStatus()%>
            </span>
        </div>
    </div>

    <!-- BODY -->
    <div class="card-body">
        <p><strong>Email:</strong> <%=p.getEmail()%></p>
        <p><strong>Mobile:</strong> <%=p.getMobile_no()%></p>
    </div>

    <!-- ACTIONS -->
    <div class="card-actions">

        <% if("ACTIVE".equalsIgnoreCase(p.getStatus())){ %>

        <a class="btn deactivate"
        href="AdminPatientServlet?action=deactivate&id=<%=p.getId()%>">
        Deactivate
        </a>

        <% } else { %>

        <a class="btn activate"
        href="AdminPatientServlet?action=activate&id=<%=p.getId()%>">
        Activate
        </a>

        <% } %>

        <a class="btn delete"
        onclick="return confirm('Are you sure you want to delete this patient?')"
        href="AdminPatientServlet?action=delete&id=<%=p.getId()%>">
        Delete
        </a>

    </div>

</div>

<% } %>

</div>

<% } else { %>

<div class="empty">
    <h3>No Patients Found</h3>
</div>

<% } %>

</div>

<jsp:include page="../components/footer.jsp"/>

</body>
</html>