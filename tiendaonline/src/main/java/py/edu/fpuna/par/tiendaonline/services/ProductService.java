package py.edu.fpuna.par.tiendaonline.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import py.edu.fpuna.par.tiendaonline.models.Product;

public class ProductService {

    private static final String API_BASE_URL = "http://api.example.com/products";

    private final CloseableHttpClient httpClient;

    public ProductService() {
        httpClient = HttpClients.createDefault();
    }

    public List<Product> getAllProducts() {
        try {
            HttpGet request = new HttpGet(API_BASE_URL);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity);

            return parseProductJson(json);
        } catch (IOException | ParseException e) {
            return new ArrayList<>();
        }
    }

    public Product getProductById(int productId) {
        try {
            String url = API_BASE_URL + "/" + productId;
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity);

            JSONArray jsonArray = new JSONArray(json);
            if (jsonArray.length() > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                return parseProductFromJsonObject(jsonObject);
            }

            return null;
        } catch (IOException | ParseException | JSONException e) {
            return null;
        }
    }

    public void addProduct(Product product) {
        try {
            JSONObject jsonObject = createJsonObjectFromProduct(product);
            StringEntity entity = new StringEntity(jsonObject.toString());

            HttpPost request = new HttpPost(API_BASE_URL);
            request.setHeader("Content-type", "application/json");
            request.setEntity(entity);

            httpClient.execute(request);
        } catch (IOException e) {
        }
    }

    public void updateProduct(Product product) {
        try {
            String url = API_BASE_URL + "/" + product.getId();
            JSONObject jsonObject = createJsonObjectFromProduct(product);
            StringEntity entity = new StringEntity(jsonObject.toString());

            HttpPut request = new HttpPut(url);
            request.setHeader("Content-type", "application/json");
            request.setEntity(entity);

            httpClient.execute(request);
        } catch (IOException e) {
        }
    }

    public void deleteProduct(int productId) {
        try {
            String url = API_BASE_URL + "/" + productId;
            HttpDelete request = new HttpDelete(url);
            httpClient.execute(request);
        } catch (IOException e) {
        }
    }

    public List<Product> parseProductJson(String json) {
        List<Product> products = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Product product = parseProductFromJsonObject(jsonObject);
            products.add(product);
        }

        return products;
    }

    private Product parseProductFromJsonObject(JSONObject jsonObject) {
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

        return product;
    }

    private JSONObject createJsonObjectFromProduct(Product product) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", product.getId());
        jsonObject.put("name", product.getName());
        jsonObject.put("description", product.getDescription());
        jsonObject.put("categoryId", product.getCategoryId());
        jsonObject.put("quantity", product.getQuantity());
        jsonObject.put("image", product.getImage());
        jsonObject.put("price", product.getPrice());

        return jsonObject;
    }
}
