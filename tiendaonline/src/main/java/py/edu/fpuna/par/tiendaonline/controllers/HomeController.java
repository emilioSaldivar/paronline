package py.edu.fpuna.par.tiendaonline.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import py.edu.fpuna.par.tiendaonline.models.Product;

public class HomeController extends HttpServlet {

    private static final String PRODUCT_SERVICE_URL = "http://localhost:8080/parprdmcs/rest/prodmcs/prod/search";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String companyInfo = "Tienda Graciela - Desde 1997 cuidando tu economía";
        String username = (String) request.getSession().getAttribute("username");

        request.setAttribute("companyInfo", companyInfo);
        request.setAttribute("username", username);

        String category = request.getParameter("category");
        String description = request.getParameter("description");
        if (category != null && description != null) {
            List<Product> products = performProductSearch(category, description);
            request.setAttribute("products", products);
        }

        request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
    }

    private List<Product> performProductSearch(String category, String description) throws IOException {
        URL url = new URL(PRODUCT_SERVICE_URL + "?category=" + category + "&description=" + description);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String responseBody = "";
            try (Scanner scanner = new Scanner(connection.getInputStream())) {
                while (scanner.hasNextLine()) {
                    responseBody += scanner.nextLine();
                }
            }

            List<Product> products = parseProductJson(responseBody);
            return products;
        } else {
            throw new IOException("Error in HTTP request. Response Code: " + responseCode);
        }
    }

    private List<Product> parseProductJson(String json) {
        List<Product> products = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");
            String description = jsonObject.getString("description");
            int categoryId = jsonObject.getInt("categoryId");
            int quantity = jsonObject.getInt("quantity");
            String image = jsonObject.getString("image");
            BigDecimal price = jsonObject.getBigDecimal("price");

            Product product = new Product(id, name);
            product.setDescription(description);
            product.setCategoryId(categoryId);
            product.setQuantity(quantity);
            product.setImage(image);
            product.setPrice(price);

            products.add(product);
        }

        return products;
    }
}
