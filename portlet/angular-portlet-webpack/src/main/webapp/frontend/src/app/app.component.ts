import {Component} from "@angular/core";

@Component({
    selector: "hellow-app",
    styles: [require("./app.component.scss")],
    template: require("./app.component.html"),
})
export class HelloComponent {
    message = "Hoo Ah!";
}