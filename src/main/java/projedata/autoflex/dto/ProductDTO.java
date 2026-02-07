package projedata.autoflex.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductDTO {
  @NotBlank(message = "Nome é obrigatório")
  public String name;

  @NotNull(message = "Preço é obrigatório")
  @Positive(message = "O preço deve ser positivo")
  public BigDecimal price;
}