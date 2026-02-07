package projedata.autoflex.service;

import projedata.autoflex.entity.ProductEntity;
import projedata.autoflex.repository.IProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    IProductRepository productrepository;

    @Transactional
    public ProductEntity create(ProductEntity product) {
        productrepository.persist(product);
        return product;
    }

    public List<ProductEntity> findAll() {
        return productrepository.listAll();
    }

    public ProductEntity findById(Long id) {
        return productrepository.findById(id);
    }

    @Transactional
    public ProductEntity update(Long id, ProductEntity data) {
        ProductEntity product = productrepository.findById(id);
        if (product == null) return null;
        
        product.name = data.name;
        product.price = data.price;

        return product;
    }

    @Transactional
    public void delete(Long id) {
        productrepository.deleteById(id);
    }
}
