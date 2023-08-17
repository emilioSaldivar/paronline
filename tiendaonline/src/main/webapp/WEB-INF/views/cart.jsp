<%-- 
    Document   : cart
    Author     : s4aldiv
--%>

<%@page import="py.edu.fpuna.par.tiendaonline.models.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Carrito de Compras</title>
</head>
<body>
    <h2>Carrito de Compras</h2>

    <%-- Mostrar productos en el carrito --%>
    <% if (cart != null && !cart.isEmpty()) { %>
    <table>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Cantidad</th>
            <th>Acciones</th>
        </tr>
        <% for (Product product : cart) {%>
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>
                <form action="${pageContext.request.contextPath}/CartController" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="index" value="${cart.indexOf(product)}">
                    <input type="number" name="quantity" value="${product.quantity}">
                    <input type="submit" value="Actualizar">
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/CartController" method="post">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="index" value="${cart.indexOf(product)}">
                    <input type="submit" value="Eliminar">
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    <% } else { %>
    <p>No hay productos en el carrito.</p>
    <% }%>

    <h3>Agregar Producto</h3>
    <form action="${pageContext.request.contextPath}/CartController" method="post">
        <input type="hidden" name="action" value="add">
        <label for="productId">ID del Producto:</label>
        <input type="number" id="productId" name="productId">
        <label for="quantity">Cantidad:</label>
        <input type="number" id="quantity" name="quantity" value="1">
        <button type="submit">Agregar al Carrito</button>
    </form>
</body>
</html>
