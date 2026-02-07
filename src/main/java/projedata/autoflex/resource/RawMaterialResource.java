package projedata.autoflex.resource;

import projedata.autoflex.dto.RawMaterialDTO;
import projedata.autoflex.entity.RawMaterialEntity;
import projedata.autoflex.service.RawMaterialService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.UUID;

import java.util.List;

@Path("/raw-materials")
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
        RawMaterialEntity material = rawMaterialService.findById(id);

        if (material == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(material).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, RawMaterialEntity material) {
        RawMaterialEntity updated = rawMaterialService.update(id, material);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {
        rawMaterialService.delete(id);
        return Response.noContent().build();
    }
}
