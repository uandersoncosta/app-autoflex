package projedata.autoflex.repository;

import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import projedata.autoflex.entity.ProductRawMaterialEntity;

@ApplicationScoped
public class IProductRawMaterialRepository implements PanacheRepositoryBase<ProductRawMaterialEntity, UUID> {
}
