package projedata.autoflex.service;

import java.util.List;
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

  public Response findAll() {
    List<ProductRawMaterialEntity> productRawMaterial = productRawMaterialRepository.listAll();

    return Response.ok(productRawMaterial).build();
  }

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

  // @Transactional
  // public ProductRawMaterialDTO update(UUID id, ProductRawMaterialDTO dto) {
  // ProductRawMaterial entity = repository.findById(id)
  // .orElseThrow(() -> new NotFoundException("Association not found"));

  // Product product = productRepository.findById(dto.getProductId())
  // .orElseThrow(() -> new NotFoundException("Product not found"));

  // RawMaterial rawMaterial =
  // rawMaterialRepository.findById(dto.getRawMaterialId())
  // .orElseThrow(() -> new NotFoundException("Raw material not found"));

  // entity.setProduct(product);
  // entity.setRawMaterial(rawMaterial);
  // entity.setQuantityRequired(dto.getQuantityRequired());

  // repository.persist(entity);

  // dto.setId(entity.getId());
  // return dto;
  // }

  // @Transactional
  // public void delete(UUID id) {
  // ProductRawMaterial entity = repository.findById(id)
  // .orElseThrow(() -> new NotFoundException("Association not found"));

  // repository.delete(entity);
  // }

  // public ProductRawMaterialDTO findById(UUID id) {
  // ProductRawMaterial entity = repository.findById(id)
  // .orElseThrow(() -> new NotFoundException("Association not found"));

  // ProductRawMaterialDTO dto = new ProductRawMaterialDTO();
  // dto.setId(entity.getId());
  // dto.setProductId(entity.getProduct().getId());
  // dto.setRawMaterialId(entity.getRawMaterial().getId());
  // dto.setQuantityRequired(entity.getQuantityRequired());
  // return dto;
  // }
}
