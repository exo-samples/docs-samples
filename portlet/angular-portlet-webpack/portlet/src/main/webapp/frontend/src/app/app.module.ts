import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";
import {HttpClientModule} from "@angular/common/http";
import {CrudComponent} from "./app.component";

@NgModule({
    imports:      [BrowserModule,HttpClientModule,FormsModule],
    bootstrap:    [CrudComponent],
    declarations: [CrudComponent],
})
export class AppModule {}