import {Component, Input} from "@angular/core";
import {Reservation} from "../../../shared/interfaces/reservation.interface";
import {ReservationStatus} from "../../../shared/enums/reservation-status.enum";

@Component({
  selector: "reservation",
  styleUrls: ["reservation.component.scss"],
  templateUrl: "reservation.component.html"
})
export class ReservationComponent {

  @Input() reservation: Reservation;

  constructor(){}

  handleStatus(status){
    console.log(status, ReservationStatus[status]);
    return ReservationStatus[status]
  }
}
