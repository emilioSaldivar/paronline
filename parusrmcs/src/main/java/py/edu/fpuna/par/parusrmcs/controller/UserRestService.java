package py.edu.fpuna.par.parusrmcs.controller;


import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import py.edu.fpuna.par.parusrmcs.model.LoginRequest;
import py.edu.fpuna.par.parusrmcs.model.User;
import py.edu.fpuna.par.parusrmcs.service.UserService;

@Path("/usrmcs")
public class UserRestService {

    private final UserService userService = new UserService();

    @GET
    @Path("/users")
    @Produces("application/json")
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            users = (ArrayList<User>) userService.obtenerTodosLosUsuarios();
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @GET
    @Path("/users/{id}")
    @Produces("application/json")
    public User getUser(@PathParam("id") Integer id) {
        User entity = null;
        try {
            entity = userService.obtenerUsuarioPorId(id);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }

    @POST
    @Path("/users")
    @Consumes("application/json")
    @Produces("application/json")
    public Response addUser(User entity) {
        try {
            boolean success = userService.agregarUsuario(entity);
            if (success) {
                return Response.status(Response.Status.OK).entity(new User  (0, entity.getNombre(), entity.getApellido(), entity.getEmail(), entity.getlogin_name(), "********")).build();
            } else {
                String message = "No se pudo insertar el usuario.";
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
            }
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
            String message = "Ocurrió un error al agregar el usuario. "+ex.getMessage();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
        }
    }


    @PUT
    @Path("/users")
    @Consumes("application/json")
    public void updateUser(User entity) {
        try {
            userService.actualizarUsuario(entity);
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("/users/{id}")
    public Response removeUser(@PathParam("id") Integer id) {
        try {
            boolean success = userService.eliminarUsuario(id);
            if (success) {
                return Response.status(Response.Status.OK).build();
            } else {
                String message = "No se pudo eliminar el usuario.";
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
            }
        } catch (Exception ex) {
            Logger.getLogger(UserRestService.class.getName()).log(Level.SEVERE, null, ex);
            String message = "Ocurrió un error al eliminar el usuario. " + ex.getMessage();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
        }
    }

    
    @POST
    @Path("/auth")
    @Consumes("application/json")
    @Produces("application/json")
    public User autenticarUsuario(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        //String password = loginRequest.getPassword();

        // Verificar las credenciales del usuario y autenticarlo

        // Si la autenticación es exitosa, crea un objeto User simulado como respuesta
        User user = new User();
        user.setlogin_name(username);
        user.setlogin_name(username);
        user.setNombre("John");
        user.setApellido("Doe");
        user.setEmail("john.doe@example.com");

        // Devolver el objeto User
        return user;
    }

}
