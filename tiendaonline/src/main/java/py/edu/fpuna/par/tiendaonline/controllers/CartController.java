/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package py.edu.fpuna.par.tiendaonline.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import py.edu.fpuna.par.tiendaonline.models.Product;

public class CartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener carrito de la sesión
        List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");

        // Verificar si el carrito existe en la sesión
        if (cart == null) {
            // Si no existe, crear un nuevo carrito vacío
            cart = new ArrayList<>();
            request.getSession().setAttribute("cart", cart);
        }

        // Pasar carrito a la vista
        request.setAttribute("cart", cart);

        // Redireccionar a la vista cart.jsp
        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener acción del formulario
        String action = request.getParameter("action");
        
        // Obtener carrito de la sesión
        List<Product> cart = (List<Product>) request.getSession().getAttribute("cart");
        
        // Verificar si el carrito existe en la sesión
        if (cart == null) {
            // Si no existe, crear un nuevo carrito vacío
            cart = new ArrayList<>();
            request.getSession().setAttribute("cart", cart);
        }
        
        // Realizar la acción correspondiente
        switch (action) {
            case "add":
                {
                    // Obtener información del producto a agregar desde los parámetros del formulario
                    int productId = Integer.parseInt(request.getParameter("productId"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    // Crear un nuevo producto y agregarlo al carrito
                    Product product = new Product();
                    product.setId(productId);
                    product.setQuantity(quantity);
                    cart.add(product);
                    break;
                }
            case "update":
                {
                    // Obtener información del producto a actualizar desde los parámetros del formulario
                    int index = Integer.parseInt(request.getParameter("index"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    // Actualizar la cantidad del producto en el carrito
                    if (index >= 0 && index < cart.size()) {
                        Product product = cart.get(index);
                        product.setQuantity(quantity);
                    }       break;
                }
            case "remove":
                {
                    // Obtener índice del producto a eliminar desde los parámetros del formulario
                    int index = Integer.parseInt(request.getParameter("index"));
                    // Eliminar el producto del carrito
                    if (index >= 0 && index < cart.size()) {
                        cart.remove(index);
                    }       break;
                }
            default:
                break;
        }
        
        // Redireccionar a la página del carrito
        response.sendRedirect(request.getContextPath() + "/CartController");
    }
}
