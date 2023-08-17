package py.edu.fpuna.par.tiendaonline.services;


import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import py.edu.fpuna.par.tiendaonline.models.User;

public class UserService {
    private final CloseableHttpClient httpClient;

    public UserService() {
        httpClient = HttpClients.createDefault();
    }

    public List<User> getAllUsers() throws Exception {
        HttpGet request = new HttpGet("http://localhost:8080/parusrmcs-1.0-SNAPSHOT/rest/usrmcs/users/");
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String responseBody = EntityUtils.toString(entity);

        List<User> users = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(responseBody);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            User user = new User();
            user.setId(jsonObject.getInt("id"));
            user.setName(jsonObject.getString("name"));
            user.setApellido(jsonObject.getString("apellido"));
            user.setEmail(jsonObject.getString("email"));
            user.setlogin_name(jsonObject.getString("login_name"));
            user.setPassword(jsonObject.getString("password"));
            users.add(user);
        }

        return users;
    }

    public User getUserById(int id) throws Exception {
        HttpGet request = new HttpGet("http://localhost:8080/parusrmcs-1.0-SNAPSHOT/rest/usrmcs/users/" + id);
        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String responseBody = EntityUtils.toString(entity);

        JSONObject jsonObject = new JSONObject(responseBody);
        User user = new User();
        user.setId(jsonObject.getInt("id"));
        user.setName(jsonObject.getString("name"));
        user.setApellido(jsonObject.getString("apellido"));
        user.setEmail(jsonObject.getString("email"));
        user.setlogin_name(jsonObject.getString("login_name"));
        user.setPassword(jsonObject.getString("password"));

        return user;
    }

    public User insertUser(User user) throws Exception {
        HttpPost request = new HttpPost("http://localhost:8080/parusrmcs-1.0-SNAPSHOT/rest/usrmcs/users");
        JSONObject json = new JSONObject();
        json.put("nombre", user.getName());
        json.put("apellido", user.getApellido());
        json.put("email", user.getEmail());
        json.put("login_name", user.getlogin_name());
        json.put("password", user.getPassword());
        StringEntity requestBody = new StringEntity(json.toString());
        request.setEntity(requestBody);
        request.setHeader("Content-Type", "application/json");

        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String responseBody = EntityUtils.toString(entity);

        JSONObject jsonObject = new JSONObject(responseBody);
        User insertedUser = new User();
        insertedUser.setId(jsonObject.getInt("id"));
        insertedUser.setName(jsonObject.getString("name"));
        insertedUser.setApellido(jsonObject.getString("apellido"));
        insertedUser.setEmail(jsonObject.getString("email"));
        insertedUser.setlogin_name(jsonObject.getString("login_name"));
        insertedUser.setPassword("********");

        return insertedUser;
    }

    public User updateUser(User user) throws Exception {
        HttpPut request = new HttpPut("http://localhost:8080/parusrmcs-1.0-SNAPSHOT/rest/usrmcs/users/" + user.getId());
        JSONObject json = new JSONObject();
        json.put("nombre", user.getName());
        json.put("apellido", user.getApellido());
        json.put("email", user.getEmail());
        json.put("login_name", user.getlogin_name());
        json.put("password", user.getPassword());
        StringEntity requestBody = new StringEntity(json.toString());
        request.setEntity(requestBody);
        request.setHeader("Content-Type", "application/json");

        HttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String responseBody = EntityUtils.toString(entity);

        JSONObject jsonObject = new JSONObject(responseBody);
        User updatedUser = new User();
        updatedUser.setId(jsonObject.getInt("id"));
        updatedUser.setName(jsonObject.getString("name"));
        updatedUser.setApellido(jsonObject.getString("apellido"));
        updatedUser.setEmail(jsonObject.getString("email"));
        updatedUser.setlogin_name(jsonObject.getString("login_name"));
        updatedUser.setPassword("********");

        return updatedUser;
    }

    public void deleteUser(int id) throws Exception {
        HttpDelete request = new HttpDelete("http://localhost:8080/parusrmcs-1.0-SNAPSHOT/rest/usrmcs/users/" + id);
        HttpResponse response = httpClient.execute(request);
        response.getEntity().getContent().close();
    }
}
