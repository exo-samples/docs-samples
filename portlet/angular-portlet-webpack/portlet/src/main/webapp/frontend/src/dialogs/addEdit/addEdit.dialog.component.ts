import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Component, Inject} from '@angular/core';
import {FormControl, Validators} from '@angular/forms';


@Component({
  selector: 'app-add.dialog',
  template: require("./addEdit.dialog.html")
})

export class AddEditDialogComponent {
  constructor(public dialogRef: MatDialogRef<AddEditDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }
  
  formControl = new FormControl('', [
    Validators.required
  ]);

  getErrorMessage() {
    return this.formControl.hasError('required') ? 'Required field' : '';
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
