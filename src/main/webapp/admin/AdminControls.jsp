<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.List"%>
<%@ page import="com.amstech.hms.model.PatientDTO"%>

<%
List<PatientDTO> list = (List<PatientDTO>)request.getAttribute("patientList");
String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>

<title>Manage Patients</title>

<link rel="stylesheet" href="<%=ctx%>/css/pages/AdminControls.css">
<link rel="stylesheet" href="<%=ctx%>/css/layout/header.css">
<link rel="stylesheet" href="<%=ctx%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<div class="container">

<h2>Manage Patients</h2>

<!-- SEARCH -->
<input type="text" id="search" placeholder="Search patient..." />

<% if(list == null || list.isEmpty()){ %>

<div class="empty">
    <h3>No Patients Found</h3>
</div>

<% } else { %>

<div class="card-container" id="patientContainer">

<% for(PatientDTO p : list){ %>

<div class="patient-card">

    <!-- HEADER -->
    <div class="card-header">
        <div class="avatar">
            <%= (p.getFirst_name() != null && !p.getFirst_name().isEmpty())
                ? p.getFirst_name().charAt(0)
                : "?" %>
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
        href="<%=ctx%>/AdminPatientServlet?action=deactivate&id=<%=p.getId()%>">
        Deactivate
        </a>

        <% } else { %>

        <a class="btn activate"
        href="<%=ctx%>/AdminPatientServlet?action=activate&id=<%=p.getId()%>">
        Activate
        </a>

        <% } %>

        <a class="btn delete"
        onclick="return confirm('Are you sure you want to delete this patient?')"
        href="<%=ctx%>/AdminPatientServlet?action=delete&id=<%=p.getId()%>">
        Delete
        </a>

    </div>

</div>

<% } %>

</div>

<% } %>

</div>

<jsp:include page="../components/footer.jsp"/>

<!-- SEARCH SCRIPT -->
<script>
document.getElementById("search").addEventListener("keyup", function(){
    let value = this.value.toLowerCase();
    let cards = document.querySelectorAll(".patient-card");

    cards.forEach(card => {
        let text = card.innerText.toLowerCase();
        card.style.display = text.includes(value) ? "block" : "none";
    });
});
</script>

</body>
</html> --%>







<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.List"%>
<%@ page import="com.amstech.hms.model.PatientDTO"%>

<%
List<PatientDTO> list = (List<PatientDTO>)request.getAttribute("patientList");
String ctx = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>

<title>Admin • Manage Patients</title>

<link rel="stylesheet" href="<%=ctx%>/css/pages/AdminControls.css">
<link rel="stylesheet" href="<%=ctx%>/css/layout/header.css">
<link rel="stylesheet" href="<%=ctx%>/css/layout/footer.css">

</head>

<body>

<jsp:include page="../components/header.jsp"/>

<div class="admin-wrapper">

    <div class="top-bar">
        <h2>Patient Management</h2>
        <input type="text" id="search" placeholder="Search by name, email..." />
    </div>

<% if(list == null || list.isEmpty()){ %>

    <div class="empty-state">
        <h3>No Patients Found</h3>
        <p>No records available in system</p>
    </div>

<% } else { %>

<div class="grid" id="patientContainer">

<% for(PatientDTO p : list){ %>

<div class="card">

    <!-- HEADER -->
    <div class="card-top">
        <div class="avatar">
            <%= (p.getFirst_name()!=null && !p.getFirst_name().isEmpty())
                ? p.getFirst_name().charAt(0)
                : "?" %>
        </div>

        <div class="info">
            <h3><%=p.getFirst_name()%></h3>

            <span class="badge <%= "ACTIVE".equalsIgnoreCase(p.getStatus()) ? "active" : "inactive"%>">
                <%=p.getStatus()%>
            </span>
        </div>
    </div>

    <!-- BODY -->
    <div class="card-body">
        <p><span>Email:</span> <%=p.getEmail()%></p>
        <p><span>Mobile:</span> <%=p.getMobile_no()%></p>
    </div>

    <!-- ACTIONS -->
    <div class="actions">

        <% if("ACTIVE".equalsIgnoreCase(p.getStatus())){ %>

        <a class="btn warn"
        href="<%=ctx%>/AdminPatientServlet?action=deactivate&id=<%=p.getId()%>">
        Deactivate
        </a>

        <% } else { %>

        <a class="btn success"
        href="<%=ctx%>/AdminPatientServlet?action=activate&id=<%=p.getId()%>">
        Activate
        </a>

        <% } %>

        <a class="btn danger"
        onclick="return confirm('Delete this patient?')"
        href="<%=ctx%>/AdminPatientServlet?action=delete&id=<%=p.getId()%>">
        Delete
        </a>

    </div>

</div>

<% } %>

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