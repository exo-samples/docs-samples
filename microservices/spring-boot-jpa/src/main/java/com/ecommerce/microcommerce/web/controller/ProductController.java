/**
 * 
 */
package com.ecommerce.microcommerce.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;

/**
 * @author Ayoub
 */
@RestController
public class ProductController {
  
  @Autowired
  private ProductDao productDao;

  @RequestMapping(value = "/products")
  public List<Product> getProducts() {
    return productDao.findAll();
  }

  @GetMapping(value = "/products/{id}")
  public Product getProductById(@PathVariable int id) {
    return productDao.findById(id);
  }
  
  @PostMapping(value = "/products")
  public ResponseEntity<Void> addProduct(@RequestBody Product product) {
    Product productAdded =  productDao.save(product);

    if (productAdded == null)
        return ResponseEntity.noContent().build();

    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(productAdded.getId())
            .toUri();

    return ResponseEntity.created(location).build();
  }
}
