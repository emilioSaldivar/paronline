package py.edu.fpuna.par.tiendaonline.models;

import py.una.pol.par.commons.entity.BaseEntity;

public class User extends BaseEntity<Integer> {

    private String nombre;
    private String apellido;
    private String email;
    private String login_name;
    private String password;

    public User() {
        super(0, "");
    }

    public User(Integer id, String nombre, String apellido, String email, String login_name, String password) {
        super(id, nombre);
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.login_name = login_name;
        this.password = password;
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getlogin_name() {
        return login_name;
    }

    public void setlogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "id: " + this.getId() +
                ", nombre: " + this.nombre +
                ", apellido: " + apellido +
                ", email: " + email +
                ", login_name: " + login_name +
                ", password: " + password +
                "}";
    }
}
