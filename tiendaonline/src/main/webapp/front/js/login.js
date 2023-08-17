document.getElementById("loginForm").addEventListener("submit", function(event) {
  event.preventDefault(); // Evitar el envío del formulario

  // Obtener los valores de usuario y contraseña
  var username = document.getElementById("username").value;
  var password = document.getElementById("password").value;

  // Validar el usuario y la contraseña (puedes agregar más validaciones si es necesario)
  if (username.trim() === "" || password.trim() === "") {
    showError("Se requiere el nombre de usuario y la contraseña.");
    return;
  }

  // Crear un objeto con las credenciales del usuario
  var user = {
    username: username,
    password: password
  };

  // Realizar una solicitud AJAX al endpoint de inicio de sesión
  var xhr = new XMLHttpRequest();
  xhr.open("POST", "http://your-api-domain.com/login", true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onload = function() {
    if (xhr.status === 200) {
      // Inicio de sesión exitoso, redirigir a la página principal
      window.location.href = "main.html";
    } else {
      // Inicio de sesión fallido, mostrar mensaje de error
      showError("Nombre de usuario o contraseña inválidos.");
    }
  };
  xhr.onerror = function() {
    // Ocurrió un error durante la solicitud AJAX
    showError("Ocurrió un error. Por favor, intenta nuevamente más tarde.");
  };
  xhr.send(JSON.stringify(user));
});

function showError(message) {
  document.getElementById("errorMessage").textContent = message;
}
