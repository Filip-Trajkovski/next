import {NgModule} from "@angular/core";
import {ReservationTimeConfigurationsPage} from "./pages/reservation-time-configurations/reservation-time-configurations.page";
import {AdminPagesRoutingModule} from "./admin-pages-routing.module";
import {ReservationTimeService} from "./services/reservation-time.service";
import {ReservationTimeConfigService} from "./services/reservation-time-config.service";
import {ReservationsAdminService} from "./services/reservations-admin.service";
import {CommonModule} from "@angular/common";
import {
  MatCheckboxModule,
  MatDatepickerModule,
  MatDialogModule,
  MatInputModule,
  MatSelectModule
} from "@angular/material";
import {HttpClientModule} from "@angular/common/http";
import {ReservationComponent} from "./components/reservation/reservation.component";
import {ReservationRemovalDialog} from "./dialogs/reservation-removal/reservation-removal.dialog";
import {EditTimesDialog} from "./dialogs/edit-times/edit-times.dialog";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgxMaterialTimepickerModule} from "ngx-material-timepicker";

const modules = [
  AdminPagesRoutingModule,
  CommonModule,
  MatCheckboxModule,
  HttpClientModule,
  MatDialogModule,
  ReactiveFormsModule,
  NgxMaterialTimepickerModule,
  MatDatepickerModule,
  MatSelectModule
];

const views = [];

const dialogs = [
  ReservationRemovalDialog,
  EditTimesDialog
];

const pages = [
  ReservationTimeConfigurationsPage
];

const components = [
  ReservationComponent
];

const services = [
  ReservationTimeService,
  ReservationTimeConfigService,
  ReservationsAdminService
];

@NgModule({
  providers: [...services],
  imports: [...modules, MatInputModule, FormsModule],
  declarations: [...pages, ...views, ...components, ...dialogs],
  entryComponents: [...dialogs]
})
export class AdminPagesModule {
}