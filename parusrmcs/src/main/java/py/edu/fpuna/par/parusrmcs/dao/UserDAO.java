package py.edu.fpuna.par.parusrmcs.dao;


import py.edu.fpuna.par.parusrmcs.model.User;
import py.edu.fpuna.par.parusrmcs.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {

    private final String SELECT_ALL_USERS = "SELECT * FROM clientes WHERE tipo_cliente = 1";
    private final String SELECT_USER_BY_ID = "SELECT * FROM clientes WHERE id_cliente = ?";
    private final String INSERT_USER = "INSERT INTO public.clientes(nombre, apellido, email, login_name, passwd) VALUES (?,?,?,?,?)";
    private final String UPDATE_USER = "UPDATE clientes SET nombre = ?, apellido = ?, email = ?, login_name = ?, passwd = ? WHERE id_cliente = ?";
    private final String DELETE_USER = "DELETE FROM public.clientes WHERE id_cliente = ?";


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            stmt = conn.prepareStatement(SELECT_ALL_USERS);
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id_cliente"));
                user.setName(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setEmail(rs.getString("email"));
                user.setlogin_name(rs.getString("login_name"));
                user.setPassword(rs.getString("passwd"));

                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeResultSet(rs);
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(conn);
        }

        return users;
    }


    public User getUserById(Integer userId) {
        User user = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            stmt = conn.prepareStatement(SELECT_USER_BY_ID);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id_cliente"));
                user.setName(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setEmail(rs.getString("email"));
                user.setlogin_name(rs.getString("login_name"));
                user.setPassword(rs.getString("passwd"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeResultSet(rs);
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(conn);
        }

        return user;
    }


    public boolean add(User user) throws Exception{
        
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellido());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getlogin_name());
            statement.setString(5, user.getPassword());
            
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }



    public boolean updateUser(User user) {
        boolean success = false;

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBUtils.getConnection();
            stmt = conn.prepareStatement(UPDATE_USER);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getApellido());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getlogin_name());
            stmt.setString(5, user.getPassword());
            stmt.setInt(6, user.getId());

            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(conn);
        }

        return success;
    }

    public boolean deleteUser(Integer userId) throws Exception {
        boolean success = false;

        try (Connection conn = DBUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_USER)) {
            stmt.setInt(1, userId);

            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return success;
    }


    public User obtenerUsuarioPorNombreDeInicio(String nombreInicio) {
        User user = null;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM clientes WHERE login_name LIKE ?");
            stmt.setString(1, nombreInicio);
            rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id_cliente"));
                user.setName(rs.getString("nombre"));
                user.setApellido(rs.getString("apellido"));
                user.setEmail(rs.getString("email"));
                user.setlogin_name(rs.getString("login_name"));
                user.setPassword(rs.getString("passwd"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeResultSet(rs);
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(conn);
        }

        return user;
    }

}
