package projedata.autoflex.resource;

import projedata.autoflex.entity.ProductEntity;
import projedata.autoflex.dto.ProductDTO;
import projedata.autoflex.service.ProductService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.UUID;

import java.util.List;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService service;

    @POST
    public Response create(@Valid ProductDTO productdto) {
        var created = service.create(productdto);
        return created;
    }

    // READ ALL
    @GET
    public Response findAll() {
        var products = service.findAll();
        return products;
    }

    // READ BY ID
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") UUID id) {
        var product = service.findById(id);

        return product;
    }

    // UPDATE
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, ProductEntity product) {
        ProductEntity updated = service.update(id, product);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(updated).build();
    }

    // DELETE
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
