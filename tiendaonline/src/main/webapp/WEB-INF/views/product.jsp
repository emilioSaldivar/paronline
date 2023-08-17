<%-- 
    Document   : product
    Author     : s4aldiv
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Productos</title>
</head>
<body>
    <h2>Lista de Productos</h2>
    
    <%-- Mostrar mensajes de éxito o error si existen --%>
    <% if (request.getAttribute("successMessage") != null) { %>
        <p style="color: green;"><%= request.getAttribute("successMessage") %></p>
    <% } %>
    
    <% if (request.getAttribute("errorMessage") != null) { %>
        <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
    <% } %>
    
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Categoría</th>
            <th>Cantidad</th>
            <th>Precio</th>
            <th>Acciones</th>
        </tr>
        <%-- Iterar sobre la lista de productos y mostrar los datos --%>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.categoryId}</td>
                <td>${product.quantity}</td>
                <td>${product.price}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/products?action=edit&id=${product.id}">Editar</a>
                    <a href="${pageContext.request.contextPath}/products?action=delete&id=${product.id}">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    
    <%-- Formulario para crear un nuevo producto --%>
    <h3>Crear Producto</h3>
    <form action="${pageContext.request.contextPath}/products" method="post">
        <input type="hidden" name="action" value="create">
        <label>ID: </label><input type="text" name="productId"><br>
        <label>Nombre: </label><input type="text" name="name"><br>
        <label>Descripción: </label><input type="text" name="description"><br>
        <label>Categoría: </label><input type="text" name="categoryId"><br>
        <label>Cantidad: </label><input type="text" name="quantity"><br>
        <label>Precio: </label><input type="text" name="price"><br>
        <input type="submit" value="Crear Producto">
    </form>
</body>
</html>
