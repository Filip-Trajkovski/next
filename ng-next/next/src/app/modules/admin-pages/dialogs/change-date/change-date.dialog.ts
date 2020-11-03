import {Component, Inject} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {Observable} from "rxjs";
import {Option} from "../../../shared/interfaces/option.interface";
import * as moment from "moment";
import {ReservationsAdminService} from "../../services/reservations-admin.service";
import {ReservationsService} from "../../../shared/modules/reservations/services/reservations.service";

@Component({
  templateUrl: "change-date.dialog.html",
  styleUrls: ["change-date.dialog.scss"]
})
export class ChangeDateDialog {

  form: FormGroup = this._formDefinition;
  reservationTimes$: Observable<Option[]>;

  constructor(private _dialogRef: MatDialogRef<ChangeDateDialog>,
              private _builder: FormBuilder,
              private _publicService: ReservationsService,
              private _service: ReservationsAdminService,
              @Inject(MAT_DIALOG_DATA) public data: { id: number }) {

    this.form.get('date').valueChanges.subscribe(fullDate => {
      const parsedDate = new Date(fullDate);
      const formattedDate = moment(parsedDate).format("YYYY-MM-DD");
      this.form.get('reservationTimeId').setValue(null);
      this.reservationTimes$ = this._publicService.findUnreservedTimesForDate(formattedDate);
    })

  }

  private get _formDefinition() {
    return this._builder.group({
      id: [this.data.id],
      date: ['', Validators.required],
      reservationTimeId: [null]
    })
  }

  onSubmit() {
    const date = this.form.get('date').value;
    const parsedDate = new Date(date);
    const formattedDate = moment(parsedDate).format("YYYY-MM-DD");

    this._service.changeDateAndAccept({...this.form.value, date: formattedDate}).subscribe(() =>
      this._dialogRef.close(true)
    )
  }
}
