import { Component, OnInit } from '@angular/core';

import { Product } from '../products/product';
import { ProductsService } from '../products/products.services';

@Component({
    selector: "crud-app",
    styles: [require("./app.component.scss")],
    providers: [ ProductsService ],
    template: require("./app.component.html"),
})
export class CrudComponent {

	products: Product[];
	
	product : Product = {} as Product;
	
	view = true;

  	constructor(private productsService: ProductsService) { }

  	ngOnInit() {
    	this.getProducts();
  	}

  	getProducts(): void {
    	this.productsService.getProducts()
      	.subscribe(products => this.products = products);
  	}
  	
  	addEditProduct(): void {
  		if (this.view) {
	    	this.productsService.addProduct(this.product)
	      	.subscribe(product => this.products.push(product));
	    }
	    else {
	    	this.productsService.updateProduct(this.product)
        	.subscribe(product => {
	          	const ix = product ? this.products.findIndex(p => p.id === product.id) : -1;
	          	if (ix > -1) { 
	          		this.products[ix] = product; 
	          	}
        	});
	    }
      	this.init();
  	}
  	
  	deleteProduct(product : Product): void {
  		this.products = this.products.filter(p => p !== product);
    	this.productsService.deleteProduct(product.id)
      	.subscribe();
  	}
  	
  	edit(product : Product): void {
  		this.view = false;
  		this.product.id = product.id;
  		this.product.name = product.name;
  		this.product.amount = product.amount;
  	}
  	
  	init(): void {
  		this.view = true;
      	this.product.name = "";
      	this.product.amount = "";
  	}
}