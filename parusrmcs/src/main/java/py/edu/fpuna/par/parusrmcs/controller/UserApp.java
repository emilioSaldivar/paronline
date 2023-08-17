package py.edu.fpuna.par.parusrmcs.controller;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author s4ldiv
 */
public class UserApp extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public UserApp() {
        singletons.add(new UserRestService());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}