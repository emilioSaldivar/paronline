package py.edu.fpuna.par.parusrmcs.service;

import java.util.List;
import py.edu.fpuna.par.parusrmcs.dao.UserDAO;
import py.edu.fpuna.par.parusrmcs.model.User;
import py.edu.fpuna.par.parusrmcs.util.PasswordUtils;
import py.edu.fpuna.par.parusrmcs.util.ValidationUtils;

public class UserService {
    
    private final UserDAO userDAO;
    
    public UserService() {
        this.userDAO = new UserDAO();
    }
    
    public List<User> obtenerTodosLosUsuarios() {
        return userDAO.getAllUsers();
    }
    
    public User obtenerUsuarioPorId(int id) {
        return userDAO.getUserById(id);
    }
    
    public User obtenerUsuarioPorNombreDeInicio(String nombreInicio) {
        return userDAO.obtenerUsuarioPorNombreDeInicio(nombreInicio);
    }
    
    public boolean agregarUsuario(User usuario) throws Exception {
        validarUsuario(usuario);
        usuario.setPassword(PasswordUtils.encryptPassword(usuario.getPassword()));
        return userDAO.add(usuario);
    }

    
    public void actualizarUsuario(User usuario) {
        validarUsuario(usuario);
        usuario.setPassword(PasswordUtils.encryptPassword(usuario.getPassword()));
        userDAO.updateUser(usuario);
    }
    
    public boolean eliminarUsuario(int id) throws Exception {
        return userDAO.deleteUser(id);
    }
    
    private void validarUsuario(User usuario) {
        if (ValidationUtils.isEmpty(usuario.getNombre()) || ValidationUtils.isEmpty(usuario.getApellido()) ||
                ValidationUtils.isEmpty(usuario.getEmail()) || ValidationUtils.isEmpty(usuario.getlogin_name()) ||
                ValidationUtils.isEmpty(usuario.getPassword())) {
            throw new IllegalArgumentException("Todos los campos son requeridos.");
        }
        
        if (!ValidationUtils.isValidEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El email no es válido.");
        }
    }
}
