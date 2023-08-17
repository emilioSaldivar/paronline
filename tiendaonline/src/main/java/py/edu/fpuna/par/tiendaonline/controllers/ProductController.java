package py.edu.fpuna.par.tiendaonline.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import py.edu.fpuna.par.tiendaonline.models.Product;
import py.edu.fpuna.par.tiendaonline.services.ProductService;

public class ProductController extends HttpServlet {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            // Mostrar la lista de productos
            List<Product> products = productService.getAllProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("/WEB-INF/views/product.jsp").forward(request, response);
        } else if (action.equals("edit")) {
            // Mostrar el formulario de edición de un producto
            int productId = Integer.parseInt(request.getParameter("id"));
            Product product = productService.getProductById(productId);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/WEB-INF/views/editProduct.jsp").forward(request, response);
        }
        // Agregar el código necesario para las demás acciones (crear, actualizar, eliminar)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                {
                    // Crear un nuevo producto
                    Product product = getProductFromRequest(request);
                    productService.addProduct(product);
                    response.sendRedirect(request.getContextPath() + "/products");
                    break;
                }
            case "update":
                {
                    // Actualizar un producto existente
                    Product product = getProductFromRequest(request);
                    productService.updateProduct(product);
                    response.sendRedirect(request.getContextPath() + "/products");
                    break;
                }
            case "delete":
                // Eliminar un producto
                int productId = Integer.parseInt(request.getParameter("id"));
                productService.deleteProduct(productId);
                response.sendRedirect(request.getContextPath() + "/products");
                break;
            default:
                break;
        }
    }

    private Product getProductFromRequest(HttpServletRequest request) {
        // Obtener los datos del producto del formulario
        int productId = Integer.parseInt(request.getParameter("productId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        // Obtener los demás datos del formulario según corresponda

        // Crear una instancia de Product y asignar los datos
        Product product = new Product(productId, name);
        product.setDescription(description);
        product.setCategoryId(categoryId);
        product.setQuantity(quantity);
        // Asignar los demás datos según corresponda

        return product;
    }
}
