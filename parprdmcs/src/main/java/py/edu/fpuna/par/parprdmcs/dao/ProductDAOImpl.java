package py.edu.fpuna.par.parprdmcs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import py.edu.fpuna.par.parprdmcs.model.Product;
import py.una.pol.par.commons.entity.Entity;
import py.edu.fpuna.par.parprdmcs.util.DBUtils;

public class ProductDAOImpl implements ProductDAO {

    private static final String INSERT_PRODUCT = "INSERT INTO productos (descripcion, id_categoria, precio_unit, cantidad, imagen) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE productos SET descripcion = ?, id_categoria = ?, precio_unit = ?, cantidad = ?, imagen = ? WHERE id_producto = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM productos WHERE id_producto = ?";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM productos WHERE id_producto = ?";
    private static final String SELECT_PRODUCTS_BY_NAME_DESCRIPTION = "SELECT * FROM productos WHERE descripcion ILIKE ? OR nombre ILIKE ?";
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM productos";

    @Override
    public void add(Product product) throws Exception {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCT)) {
            statement.setString(1, product.getDescription());
            statement.setInt(2, product.getCategoryId());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.setString(5, product.getImage());

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Error al agregar el producto", ex);
        }
    }

    @Override
    public void update(Product product, Integer id) throws Exception {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)) {
            statement.setString(1, product.getDescription());
            statement.setInt(2, product.getCategoryId());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.setString(5, product.getImage());
            statement.setInt(6, id);

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Error al actualizar el producto", ex);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT)) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Error al eliminar el producto", ex);
        }
    }

    @Override
    public Entity findById(Integer id) throws Exception {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = extractProductFromResultSet(resultSet);
                    return product;
                }
            }
        } catch (SQLException ex) {
            throw new Exception("Error al buscar el producto por ID", ex);
        }

        return null;
    }

    @Override
    public Collection<Product> findByNameDescription(String name, String description) throws Exception {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCTS_BY_NAME_DESCRIPTION)) {
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + description + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<Product> products = new ArrayList<>();
                while (resultSet.next()) {
                                        Product product = extractProductFromResultSet(resultSet);
                    products.add(product);
                }
                return products;
            }
        } catch (SQLException ex) {
            throw new Exception("Error al buscar productos por nombre y descripción", ex);
        }
    }

    @Override
    public Collection<Product> getAll() throws Exception {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
             ResultSet resultSet = statement.executeQuery()) {
            ArrayList<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            throw new Exception("Error al obtener todos los productos", ex);
        }
    }

    private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id_producto"));
        product.setDescription(resultSet.getString("descripcion"));
        product.setCategoryId(resultSet.getInt("id_categoria"));
        product.setPrice(resultSet.getBigDecimal("precio_unit"));
        product.setQuantity(resultSet.getInt("cantidad"));
        product.setImage(resultSet.getString("imagen"));
        return product;
    }
    
    @Override
    public Collection<Product> searchByCategoryAndDescription(String category, String description) throws Exception {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCTS_BY_NAME_DESCRIPTION)) {
            statement.setString(1, "%" + description + "%");
            statement.setString(2, "%" + description + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                ArrayList<Product> products = new ArrayList<>();
                while (resultSet.next()) {
                    Product product = extractProductFromResultSet(resultSet);
                    products.add(product);
                }
                return products;
            }
        } catch (SQLException ex) {
            throw new Exception("Error al buscar productos por categoría y descripción", ex);
        }
    }

}
