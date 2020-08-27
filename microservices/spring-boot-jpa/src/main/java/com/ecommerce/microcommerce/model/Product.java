/**
 * 
 */
package com.ecommerce.microcommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Ayoub
 */
@JsonIgnoreProperties(value = {"prixAchat", "id"})
public class Product {

  private int    id;

  private String nom;

  private int prix;
  
  //information que nous ne souhaitons pas exposer
  //@JsonIgnore
  private int prixAchat;

  /**
   */
  public Product() {
  }

  /**
   * @param id
   * @param nom
   * @param prix
   * @param prixAchat
   */
  public Product(int id, String nom, int prix, int prixAchat) {
    super();
    this.id = id;
    this.nom = nom;
    this.prix = prix;
    this.prixAchat = prixAchat;
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the nom
   */
  public String getNom() {
    return nom;
  }

  /**
   * @param nom the nom to set
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * @return the prix
   */
  public int getPrix() {
    return prix;
  }

  /**
   * @param prix the prix to set
   */
  public void setPrix(int prix) {
    this.prix = prix;
  }

  /**
   * @return the prixAchat
   */
  public int getPrixAchat() {
    return prixAchat;
  }

  /**
   * @param prixAchat the prixAchat to set
   */
  public void setPrixAchat(int prixAchat) {
    this.prixAchat = prixAchat;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", nom='" + nom + '\'' +
        ", prix=" + prix + '}';
  }
}
