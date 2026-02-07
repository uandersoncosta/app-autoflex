package projedata.autoflex.service;

import projedata.autoflex.entity.ProductEntity;
import projedata.autoflex.dto.ProductDTO;
import projedata.autoflex.repository.IProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.UUID;

import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ProductService {

    @Inject
    IProductRepository productrepository;

    @Transactional
    public Response create(ProductDTO productdto) {
        ProductEntity product = new ProductEntity();

        product.name = productdto.name;
        product.price = productdto.price;

        productrepository.persist(product);

        return Response.status(Response.Status.CREATED).entity(product).build();
    }

    public Response findAll() {
        List<ProductEntity> products = productrepository.listAll();
        return Response.ok(products).build();
    }

    public Response findById(UUID id) {
        ProductEntity product = productrepository.findById(id);

        return Response.ok(product).build();
    }

    @Transactional
    public ProductEntity update(UUID id, ProductEntity data) {
        ProductEntity product = productrepository.findById(id);
        if (product == null)
            return null;

        product.name = data.name;
        product.price = data.price;

        return product;
    }

    @Transactional
    public void delete(UUID id) {
        productrepository.deleteById(id);
    }
}
