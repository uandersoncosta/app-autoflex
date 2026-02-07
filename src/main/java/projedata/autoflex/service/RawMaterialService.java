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

    public List<RawMaterialEntity> findAll() {
        return rawmaterialRepository.listAll();
    }

    public RawMaterialEntity findById(UUID id) {
        return rawmaterialRepository.findById(id);
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
