package projedata.autoflex.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class ProductRawMaterialDTO {

  @NotNull(message = "O produto é obrigatório.")
  private UUID productId;

  @NotNull(message = "Matéria-prima é obrigatório.")
  private UUID rawMaterialId;

  @NotNull(message = "A quantidade não pode ser vazia.")
  @Min(value = 1, message = "A quantidade deve ser pelo menos 1.")
  private Integer quantityRequired;

  public UUID getProductId() {
    return productId;
  }

  public void setProductId(UUID productId) {
    this.productId = productId;
  }

  public UUID getRawMaterialId() {
    return rawMaterialId;
  }

  public void setRawMaterialId(UUID rawMaterialId) {
    this.rawMaterialId = rawMaterialId;
  }

  public Integer getQuantityRequired() {
    return quantityRequired;
  }

  public void setQuantityRequired(Integer quantityRequired) {
    this.quantityRequired = quantityRequired;
  }
}
