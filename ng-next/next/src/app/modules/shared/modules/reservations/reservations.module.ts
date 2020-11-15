import {NgModule} from "@angular/core";
import {ReservationCreateView} from "./views/reservation-create/reservation-create.view";
import {ReactiveFormsModule} from "@angular/forms";
import {
  DateAdapter,
  MAT_DATE_FORMATS,
  MAT_DATE_LOCALE,
  MatDatepickerModule,
  MatInputModule,
  MatSelectModule
} from "@angular/material";
import {ReservationsService} from "./services/reservations.service";
import {HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {MatMomentDateModule, MomentDateAdapter} from "@angular/material-moment-adapter";
import {MessageDialog} from "./dialogs/message/message.dialog";

const MY_FORMATS = {
  parse: {
    dateInput: 'LL',
  },
  display: {
    dateInput: 'DD-MM-YYYY',
    monthYearLabel: 'YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'YYYY',
  },
};

const dialogs = [
  MessageDialog
];

const views = [
  ReservationCreateView
];

const modules = [
  ReactiveFormsModule,
  MatDatepickerModule,
  HttpClientModule,
  MatMomentDateModule,
  MatSelectModule,
  MatInputModule,
  CommonModule
];

const services = [
  ReservationsService
];

const dateFormat = [
  {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
  {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS}
]

@NgModule({
  declarations: [...views, ...dialogs],
  imports: [...modules],
  providers: [...services, ...dateFormat],
  exports: [...views],
  bootstrap: [],
  entryComponents: [...dialogs]
})
export class ReservationsModule {
}
