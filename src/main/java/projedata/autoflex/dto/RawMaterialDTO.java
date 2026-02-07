package projedata.autoflex.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import jakarta.validation.constraints.NotBlank;

public class RawMaterialDTO {
  @SuppressWarnings("deprecation")
  @NotBlank(message = "Nome é obrigatório")
  @Schema(description = "Nome do produto", example = "wood", required = true)
  public String name;

  @SuppressWarnings("deprecation")
  @NotBlank(message = "Nome é obrigatório")
  @Schema(description = "Nome do produto", example = "5", required = true)
  public Double stockQuantity;
}
