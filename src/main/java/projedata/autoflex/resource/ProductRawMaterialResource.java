package projedata.autoflex.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import projedata.autoflex.dto.ProductRawMaterialDTO;
import projedata.autoflex.service.ProductRawMaterialService;

@Path("api/autoflex/v1/product-raw-material")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductRawMaterialResource {

  @Inject()
  ProductRawMaterialService productRawMaterialService;

  @POST
  public Response create(@Valid ProductRawMaterialDTO productRawMaterialDTO) {
    var productRawMaterial = productRawMaterialService.create(productRawMaterialDTO);
    return productRawMaterial;
  }
}
