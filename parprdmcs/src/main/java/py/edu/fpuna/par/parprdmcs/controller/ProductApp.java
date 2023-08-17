package py.edu.fpuna.par.parprdmcs.controller;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author s4ldiv
 */
public class ProductApp extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public ProductApp() {
        singletons.add(new ProductRestService());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}