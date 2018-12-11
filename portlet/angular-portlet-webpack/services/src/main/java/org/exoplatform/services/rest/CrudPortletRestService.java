/*
 * Copyright (C) 2003-2010 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.services.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import org.exoplatform.dao.ProductDAO;
import org.exoplatform.entity.Product;
import org.exoplatform.services.rest.resource.ResourceContainer;

@Path("crud")
public class CrudPortletRestService implements ResourceContainer {

  private ProductDAO productDAO;

  public CrudPortletRestService(ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  @GET
  @Path("/addProduct")
  @RolesAllowed("users")
  public Response addProduct(@Context UriInfo uriInfo,
                             @QueryParam("productName") String productName,
                             @QueryParam("productAmount") String productAmount) throws Exception {
    Product product = new Product(productName, productAmount);
    productDAO.create(product);
    JSONObject productJson = new JSONObject();
    productJson.put("id", product.getProductId());
    productJson.put("name", product.getProductName());
    productJson.put("amount", product.getProductAmount());
    return Response.created(uriInfo.getAbsolutePath())
                   .entity(productJson.toString())
                   .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
                   .status(Response.Status.OK)
                   .build();
  }

  @GET
  @Path("/deleteProduct/{productId}")
  @RolesAllowed("users")
  public Response deleteProduct(@Context UriInfo uriInfo, @PathParam("productId") String productId) throws Exception {
    Product product = productDAO.find(Long.parseLong(productId));
    if (product != null) {
      JSONObject productJson = new JSONObject();
      productJson.put("id", product.getProductId());
      productJson.put("name", product.getProductName());
      productJson.put("amount", product.getProductAmount());
      productDAO.delete(product);
      return Response.created(uriInfo.getAbsolutePath())
          .entity(productJson.toString())
          .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
          .status(Response.Status.OK)
          .build();
    }
    return Response.created(uriInfo.getAbsolutePath())
                   .entity("Product " + productId + " is not found")
                   .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
                   .status(Response.Status.NOT_FOUND)
                   .build();
  }
  
  @GET
  @Path("/getAllProducts")
  @RolesAllowed("users")
  public Response getAllProducts(@Context UriInfo uriInfo) throws Exception {
    List<Product> products = productDAO.findAll();
    JSONArray response = new JSONArray();
    for (Product product : products) {
      JSONObject productJson = new JSONObject();
      productJson.put("id", product.getProductId());
      productJson.put("name", product.getProductName());
      productJson.put("amount", product.getProductAmount());
      response.put(productJson);
    }
    return Response.created(uriInfo.getAbsolutePath())
                   .entity(response.toString())
                   .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
                   .status(Response.Status.OK)
                   .build();
  }
  
  @GET
  @Path("/getProductById/{productId}")
  @RolesAllowed("users")
  public Response getProductById(@Context UriInfo uriInfo, @PathParam("productId") String productId) throws Exception {
    Product product = productDAO.find(Long.parseLong(productId));
    if (product != null) {
      JSONObject productJson = new JSONObject();
      productJson.put("id", product.getProductId());
      productJson.put("name", product.getProductName());
      productJson.put("amount", product.getProductAmount());
      return Response.created(uriInfo.getAbsolutePath())
          .entity(productJson.toString())
          .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
          .status(Response.Status.OK)
          .build();
    }
    return Response.created(uriInfo.getAbsolutePath())
                   .entity("Product " + productId + " is not found")
                   .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
                   .status(Response.Status.NOT_FOUND)
                   .build();
  }

  @GET
  @Path("/updateProduct/{productId}")
  @RolesAllowed("users")
  public Response updateProduct(@Context UriInfo uriInfo,
                                @PathParam("productId") String productId,
                                @QueryParam("productName") String productName,
                                @QueryParam("productAmount") String productAmount) throws Exception {
    Product product = productDAO.find(Long.parseLong(productId));
    if (product != null) {
      product.setProductName(productName);
      product.setProductAmount(productAmount);
      productDAO.update(product);
      JSONObject productJson = new JSONObject();
      productJson.put("id", product.getProductId());
      productJson.put("name", product.getProductName());
      productJson.put("amount", product.getProductAmount());
      return Response.created(uriInfo.getAbsolutePath())
          .entity(productJson.toString())
          .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
          .status(Response.Status.OK)
          .build();
    }
    return Response.created(uriInfo.getAbsolutePath())
                   .entity("Product " + productId + " is not found")
                   .type(MediaType.TEXT_PLAIN + "; charset=utf-8")
                   .status(Response.Status.NOT_FOUND)
                   .build();
  }

}
