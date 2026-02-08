package projedata.autoflex.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductionSuggestionDTO {
  public UUID productId;
  public String productName;
  public Integer maxQuantity;
  public BigDecimal unitPrice;
  public BigDecimal totalValue;
}
