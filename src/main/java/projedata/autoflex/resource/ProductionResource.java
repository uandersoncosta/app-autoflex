package projedata.autoflex.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import projedata.autoflex.service.ProductionService;

@Path("api/autoflex/v1/production")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductionResource {

  @Inject
  ProductionService productionService;

  @GET
  @Path("/suggestions")
  public Response getSuggestions() {
    return Response.ok(productionService.getProductionSuggestions()).build();
  }
}
