import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";

@Component({
  templateUrl: "message.dialog.html",
  styleUrls: ["message.dialog.scss"]
})
export class MessageDialog {

  constructor(private _dialogRef: MatDialogRef<MessageDialog>,
              @Inject(MAT_DIALOG_DATA) public data: { text: number }) {
  }


  onClose() {
    this._dialogRef.close();
  }
}
