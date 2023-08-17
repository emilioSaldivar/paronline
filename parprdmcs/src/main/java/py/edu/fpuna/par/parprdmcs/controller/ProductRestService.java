package py.edu.fpuna.par.parprdmcs.controller;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import py.edu.fpuna.par.parprdmcs.model.Product;
import py.edu.fpuna.par.parprdmcs.service.ProductService;
import py.edu.fpuna.par.parprdmcs.service.ProductServiceImpl;

/**
 *
 * @author s4ldiv
 */
@Path("/prodmcs")
public class ProductRestService {

    private final ProductService productService = new ProductServiceImpl();

    @GET
    @Path("/prod")
    @Produces("application/json")
    public ArrayList<Product> getProducts() throws Exception {
        ArrayList<Product> prod = (ArrayList) productService.getAll();
        return prod;
    }

    @GET
    @Path("/prod/{id}")
    @Produces("application/json")
    public Product getProduct(@PathParam("id") Integer id) {
        Product entity = null;
        try {
            entity = (Product) productService.findById(id);
        } catch (Exception ex) {
            Logger.getLogger(ProductRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }

    @POST
    @Path("/prod")
    @Consumes("application/json")
    @Produces("application/json")
    public Product addProduct(Product entity) {
        try {
            productService.add(entity);
        } catch (Exception ex) {
            Logger.getLogger(ProductRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }

    @PUT
    @Path("/prod/{id}")
    @Consumes("application/json")
    public void updateProduct(Product entity, @PathParam("id") Integer id) {
        try {
            productService.update(entity, id);
        } catch (Exception ex) {
            Logger.getLogger(ProductRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("/prod/{id}")
    public void removeProduct(@PathParam("id") Integer id) {
        try {
            productService.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(ProductRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @GET
    @Path("/prod/search")
    @Produces("application/json")
    public Response searchProducts(@QueryParam("category") String category,
                                   @QueryParam("description") String description) {
        try {
            ArrayList<Product> prod = (ArrayList) productService.searchByCategoryAndDescription(category, description);
            return Response.ok(prod).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al buscar productos").build();
        }
    }

}
