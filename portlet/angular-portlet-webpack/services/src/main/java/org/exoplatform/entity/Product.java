/**
 * 
 */
package org.exoplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.exoplatform.commons.api.persistence.ExoEntity;

/**
 * @author exo
 */
@Entity
@ExoEntity
@Table(name = "PRODUCT")
public class Product {
  
  @Id
  @SequenceGenerator(name="SEQ_PRODUCT_PRODUCT_ID", sequenceName="SEQ_PRODUCT_PRODUCT_ID")
  @GeneratedValue(strategy=GenerationType.AUTO, generator="SEQ_PRODUCT_PRODUCT_ID")
  @Column(name="PRODUCT_ID")
  private Long productId;

  @Column(name="PRODUCT_NAME")
  private String productName;
  
  @Column(name="PRODUCT_AMOUNT")
  private String productAmount;
  
  public Product() {
  }

  /**
   * @param productName
   * @param productAmount
   */
  public Product(String productName, String productAmount) {
    super();
    this.productName = productName;
    this.productAmount = productAmount;
  }

  /**
   * @return the productId
   */
  public Long getProductId() {
    return productId;
  }

  /**
   * @param productId the productId to set
   */
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  /**
   * @return the productName
   */
  public String getProductName() {
    return productName;
  }

  /**
   * @param productName the productName to set
   */
  public void setProductName(String productName) {
    this.productName = productName;
  }

  /**
   * @return the productAmount
   */
  public String getProductAmount() {
    return productAmount;
  }

  /**
   * @param productAmount the productAmount to set
   */
  public void setProductAmount(String productAmount) {
    this.productAmount = productAmount;
  }
}
