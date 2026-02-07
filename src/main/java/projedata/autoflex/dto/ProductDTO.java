package projedata.autoflex.dto;

import java.math.BigDecimal;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductDTO {
  @SuppressWarnings("deprecation")
  @NotBlank(message = "Nome é obrigatório")
  @Schema(description = "Nome do produto", example = "Cadeira", required = true)
  public String name;

  @SuppressWarnings("deprecation")
  @NotNull(message = "Preço é obrigatório")
  @Positive(message = "O preço deve ser positivo")
  @Schema(description = "Nome do produto", example = "50.90", required = true)
  public BigDecimal price;
}