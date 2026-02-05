package projedata.autoflex.resource;

import projedata.autoflex.entity.ProductEntity;
import projedata.autoflex.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService service;

    // CREATE
    @POST
    public Response create(ProductEntity product) {
        ProductEntity created = service.create(product);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    // READ ALL
    @GET
    public List<ProductEntity> findAll() {
        return service.findAll();
    }

    // READ BY ID
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        ProductEntity product = service.findById(id);

        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(product).build();
    }

    // UPDATE
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ProductEntity product) {
        ProductEntity updated = service.update(id, product);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(updated).build();
    }

    // DELETE
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
