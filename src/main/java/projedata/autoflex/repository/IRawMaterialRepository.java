package projedata.autoflex.repository;

import projedata.autoflex.entity.RawMaterialEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IRawMaterialRepository implements PanacheRepository<RawMaterialEntity> {
}
