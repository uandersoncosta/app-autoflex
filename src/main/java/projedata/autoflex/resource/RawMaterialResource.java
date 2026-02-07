package projedata.autoflex.resource;

import projedata.autoflex.entity.RawMaterialEntity;
import projedata.autoflex.service.RawMaterialService;
import jakarta.inject.Inject;
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
    RawMaterialService service;

    @POST
    public Response create(RawMaterialEntity material) {
        RawMaterialEntity created = service.create(material);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public List<RawMaterialEntity> findAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") UUID id) {
        RawMaterialEntity material = service.findById(id);

        if (material == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(material).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, RawMaterialEntity material) {
        RawMaterialEntity updated = service.update(id, material);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
