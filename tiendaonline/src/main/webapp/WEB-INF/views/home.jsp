<%-- 
    Document   : home
    Author     : s4aldiv
--%>

<%@page import="java.util.List"%>
<%@page import="py.edu.fpuna.par.tiendaonline.models.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inicio - Tienda Graciela</title>
</head>
<body>
    <header>
        <h1>Tienda Graciela</h1>
        <% if (request.getAttribute("username") != null) { %>
            <p>Bienvenido, <%= request.getAttribute("username") %></p>
        <% } %>
    </header>

    <h2>Búsqueda de productos</h2>
    <form action="${pageContext.request.contextPath}/home" method="get">
        <label for="category">Categoría:</label>
        <select id="category" name="category">
            <!-- Opciones de categorías -->
        </select>
        <label for="description">Descripción:</label>
        <input type="text" id="description" name="description">
        <button type="submit">Buscar</button>
    </form>

    <%-- Mostrar resultados de la búsqueda --%>
    <% if (request.getAttribute("products") != null) { %>
        <h3>Resultados de búsqueda:</h3>
        <ul>
            <% for (Product product : (List<Product>) request.getAttribute("products")) { %>
                <li><%= product.getName() %></li>
            <% } %>
        </ul>
    <% } %>
</body>
</html>
