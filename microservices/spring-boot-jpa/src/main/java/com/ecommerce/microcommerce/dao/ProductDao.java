/**
 * 
 */
package com.ecommerce.microcommerce.dao;

import java.util.List;

import com.ecommerce.microcommerce.model.Product;

/**
 * @author Ayoub
 */
public interface ProductDao {

  public List<Product> findAll();

  public Product findById(int id);

  public Product save(Product product);
}
