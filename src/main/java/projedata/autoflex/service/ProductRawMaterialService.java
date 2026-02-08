package projedata.autoflex.service;

import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import projedata.autoflex.dto.ProductRawMaterialDTO;
import projedata.autoflex.entity.ProductRawMaterialEntity;
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

  @Transactional
  public Response create(ProductRawMaterialDTO productRawMaterialDTO) {
    var product = productRepository.findById(
        productRawMaterialDTO.productId);

    if (product == null) {
      Response.status(Response.Status.NOT_FOUND).entity(Map.of(
          "status", 404,
          "erro", "Produto não encontrado"))
          .build();
    }

    var rawMaterial = rawMaterialRepository.findById(
        productRawMaterialDTO.rawMaterialId);

    if (rawMaterial == null) {
      Response.status(Response.Status.NOT_FOUND).entity(Map.of(
          "status", 404,
          "erro", "Material não encontrado"))
          .build();
    }

    ProductRawMaterialEntity productRawMaterial = new ProductRawMaterialEntity();
    productRawMaterial.setProduct(product);
    productRawMaterial.setRawMaterial(rawMaterial);
    productRawMaterial.setQuantityRequired(productRawMaterialDTO.quantityRequired);

    productRawMaterialRepository.persist(productRawMaterial);

    return Response.status(Response.Status.CREATED).entity(product).build();
  }
}
