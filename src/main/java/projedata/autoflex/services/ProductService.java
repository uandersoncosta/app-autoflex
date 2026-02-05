package projedata.autoflex.service;

import projedata.autoflex.entity.ProductEntity;
import projedata.autoflex.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository repository;

    @Transactional
    public ProductEntity create(ProductEntity product) {
        repository.persist(product);
        return product;
    }

    public List<ProductEntity> findAll() {
        return repository.listAll();
    }

    public ProductEntity findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public ProductEntity update(Long id, ProductEntity data) {
        ProductEntity product = repository.findById(id);
        if (product == null) return null;

        product.code = data.code;
        product.name = data.name;
        product.price = data.price;

        return product;
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
