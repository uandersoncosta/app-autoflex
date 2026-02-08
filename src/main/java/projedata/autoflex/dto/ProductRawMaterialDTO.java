package projedata.autoflex.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class ProductRawMaterialDTO {
  @NotNull(message = "O produto é obrigatório.")
  public UUID productId;

  @NotNull(message = "Matéria-prima é obrigatório.")
  public UUID rawMaterialId;

  @NotNull(message = "A quantidade não pode ser vazia.")
  @Min(value = 1, message = "A quantidade deve ser pelo menos 1.")
  public Integer quantityRequired;
}
