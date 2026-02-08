package projedata.autoflex.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Table(name = "product_raw_material")
public class ProductRawMaterialEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  @NotNull
  private ProductEntity product;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "raw_material_id", nullable = false)
  @NotNull
  private RawMaterialEntity rawMaterial;

  @Column(name = "quantity_required", nullable = false)
  @Min(1)
  private Integer quantityRequired;

  public UUID getId() {
    return id;
  }

  public ProductEntity getProduct() {
    return product;
  }

  public void setProduct(ProductEntity product) {
    this.product = product;
  }

  public RawMaterialEntity getRawMaterial() {
    return rawMaterial;
  }

  public void setRawMaterial(RawMaterialEntity rawMaterial) {
    this.rawMaterial = rawMaterial;
  }

  public Integer getQuantityRequired() {
    return quantityRequired;
  }

  public void setQuantityRequired(Integer quantityRequired) {
    this.quantityRequired = quantityRequired;
  }
}
