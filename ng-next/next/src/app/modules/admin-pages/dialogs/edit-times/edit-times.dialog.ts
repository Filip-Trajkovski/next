import {Component, Inject, Input, ViewChild} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef, MatInput} from "@angular/material";
import {ReservationTimeService} from "../../services/reservation-time.service";
import {ReservationTime} from "../../interfaces/reservation-time.interface";
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {ReservationRemovalDialog} from "../reservation-removal/reservation-removal.dialog";
import {ReservationTimeConfigService} from "../../services/reservation-time-config.service";

@Component({
  templateUrl: "edit-times.dialog.html",
  styleUrls: ["edit-times.dialog.scss"]
})
export class EditTimesDialog {

  name: string;
  futureDate: string;

  times$: ReservationTime[];

  form = this._formDefinition;

  constructor(private _dialogRef: MatDialogRef<EditTimesDialog>,
              private matDialog: MatDialog,
              private _timeService: ReservationTimeService,
              private _configService: ReservationTimeConfigService,
              private _builder: FormBuilder,
              @Inject(MAT_DIALOG_DATA) public data: { id: number, def: boolean, name: string, futureDefaultDate: string }) {
    _timeService.findAllByConfigId(this.data.id).subscribe(times => this.times$ = times);
    this.name = data.name;
    this.futureDate = data.futureDefaultDate;
  }


  private get _formDefinition(): FormGroup {
    return this._builder.group([{
      times: this._builder.array([])
    }]);
  }

  private get _timesFormArray() {
    return this.form.get('times') as FormArray;
  }

  private _getTimeFormGroup(id: number, time: string): FormGroup {
    return this._builder.group([
      {
        id: [id || null],
        time: [time || null],
        reservationTimeConfigurationId: [this.data.id]
      }
    ])
  }

  addTime(id: number, time: string) {
    this.times$.push({id: id, time: time, reservationTimeConfigurationId: this.data.id});
    this._timesFormArray.push(this._getTimeFormGroup(id, time));
  }


  onTimeChange(id: number, time: string, index: number) {
    console.log(id, time, index);
    this._timeService
      .createOrUpdate({id: id, time: time, reservationTimeConfigurationId: this.data.id})
      .subscribe();
  }

  delete(id: number, index: number) {
    const deleteRef = this.matDialog.open(ReservationRemovalDialog, {
      data: {id: id, type: "TIME"}
    });

    deleteRef.afterClosed().subscribe(result => {
      if (result == true) {
        this.times$.splice(index, 1);
        this._timesFormArray.removeAt(index);
      }
    })
  }

  onNameChange() {
    console.log(this.name);
    this._configService.changeName(this.data.id, this.name).subscribe();
  }

  setDefault() {
    const defaultRef = this.matDialog.open(ReservationRemovalDialog, {
      data: {id: this.data.id, type: 'NEW_DEFAULT'}
    })
  }

  setFutureDefault() {
    const futureDefRef = this.matDialog.open(ReservationRemovalDialog, {
      data: {id: this.data.id, type: 'FUTURE_DEFAULT', date: this.futureDate}
    })
  }
}

