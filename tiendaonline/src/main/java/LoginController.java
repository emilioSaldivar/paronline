/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *
 * @author s4aldiv
 */

public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Realizar validación de autenticación utilizando MD5 para encriptar la contraseña
        String encryptedPassword = DigestUtils.md5Hex(password);

        // Lógica de validación de usuario y contraseña
        if (isValidUser(username, encryptedPassword)) {
            // Usuario válido, redirigir a la página principal
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            // Usuario no válido, mostrar mensaje de error en la página de inicio de sesión
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private boolean isValidUser(String username, String encryptedPassword) {
        // Lógica de validación de usuario y contraseña
        // realizar la consulta a tu servidor externo o base de datos para verificar la autenticidad del usuario y la contraseña encriptada
        // Retorna true si el usuario es válido, de lo contrario retorna false
        return false;
    }
}

