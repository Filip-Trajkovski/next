import {Component, OnInit, ViewChild} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ReservationsService} from "../../services/reservations.service";
import {Option} from "../../../../interfaces/option.interface";
import {Observable, of} from "rxjs";
import * as moment from "moment";
import {MatDatepicker, MatDialog} from "@angular/material";
import {MessageDialog} from "../../dialogs/message/message.dialog";

@Component({
  selector: "reservation-create",
  templateUrl: "reservation-create.view.html",
  styleUrls: ["reservation-create.view.scss"]
})
export class ReservationCreateView implements OnInit {
  @ViewChild('picker', {static: false})
  private picker: MatDatepicker<string>;

  form: FormGroup = this._formDefinition;
  reservationTimes$: Observable<Option[]>;
  numberOfPlayers$: Observable<Option[]>;

  invalidDate: boolean = false;

  constructor(private _builder: FormBuilder,
              private _service: ReservationsService,
              private _dialog: MatDialog) {
    this.numberOfPlayers$ = this._service.findAllNumberOfPlayers();
  }

  ngOnInit() {
    this.form.get('date').valueChanges.subscribe(fullDate => {
      const parsedDate = new Date(fullDate);
      const formattedDate = moment(parsedDate).format("YYYY-MM-DD");
      this.form.get('reservationTimeId').setValue(null);
      this.reservationTimes$ = this._service.findUnreservedTimesForDate(formattedDate);

      this.reservationTimes$.subscribe(times => {
        if (times.length == 0 && this.form.get('date').value) {
          this.invalidDate = true;
          this.form.get('reservationTimeId').disable();
        } else {
          this.invalidDate = false;
          this.form.get('reservationTimeId').enable();
        }
      })
    });

  }

  private get _formDefinition() {
    return this._builder.group({
      date: ['', Validators.required],
      fullName: ['', Validators.required],
      email: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      comment: [],
      numberOfPlayers: ['', Validators.required],
      reservationTimeId: ['', Validators.required]
    })
  }

  onSubmit() {
    const date = this.form.get('date').value;
    const parsedDate = new Date(date);
    const formattedDate = moment(parsedDate).format("YYYY-MM-DD");

    this._service.makeReservation({...this.form.value, date: formattedDate}).subscribe(() => {
      this._dialog.open(MessageDialog, {
        data: { text: "Вашата резервација е успешно испратена!"}
      });
      this.form.reset();
    }, error => {
      this._dialog.open(MessageDialog, {
        data: { text: "Имаше проблем при резервација, ве молиме обидете се повторно."}
      })
    });
  }

  onDatepickerInputClick() {
    this.picker.open();
  }


}
