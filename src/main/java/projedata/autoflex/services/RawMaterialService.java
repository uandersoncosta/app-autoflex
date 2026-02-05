package projedata.autoflex.service;

import projedata.autoflex.entity.RawMaterialEntity;
import projedata.autoflex.repository.RawMaterialRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class RawMaterialService {

    @Inject
    RawMaterialRepository repository;

    @Transactional
    public RawMaterialEntity create(RawMaterialEntity material) {
        repository.persist(material);
        return material;
    }

    public List<RawMaterialEntity> findAll() {
        return repository.listAll();
    }

    public RawMaterialEntity findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public RawMaterialEntity update(Long id, RawMaterialEntity data) {
        RawMaterialEntity material = repository.findById(id);

        if (material == null) return null;

        material.code = data.code;
        material.name = data.name;
        material.stockQuantity = data.stockQuantity;

        return material;
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
