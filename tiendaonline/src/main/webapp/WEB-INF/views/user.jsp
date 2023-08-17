<%-- 
    Document   : user
    Author     : s4aldiv
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuarios</title>
</head>
<body>
    <h2>Lista de Usuarios</h2>
    
    <%-- Mostrar mensaje de error si existe --%>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color: red;"><%= request.getAttribute("error") %></p>
    <% } %>
    
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Correo Electrónico</th>
        </tr>
        <%-- Iterar sobre la lista de usuarios y mostrar los datos --%>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
    </table>
    
    <%-- Formulario para crear un nuevo usuario --%>
    <h3>Crear Usuario</h3>
    <form action="${pageContext.request.contextPath}/users" method="post">
        <input type="hidden" name="action" value="create">
        <label>ID: </label><input type="text" name="userId"><br>
        <label>Nombre: </label><input type="text" name="name"><br>
        <label>Correo Electrónico: </label><input type="text" name="email"><br>
        <input type="submit" value="Crear Usuario">
    </form>
</body>
</html>
