package projedata.autoflex.service;

import projedata.autoflex.dto.RawMaterialDTO;
import projedata.autoflex.entity.RawMaterialEntity;
import projedata.autoflex.repository.IRawMaterialRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

import java.util.List;
import java.util.Map;

@ApplicationScoped
public class RawMaterialService {

  @Inject
  IRawMaterialRepository rawmaterialRepository;

  @Transactional
  public Response create(RawMaterialDTO rawmaterialDTO) {
    RawMaterialEntity rawMaterialEntity = new RawMaterialEntity();

    rawMaterialEntity.name = rawmaterialDTO.name;
    rawMaterialEntity.stockQuantity = rawmaterialDTO.stockQuantity;

    rawmaterialRepository.persist(rawMaterialEntity);
    return Response.status(Response.Status.CREATED).entity(rawMaterialEntity).build();
  }

  public Response findAll() {
    List<RawMaterialEntity> rawMaterial = rawmaterialRepository.listAll();
    return Response.ok(rawMaterial).build();
  }

  public Response findById(UUID id) {
    RawMaterialEntity rawMaterial = rawmaterialRepository.findById(id);

    if (rawMaterial == null) {
      return Response.status(Response.Status.NOT_FOUND)
          .entity(Map.of(
              "status", 404,
              "erro", "Material não encontrado"))
          .build();
    }

    return Response.ok(rawMaterial).build();
  }

  @Transactional
  public RawMaterialEntity update(UUID id, RawMaterialEntity data) {
    RawMaterialEntity material = rawmaterialRepository.findById(id);

    if (material == null)
      return null;

    material.name = data.name;
    material.stockQuantity = data.stockQuantity;

    return material;
  }

  @Transactional
  public void delete(UUID id) {
    rawmaterialRepository.deleteById(id);
  }
}
