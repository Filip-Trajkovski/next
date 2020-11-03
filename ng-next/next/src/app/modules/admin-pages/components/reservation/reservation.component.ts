import {Component, EventEmitter, Input, Output} from "@angular/core";
import {Reservation} from "../../../shared/interfaces/reservation.interface";
import {ReservationStatus} from "../../../shared/enums/reservation-status.enum";
import {ReservationsAdminService} from "../../services/reservations-admin.service";
import {DatePipe} from "@angular/common";
import {MatDialog} from "@angular/material";
import {ChangeDateDialog} from "../../dialogs/change-date/change-date.dialog";

@Component({
  selector: "reservation",
  styleUrls: ["reservation.component.scss"],
  templateUrl: "reservation.component.html"
})
export class ReservationComponent {

  @Input() reservation: Reservation;

  @Input() showActions = false;
  @Output() refresh: EventEmitter<void> = new EventEmitter();

  constructor(private _reservationService: ReservationsAdminService,
              private _dialogRef: MatDialog) {
  }

  handleStatus(status) {
    return ReservationStatus[status]
  }

  showAccept() {
    return this.reservation.status == ReservationStatus.PENDING
  }


  showReject() {
    return this.reservation.status == ReservationStatus.PENDING || this.reservation.status == ReservationStatus.ACCEPTED
  }

  showChangeDate() {
    return this.reservation.status != ReservationStatus.FINISHED
  }

  accept() {
    this._reservationService.acceptReservation(this.reservation.id).subscribe(() => this.refresh.emit())
  }

  reject() {
    this._reservationService.rejectReservation(this.reservation.id).subscribe(() => this.refresh.emit())
  }

  changeDateAndAccept() {
    this._dialogRef.open(ChangeDateDialog, {
      data: {id: this.reservation.id}
    }).afterClosed().subscribe(boolean => {
      if(boolean == true){
        this.refresh.emit();
      }
    });

  }
}
