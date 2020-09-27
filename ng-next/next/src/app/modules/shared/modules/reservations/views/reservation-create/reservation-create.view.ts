import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ReservationsService} from "../../services/reservations.service";
import {Option} from "../../../../interfaces/option.interface";
import {Observable} from "rxjs";
import * as moment from "moment";

@Component({
  selector: "reservation-create",
  templateUrl: "reservation-create.view.html",
  styleUrls: ["reservation-create.view.scss"]
})
export class ReservationCreateView implements OnInit {

  form: FormGroup = this._formDefinition;
  reservationTimes$: Observable<Option[]>;
  numberOfPlayers$: Observable<Option[]>;

  constructor(private _builder: FormBuilder,
              private _service: ReservationsService) {
    this.numberOfPlayers$ = this._service.findAllNumberOfPlayers();
  }

  ngOnInit() {
    this.form.get('date').valueChanges.subscribe(fullDate => {
      const parsedDate = new Date(fullDate);
      const formattedDate = moment(parsedDate).format("YYYY-MM-DD");
      this.form.get('reservationTimeId').setValue(null);
      this.reservationTimes$ = this._service.findUnreservedTimesForDate(formattedDate);
    })

  }

  private get _formDefinition() {
    return this._builder.group({
      date: [],
      fullName: [],
      email: [],
      phoneNumber: [],
      comment: [],
      numberOfPlayers: [],
      reservationTimeId: []
    })
  }

  onSubmit(){
    const date = this.form.get('date').value;
    const parsedDate = new Date(date);
    const formattedDate = moment(parsedDate).format("YYYY-MM-DD");

    this._service.makeReservation({...this.form.value, date: formattedDate});
  }
}
