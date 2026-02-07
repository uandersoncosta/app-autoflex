package projedata.autoflex.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import projedata.autoflex.entity.ProductEntity;
import java.util.UUID;

@ApplicationScoped
public class IProductRepository implements PanacheRepositoryBase<ProductEntity, UUID> {
}
