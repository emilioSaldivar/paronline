package py.edu.fpuna.par.parprdmcs.service;

import java.util.Collection;
import py.edu.fpuna.par.parprdmcs.dao.ProductDAO;
import py.edu.fpuna.par.parprdmcs.dao.ProductDAOImpl;
import py.edu.fpuna.par.parprdmcs.model.Product;
import py.una.pol.par.commons.entity.Entity;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl() {
        this.productDAO = new ProductDAOImpl();
    }

    @Override
    public void add(Product product) throws Exception {
        productDAO.add(product);
    }

    @Override
    public void update(Product product, Integer id) throws Exception {
        productDAO.update(product, id);
    }

    @Override
    public void delete(Integer id) throws Exception {
        productDAO.delete(id);
    }

    @Override
    public Entity findById(Integer id) throws Exception {
        return productDAO.findById(id);
    }

    @Override
    public Collection<Product> findByNameDescription(String name, String description) throws Exception {
        return productDAO.findByNameDescription(name, description);
    }

    @Override
    public Collection<Product> getAll() throws Exception {
        return productDAO.getAll();
    }

    @Override
    public Collection<Product> searchByCategoryAndDescription(String category, String description) throws Exception {
        try {
            // Validar los parámetros de búsqueda
            if (category == null && description == null) {
                throw new IllegalArgumentException("Debe proporcionar al menos una categoría o una descripción para realizar la búsqueda");
            }

            // Obtener la lista de productos según los criterios de búsqueda
            Collection<Product> products = productDAO.searchByCategoryAndDescription(category, description);

            return products;
        } catch (Exception ex) {
            throw new Exception("Error al buscar productos por categoría y descripción", ex);
        }
    }


}
