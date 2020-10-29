import {Component} from "@angular/core";
import {Observable} from "rxjs";
import {Option} from "../../../shared/interfaces/option.interface";
import {ReservationsAdminService} from "../../services/reservations-admin.service";
import {Reservation} from "../../../shared/interfaces/reservation.interface";
import {DatePipe} from "@angular/common";
import {FormControl} from "@angular/forms";

@Component({
  templateUrl: "reservations-list.page.html",
  styleUrls: ["reservations-list.page.scss"]
})
export class ReservationsListPage {

  statuses$: Observable<Option[]>;
  reservations$: Observable<Reservation[]>;

  dateControl: FormControl = new FormControl('');

  constructor(private _service: ReservationsAdminService,
              private _datePipe: DatePipe){
    const todaysDate = this._datePipe.transform(new Date(), 'dd-MM-yyyy');

    this.dateControl.setValue(todaysDate);

    this.reservations$ = this._service.findAllByStatusAndDateAfter('Pending', todaysDate);
    this.statuses$ = this._service.findAllStatuses();

  }


}
