package projedata.autoflex.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import projedata.autoflex.entity.ProductEntity;

@ApplicationScoped
public class IProductRepository implements PanacheRepository<ProductEntity> {
}
