package py.edu.fpuna.par.tiendaonline.controllers;

import py.edu.fpuna.par.tiendaonline.models.User;
import py.edu.fpuna.par.tiendaonline.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void getAllUsers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/user.jsp").forward(request, response);
    }

    public void getUserById(HttpServletRequest request, HttpServletResponse response, int id) throws IOException, ServletException, Exception {
        User user = userService.getUserById(id);
        if (user != null) {
            request.setAttribute("user", user);
        } else {
            request.setAttribute("error", "User not found");
        }
        request.getRequestDispatcher("/user.jsp").forward(request, response);
    }

    public void createUser(HttpServletRequest request, HttpServletResponse response, User user) throws IOException, Exception {
        userService.insertUser(user);
        response.sendRedirect(request.getContextPath() + "/users");
    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response, int id, User user) throws IOException, Exception {
        user.setId(id);
        userService.updateUser(user);
        response.sendRedirect(request.getContextPath() + "/users");
    }

    public void deleteUser(HttpServletRequest request, HttpServletResponse response, int id) throws IOException, Exception {
        userService.deleteUser(id);
        response.sendRedirect(request.getContextPath() + "/users");
    }

}
