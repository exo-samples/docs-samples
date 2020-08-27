/**
 * 
 */
package com.ecommerce.microcommerce.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecommerce.microcommerce.model.Product;

/**
 * @author Ayoub
 */
@Repository
public class ProductDaoImpl implements ProductDao {

  public static List<Product> products = new ArrayList<>();

  static {
    products.add(new Product(1, "Ordinateur portable", 350, 120));
    products.add(new Product(2, "Aspirateur Robot", 500, 150));
    products.add(new Product(3, "Table de Ping Pong", 750, 500));
  }

  @Override
  public List<Product> findAll() {
    return products;
  }

  @Override
  public Product findById(int id) {
    for (Product product : products) {
      if (product.getId() == id) {
        return product;
      }
    }
    return null;
  }

  @Override
  public Product save(Product product) {
    products.add(product);
    return product;
  }

}
