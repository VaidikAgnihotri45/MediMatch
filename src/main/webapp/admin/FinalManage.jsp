<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    <%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");
response.setDateHeader("Expires", 0);

if(session.getAttribute("activeUserDTO") == null){
    response.sendRedirect("../auth/Login.jsp");
    return;
}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="UserServlet" method="get">
		<input type="hidden" name="task" value="updateById" />
		<table>
			<tr>
				<td><input type="submit" name="Find" value="Edit" /></td>
				
			</tr>
		</table>
	</form>
</body>
</html>