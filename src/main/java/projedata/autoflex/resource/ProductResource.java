package projedata.autoflex.resource;

import projedata.autoflex.dto.ProductDTO;
import projedata.autoflex.service.ProductService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.UUID;

@Path("api/autoflex/v1/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

  @Inject
  ProductService service;

  @POST
  public Response create(@Valid ProductDTO productdto) {
    var product = service.create(productdto);
    return product;
  }

  @GET
  public Response findAll() {
    var products = service.findAll();
    return products;
  }

  @GET
  @Path("/{id}")
  public Response findById(@PathParam("id") UUID id) {
    var product = service.findById(id);
    return product;
  }

  @PUT
  @Path("/{id}")
  public Response update(@PathParam("id") UUID id, @Valid ProductDTO productDTO) {
    var product = service.update(id, productDTO);
    return product;
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") UUID id) {
    var product = service.delete(id);
    return product;
  }
}
