package py.edu.fpuna.par.tiendaonline.services;


public class ServiceFactory {

    private static ProductService productService;

    private ServiceFactory() {
    }

    public static ProductService getProductService() {
        if (productService == null) {
            productService = new ProductService();
        }
        return productService;
    }


}
