package projedata.autoflex.service;

import projedata.autoflex.entity.RawMaterialEntity;
import projedata.autoflex.repository.IRawMaterialRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class RawMaterialService {

    @Inject
    IRawMaterialRepository rawmaterialrepository;

    @Transactional
    public RawMaterialEntity create(RawMaterialEntity material) {
        rawmaterialrepository.persist(material);
        return material;
    }

    public List<RawMaterialEntity> findAll() {
        return rawmaterialrepository.listAll();
    }

    public RawMaterialEntity findById(Long id) {
        return rawmaterialrepository.findById(id);
    }

    @Transactional
    public RawMaterialEntity update(Long id, RawMaterialEntity data) {
        RawMaterialEntity material = rawmaterialrepository.findById(id);

        if (material == null) return null;

        material.code = data.code;
        material.name = data.name;
        material.stockQuantity = data.stockQuantity;

        return material;
    }

    @Transactional
    public void delete(Long id) {
        rawmaterialrepository.deleteById(id);
    }
}
