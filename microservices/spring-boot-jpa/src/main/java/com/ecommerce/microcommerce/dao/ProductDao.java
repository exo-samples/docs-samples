/**
 * 
 */
package com.ecommerce.microcommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.microcommerce.model.Product;

/**
 * @author Ayoub
 */
@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
  
  Product findById(int id);
  
  List<Product> findByPrixGreaterThan(int prixLimit);
  
  Product findByNomLike(String nom);
  
  @Query("SELECT nom, prix FROM Product p WHERE p.prix < :prixMax")
  List<Product> searchCheapProducts(@Param("prixMax") int prix);
}
