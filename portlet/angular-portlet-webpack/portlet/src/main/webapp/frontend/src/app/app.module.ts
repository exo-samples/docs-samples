import {NgModule} from "@angular/core";
import {MatButtonModule, MatDialogModule, MatIconModule, MatInputModule, MatPaginatorModule, MatSortModule, MatTableModule, MatToolbarModule, MatProgressSpinnerModule} from "@angular/material";
import {BrowserModule} from "@angular/platform-browser";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from "@angular/common/http";
import {AddEditDialogComponent} from '../dialogs/addEdit/addEdit.dialog.component';
import {DeleteDialogComponent} from '../dialogs/delete/delete.dialog.component';
import {ProductsService} from '../services/products.services';
import {CrudComponent} from "./app.component";

@NgModule({
    imports: [
    	BrowserModule,
    	BrowserAnimationsModule,
    	HttpClientModule,
    	FormsModule,
    	MatTableModule,
    	MatDialogModule,
    	MatButtonModule,
    	MatInputModule,
    	MatIconModule,
    	MatSortModule,
   		MatToolbarModule,
    	MatPaginatorModule,
    	ReactiveFormsModule,
    	MatProgressSpinnerModule
    ],
    bootstrap:    [CrudComponent],
    declarations: [CrudComponent, AddEditDialogComponent, DeleteDialogComponent],
    entryComponents: [AddEditDialogComponent, DeleteDialogComponent],
  	providers: [ProductsService],
})
export class AppModule {}