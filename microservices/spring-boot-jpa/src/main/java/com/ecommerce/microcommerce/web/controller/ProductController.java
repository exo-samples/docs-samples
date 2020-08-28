/**
 * 
 */
package com.ecommerce.microcommerce.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @GetMapping(value = "/products")
  public List<Product> getProducts() {
    return productDao.findAll();
  }

  @PostMapping(value = "/products")
  public ResponseEntity<Void> addProduct(@RequestBody Product product) {
    Product productAdded = productDao.save(product);

    if (productAdded == null)
      return ResponseEntity.noContent().build();

    URI location = ServletUriComponentsBuilder
                                              .fromCurrentRequest()
                                              .path("/{id}")
                                              .buildAndExpand(productAdded.getId())
                                              .toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping(value = "/products/{id}")
  public Product getProductById(@PathVariable int id) {
    return productDao.findById(id);
  }

  @GetMapping(value = "/products/nom/{nom}")
  public Product getProductByNom(@PathVariable String nom) {
    return productDao.findByNomLike("%" + nom + "%");
  }

  @GetMapping(value = "/products/prixMin/{prixMin}")
  public List<Product> getProductByMaxPrice(@PathVariable int prixMin) {
    return productDao.findByPrixGreaterThan(prixMin);
  }

  @DeleteMapping(value = "/products/{id}")
  public void deleteProduct(@PathVariable int id) {
    productDao.deleteById(id);
  }

  @PatchMapping(value = "/products")
  public void updateProduit(@RequestBody Product product) {
    productDao.save(product);
  }
}
