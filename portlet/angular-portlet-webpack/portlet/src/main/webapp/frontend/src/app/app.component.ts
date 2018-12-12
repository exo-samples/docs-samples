import {Component, OnInit, ViewChild } from '@angular/core';
import {MatTable, MatDialog, MatDialogConfig, MatTableDataSource, MatPaginator, MatSort} from '@angular/material';
import {AddEditDialogComponent} from '../dialogs/addEdit/addEdit.dialog.component';
import {DeleteDialogComponent} from '../dialogs/delete/delete.dialog.component';
import {Product} from '../models/product';
import {ProductsService} from '../services/products.services';

@Component({
    selector: "crud-app",
    styles: [require("./app.component.scss")],
    providers: [ ProductsService ],
    template: require("./app.component.html")
})
export class CrudComponent {
	
  	displayedColumns: string[] = ['id', 'name', 'amount', 'action'];
  	
	dataSource: MatTableDataSource<Product>;
	
	product : Product = {} as Product;
	
	@ViewChild(MatTable) table: MatTable<Product>;
	
	@ViewChild(MatPaginator) paginator: MatPaginator;
	
  	@ViewChild(MatSort) sort: MatSort;
	
  	constructor(private productsService: ProductsService, public dialog: MatDialog) {
  		 this.dataSource = new MatTableDataSource([]);
  	}

  	ngOnInit() {
    	this.getProducts();
  	}

  	getProducts(): void {
    	this.productsService.getProducts()
      	.subscribe(products => this.dataSource.data = products);
  	}

  	openProductDialog(product: Product) {
  		const dialogConfig = new MatDialogConfig();
	    if (product != null) {
		    dialogConfig.data = {
		    	id: product.id,
		        name: product.name,
		        amount: product.amount,
		        view: false,
		        title: 'Edit product'
		    };
	    }
	    else {
	    	dialogConfig.data = {
		        view: true,
		        title: 'Add new product'
		    };
	    }
	    const dialogRef = this.dialog.open(AddEditDialogComponent, dialogConfig);
	
	    dialogRef.afterClosed().subscribe(result => {
	   		if (result !== undefined) {
	   			if (result.view) {
	   				this.product.name = result.name;
	   				this.product.amount = result.amount;
			    	this.productsService.addProduct(this.product)
			      	.subscribe(product => {
			      		var products = this.dataSource.data;
			      		products.push(product);
			      		this.dataSource.data = products;
			      	});
			    }
			    else {
			    	this.product.id = result.id;
			    	this.product.name = result.name;
	   				this.product.amount = result.amount;
			    	this.productsService.updateProduct(this.product)
		        	.subscribe(product => {
		        		var products = this.dataSource.data;
			          	const ix = product ? products.findIndex(p => p.id === product.id) : -1;
			          	if (ix > -1) { 
			          		products[ix] = product;
			          	}
			          	this.dataSource.data = products;
		        	});
			    }
	      	}
	    });
  	}
  	
  	openDeleteProductDialog(product: Product) {
  		const dialogConfig = new MatDialogConfig();
		dialogConfig.data = {
			id: product.id,
		    name: product.name,
		    amount: product.amount,
		};
	    const dialogRef = this.dialog.open(DeleteDialogComponent, dialogConfig);
	
	    dialogRef.afterClosed().subscribe(product => {
	   		if (product !== undefined) {
	   			this.productsService.deleteProduct(product.id).subscribe(result => {
			    	this.dataSource.data = this.dataSource.data.filter(p => p.id !== result.id);
		        });
	      	}
	    });
  	}
  	
  	ngAfterViewInit() {
	    this.dataSource.paginator = this.paginator;
	    this.dataSource.sort = this.sort;
  	}

	applyFilter(filterValue: string) {
		filterValue = filterValue.trim(); // Remove whitespace
	    filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
	    this.dataSource.filter = filterValue;
	}
}