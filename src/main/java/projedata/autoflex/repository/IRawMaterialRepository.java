package projedata.autoflex.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import projedata.autoflex.entity.RawMaterialEntity;
import java.util.UUID;


@ApplicationScoped
public class IRawMaterialRepository implements PanacheRepositoryBase<RawMaterialEntity, UUID> {
}
