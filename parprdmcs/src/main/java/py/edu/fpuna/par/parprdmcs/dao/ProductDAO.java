package py.edu.fpuna.par.parprdmcs.dao;

import java.util.Collection;
import py.edu.fpuna.par.parprdmcs.model.Product;
import py.una.pol.par.commons.entity.Entity;

/**
 *
 * @author s4aldiv
 */
public interface ProductDAO {

    void add(Product product) throws Exception;

    void update(Product product, Integer id) throws Exception;

    void delete(Integer id) throws Exception;

    Entity findById(Integer id) throws Exception;

    Collection<Product> findByNameDescription(String name, String description) throws Exception;

    Collection<Product> getAll() throws Exception;
    
    Collection<Product> searchByCategoryAndDescription(String category, String description) throws Exception;

}
