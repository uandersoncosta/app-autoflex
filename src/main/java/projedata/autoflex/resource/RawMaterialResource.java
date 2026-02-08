package projedata.autoflex.resource;

import projedata.autoflex.dto.RawMaterialDTO;
import projedata.autoflex.service.RawMaterialService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.UUID;

@Path("/api/autoflex/v1/raw-materials")
@PermitAll
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RawMaterialResource {

    @Inject
    RawMaterialService rawMaterialService;

    @POST
    public Response create(@Valid RawMaterialDTO rawmaterialDTO) {
        var rawmaterial = rawMaterialService.create(rawmaterialDTO);
        return rawmaterial;
    }

    @GET
    public Response findAll() {
        var rawMaterial = rawMaterialService.findAll();
        return rawMaterial;
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") UUID id) {
        var rawMaterial = rawMaterialService.findById(id);
        return Response.ok(rawMaterial).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, @Valid RawMaterialDTO rawMaterialDTO) {
        var rawMaterial = rawMaterialService.update(id, rawMaterialDTO);
        return rawMaterial;
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {
        var rawMaterial = rawMaterialService.delete(id);
        return rawMaterial;
    }
}
