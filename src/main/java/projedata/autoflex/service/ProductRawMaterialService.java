package projedata.autoflex.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import projedata.autoflex.dto.ProductRawMaterialDTO;
import projedata.autoflex.entity.ProductEntity;
import projedata.autoflex.entity.ProductRawMaterialEntity;
import projedata.autoflex.entity.RawMaterialEntity;
import projedata.autoflex.repository.IProductRawMaterialRepository;
import projedata.autoflex.repository.IProductRepository;
import projedata.autoflex.repository.IRawMaterialRepository;

@ApplicationScoped
public class ProductRawMaterialService {
  @Inject
  IProductRawMaterialRepository productRawMaterialRepository;

  @Inject
  IProductRepository productRepository;

  @Inject
  IRawMaterialRepository rawMaterialRepository;

  public Response findAll() {
    List<ProductRawMaterialEntity> productRawMaterial = productRawMaterialRepository.listAll();

    return Response.ok(productRawMaterial).build();
  }

  public Response findById(UUID id) {
    ProductRawMaterialEntity ProductRawMaterial = productRawMaterialRepository.findById(id);

    if (ProductRawMaterial == null) {
      return Response.status(Response.Status.NOT_FOUND)
          .entity(Map.of(
              "status", 404,
              "erro", "Relação não encontrado"))
          .build();
    }

    return Response.ok(ProductRawMaterial).build();
  }

  @Transactional
  public Response create(ProductRawMaterialDTO productRawMaterialDTO) {
    var product = productRepository.findById(
        productRawMaterialDTO.getProductId());

    if (product == null) {
      Response.status(Response.Status.NOT_FOUND).entity(Map.of(
          "status", 404,
          "erro", "Produto não encontrado"))
          .build();
    }

    var rawMaterial = rawMaterialRepository.findById(
        productRawMaterialDTO.getRawMaterialId());

    if (rawMaterial == null) {
      Response.status(Response.Status.NOT_FOUND).entity(Map.of(
          "status", 404,
          "erro", "Material não encontrado"))
          .build();
    }

    ProductRawMaterialEntity productRawMaterial = new ProductRawMaterialEntity();
    productRawMaterial.setProduct(product);
    productRawMaterial.setRawMaterial(rawMaterial);
    productRawMaterial.setQuantityRequired(productRawMaterialDTO.getQuantityRequired());

    productRawMaterialRepository.persist(productRawMaterial);

    return Response.status(Response.Status.CREATED).entity(product).build();
  }

  @Transactional
  public Response update(UUID id, ProductRawMaterialDTO productRawMaterialDTO) {
    ProductRawMaterialEntity ProductRawMaterial = productRawMaterialRepository.findById(id);

    if (ProductRawMaterial == null) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }

    ProductEntity product = productRepository.findById(productRawMaterialDTO.getProductId());

    if (product == null) {
      return Response.status(Response.Status.BAD_REQUEST)
          .entity("O produto não foi encontrado ou não existe!")
          .build();
    }

    RawMaterialEntity rawMaterial = rawMaterialRepository.findById(productRawMaterialDTO.getRawMaterialId());

    if (rawMaterial == null) {
      return Response.status(Response.Status.BAD_REQUEST)
          .entity("O material não foi encontrado ou não existe!")
          .build();
    }

    ProductRawMaterial.setProduct(product);
    ProductRawMaterial.setRawMaterial(rawMaterial);
    ProductRawMaterial.setQuantityRequired(productRawMaterialDTO.getQuantityRequired());

    return Response.ok(product).build();
  }

  // @Transactional
  // public void delete(UUID id) {
  // ProductRawMaterial entity = repository.findById(id)
  // .orElseThrow(() -> new NotFoundException("Association not found"));

  // repository.delete(entity);
  // }

}
