import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Product } from '../models/product';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable()
export class ProductsService {

  dialogData: any;
  
  getProductsUrl = '/rest/crud/getAllProducts';
  
  addProductUrl = '/rest/crud/addProduct';
  
  deleteProductUrl = '/rest/crud/deleteProduct';
  
  updateProductUrl = '/rest/crud/updateProduct';

  constructor(private http: HttpClient) { }
  
  getDialogData() {
    return this.dialogData;
  }

  getProducts (): Observable<Product[]> {
    return this.http.get<Product[]>(this.getProductsUrl);
  }
  
  addProduct (product: Product): Observable<Product> {
    return this.http.get<Product>(this.addProductUrl + "?productName=" + product.name + "&productAmount=" + product.amount);
  }
    
  deleteProduct (id: number): Observable<Product> {
    return this.http.get<Product>(this.deleteProductUrl + "/" + id);
  }
  
  updateProduct (product: Product): Observable<Product> {
    return this.http.get<Product>(this.updateProductUrl + "/" + product.id + "?productName=" + product.name + "&productAmount=" + product.amount);
  }
}

  