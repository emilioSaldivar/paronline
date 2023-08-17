<%-- 
    Document   : login
    Author     : s4aldiv
--%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar sesi�n</title>
</head>
<body>
    <h1>Iniciar sesi�n</h1>
    
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    
    <form method="post" action="${pageContext.request.contextPath}/login">
        <label for="username">Nombre de usuario:</label>
        <input type="text" id="username" name="username" required><br>
        
        <label for="password">Contrase�a:</label>
        <input type="password" id="password" name="password" required><br>
        
        <input type="submit" value="Iniciar sesi�n">
    </form>
</body>
</html>

