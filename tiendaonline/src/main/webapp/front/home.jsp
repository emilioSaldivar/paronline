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

    <h2>Búsqueda de productos</h2>
    <form action="search" method="get">
        <label for="category">Categoría:</label>
        <select id="category" name="category">
            <!-- Opciones de categorías -->
        </select>
        <label for="description">Descripción:</label>
        <input type="text" id="description" name="description">
        <button type="submit">Buscar</button>
    </form>

    <h3>Resultados de búsqueda:</h3>
    <ul id="searchResults">
        <!-- Resultados de búsqueda -->
    </ul>

    <script src="menu.js"></script>
</body>
</html>
