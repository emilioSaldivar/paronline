<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inicio - Tienda Graciela</title>
</head>
<body>
    <header>
        <h1>Tienda Graciela</h1>
        <p id="welcomeMessage"></p>
    </header>

    <h2>B�squeda de productos</h2>
    <form action="search" method="get">
        <label for="category">Categor�a:</label>
        <select id="category" name="category">
            <!-- Opciones de categor�as -->
        </select>
        <label for="description">Descripci�n:</label>
        <input type="text" id="description" name="description">
        <button type="submit">Buscar</button>
    </form>

    <h3>Resultados de b�squeda:</h3>
    <ul id="searchResults">
        <!-- Resultados de b�squeda -->
    </ul>

    <script src="menu.js"></script>
</body>
</html>
