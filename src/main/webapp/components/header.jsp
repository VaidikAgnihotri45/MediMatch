<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.amstech.hms.model.UserDTO"%>

<%
UserDTO activeUserDTO = (UserDTO) session.getAttribute("activeUserDTO");
%>

<header class="navbar">

    <div class="nav-container">

        <!-- LEFT: LOGO -->
        <div class="logo">
            <h1>NewLife</h1>
            <span>Hospital</span>
        </div>

        <!-- CENTER: SEARCH -->
        <form action="<%=request.getContextPath()%>/DoctorServlet" method="get" class="header-search">
            <input type="hidden" name="task" value="findAll">
            <input type="text" name="search" placeholder="Search doctors..." class="search-input">
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>

        <!-- RIGHT: NAV -->
        <nav>
            <ul>

                <li><a href="${pageContext.request.contextPath}/public/HomePage.jsp">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/public/About.jsp">About</a></li>
                <li><a href="${pageContext.request.contextPath}/public/Service.jsp">Services</a></li>
                <li><a href="${pageContext.request.contextPath}/public/Department.jsp">Departments</a></li>
                <li><a href="${pageContext.request.contextPath}/public/Contact.jsp">Contact</a></li>

                <% if (activeUserDTO == null) { %>
                    <li><a href="${pageContext.request.contextPath}/auth/Login.jsp">Login</a></li>
                    <li><a href="${pageContext.request.contextPath}/auth/Signup.jsp">Signup</a></li>
                <% } else { %>

                    <% if (activeUserDTO.getRole_id() == 2) { %>
                        <li><a href="<%=request.getContextPath()%>/DoctorServlet?page=dashboard">Doctor</a></li>
                    <% } %>

                    <% if (activeUserDTO.getRole_id() == 3) { %>
                        <li><a href="<%=request.getContextPath()%>/AdminAppointmentServlet?page=dashboard">Admin</a></li>
                    <% } %>



                    <%-- <li class="dropdown">
                        <a href="#">👤 <%=activeUserDTO.getFirst_name()%> ▼</a>
                        <ul class="dropdown-menu">
                        
                            <li><a href="#">My Profile</a></li>
                            <li><a href="<%=request.getContextPath()%>/UserServlet?task=logout">Logout</a></li>
                        </ul>
                    </li>

                <% } %>

            </ul>
        </nav>

    </div>

</header> --%>


<!-- 👤 ACCOUNT -->
<li class="dropdown">

<a href="#">👤 <%=activeUserDTO.getFirst_name()%> ▼</a>

<ul class="dropdown-menu">

<li>
<a href="${pageContext.request.contextPath}/admin/pati.jsp">My Profile</a>
</li>

<li>
<a href="<%=request.getContextPath()%>/UserServlet?task=findById&userId=<%=activeUserDTO.getId()%>">
Edit Profile
</a>
</li>

<li>
<a href="<%=request.getContextPath()%>/UserServlet?task=logout">
Logout
</a>
</li>

</ul>

</li>

<%
}
%>

</ul>
</nav>

</header>



