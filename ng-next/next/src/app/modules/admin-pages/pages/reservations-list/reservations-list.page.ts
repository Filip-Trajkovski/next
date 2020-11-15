import {Component, ViewChild} from "@angular/core";
import {Observable} from "rxjs";
import {Option} from "../../../shared/interfaces/option.interface";
import {ReservationsAdminService} from "../../services/reservations-admin.service";
import {Reservation} from "../../../shared/interfaces/reservation.interface";
import {DatePipe} from "@angular/common";
import {Moment} from "moment";
import {MatDatepicker} from "@angular/material";
import {FormControl} from "@angular/forms";

@Component({
  templateUrl: "reservations-list.page.html",
  styleUrls: ["reservations-list.page.scss"]
})
export class ReservationsListPage {
  @ViewChild('picker', {static: false})
  private picker: MatDatepicker<string>;

  statuses$: Observable<Option[]>;
  reservations$: Observable<Reservation[]>;

  selectedStatus: string;
  selectedDate: Moment;

  date = new Date((new Date().getTime() - 3888000000));

  constructor(private _service: ReservationsAdminService,
              private _datePipe: DatePipe) {
    this.reservations$ = this._service.findAllByStatusAndDateAfter('Pending', this._todaysDate);
    this.statuses$ = this._service.findAllStatuses();

  }

  private get _todaysDate(): string {
    return this._datePipe.transform(new Date(), 'dd-MM-yyyy');
  }

  onFilterChange() {
    this.reservations$ = this._service.findAllByStatusAndDateAfter(this.selectedStatus, this.selectedDate.format("DD-MM-yyyy"));
  }

  onDatepickerInputClick() {
    this.picker.open();
  }
}
