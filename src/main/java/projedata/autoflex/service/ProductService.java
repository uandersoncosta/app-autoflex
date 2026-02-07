package projedata.autoflex.service;

import projedata.autoflex.entity.ProductEntity;
import projedata.autoflex.dto.ProductDTO;
import projedata.autoflex.repository.IProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

    if (product == null) {
      return Response.status(Response.Status.NOT_FOUND)
          .entity(Map.of(
              "status", 404,
              "erro", "Produto não encontrado"))
          .build();
    }

    return Response.ok(product).build();
  }

  @Transactional
  public Response update(UUID id, ProductDTO productdto) {
    var product = productrepository.findById(id);

    if (product == null) {
      return Response.status(Response.Status.NOT_FOUND)
          .entity(Map.of(
              "status", 404,
              "erro", "Produto não encontrado"))
          .build();
    }

    product.name = productdto.name;
    product.price = productdto.price;

    return Response.ok(product).build();
  }

  @Transactional
  public void delete(UUID id) {
    productrepository.deleteById(id);
  }
}
