package projedata.autoflex.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import projedata.autoflex.dto.ProductionResponseDTO;
import projedata.autoflex.dto.ProductionSuggestionDTO;
import projedata.autoflex.entity.ProductEntity;
import projedata.autoflex.entity.ProductRawMaterialEntity;
import projedata.autoflex.repository.IProductRawMaterialRepository;
import projedata.autoflex.repository.IProductRepository;

@ApplicationScoped
public class ProductionService {
  @Inject
  IProductRepository productRepository;

  @Inject
  IProductRawMaterialRepository productRawMaterialRepository;

  public Response getProductionSuggestions() {

    List<ProductEntity> products = productRepository.listAll();
    List<ProductionSuggestionDTO> result = new ArrayList<>();

    for (ProductEntity product : products) {

      List<ProductRawMaterialEntity> materials = productRawMaterialRepository.find("product.id", product.getId())
          .list();

      if (materials.isEmpty())
        continue;

      int maxProduction = Integer.MAX_VALUE;

      for (ProductRawMaterialEntity pm : materials) {
        BigDecimal possible = pm.getRawMaterial().stockQuantity
            .divide(BigDecimal.valueOf(pm.getQuantityRequired()), RoundingMode.FLOOR);
        int possibleInt = possible.intValue();
        maxProduction = Math.min(maxProduction, possibleInt);
      }

      if (maxProduction > 0) {
        ProductionSuggestionDTO dto = new ProductionSuggestionDTO();
        dto.productId = product.getId();
        dto.productName = product.getName();
        dto.maxQuantity = maxProduction;
        dto.unitPrice = product.getPrice();
        dto.totalValue = product.getPrice().multiply(BigDecimal.valueOf(maxProduction));

        result.add(dto);
      }
    }

    result.sort((a, b) -> b.totalValue.compareTo(a.totalValue));

    BigDecimal totalValue = result.stream()
        .map(p -> p.unitPrice.multiply(BigDecimal.valueOf(p.maxQuantity)))
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    ProductionResponseDTO response = new ProductionResponseDTO();
    response.totalValue = totalValue;
    response.products = result;

    return Response.ok(response).build();
  }
}
