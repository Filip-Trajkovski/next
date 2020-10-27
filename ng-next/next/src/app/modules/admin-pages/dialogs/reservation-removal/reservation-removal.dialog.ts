import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {Observable} from "rxjs";
import {Reservation} from "../../../shared/interfaces/reservation.interface";
import {ReservationsAdminService} from "../../services/reservations-admin.service";
import {ReservationTimeConfigService} from "../../services/reservation-time-config.service";
import {ReservationTimeService} from "../../services/reservation-time.service";

@Component({
  templateUrl: "reservation-removal.dialog.html",
  styleUrls: ["reservation-removal.dialog.scss"]
})
export class ReservationRemovalDialog {

  reservations$: Observable<Reservation[]>;

  constructor(private _dialogRef: MatDialogRef<ReservationRemovalDialog>,
              private _service: ReservationsAdminService,
              private _configService: ReservationTimeConfigService,
              private _timeService: ReservationTimeService,
              @Inject(MAT_DIALOG_DATA) public data: { type: string, id: number, date: string }) {
    this.reservations$ = this.getReservationsByType(this.data.type, this.data.id)
  }


  getReservationsByType(type, id): Observable<Reservation[]> {
    if (type == 'CONFIG') {
      return this._service.findAllActiveByConfigId(id)
    } else if (type == 'TIME') {
      return this._service.findAllActiveByTimeId(id)
    } else if (type == 'NEW_DEFAULT') {
      return this._service.findAllByCurrentDefault();
    } else if(type == 'FUTURE_DEFAULT'){
      return this._service.findAllInvalidByNewFutureDefaultDate(this.data.date);
    }
  }

  confirm() {
    if (this.data.type == 'CONFIG') {
      this._configService.deleteById(this.data.id).subscribe(() => {
        this._dialogRef.close();
      }, error => {

      })
    } else if (this.data.type == 'TIME') {
      this._timeService.deleteById(this.data.id).subscribe(() => {
        this._dialogRef.close(true);
      }, error => {

      })
    } else if(this.data.type == 'NEW_DEFAULT') {
      this._configService.changeDefaultConfig(this.data.id).subscribe(() => {
        this._dialogRef.close(true);
      }, error => {

      })
    } else if(this.data.type == 'FUTURE_DEFAULT') {
      this._configService.changeFutureDefault(this.data.id, this.data.date).subscribe(() => {
        this._dialogRef.close(true);
      }, error => {

      })
    }
  }

  close() {
    this._dialogRef.close(false);
  }
}
