import {NgModule} from "@angular/core";
import {ReservationTimeConfigurationsPage} from "./pages/reservation-time-configurations/reservation-time-configurations.page";
import {AdminPagesRoutingModule} from "./admin-pages-routing.module";
import {ReservationTimeService} from "./services/reservation-time.service";
import {ReservationTimeConfigService} from "./services/reservation-time-config.service";
import {ReservationsAdminService} from "./services/reservations-admin.service";
import {CommonModule, DatePipe} from "@angular/common";
import {
  MatCheckboxModule,
  MatDatepickerModule,
  MatDialogModule,
  MatInputModule, MatRadioModule,
  MatSelectModule, MatTooltipModule
} from "@angular/material";
import {HttpClientModule} from "@angular/common/http";
import {ReservationComponent} from "./components/reservation/reservation.component";
import {ReservationRemovalDialog} from "./dialogs/reservation-removal/reservation-removal.dialog";
import {EditTimesDialog} from "./dialogs/edit-times/edit-times.dialog";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgxMaterialTimepickerModule} from "ngx-material-timepicker";
import {ReservationsListPage} from "./pages/reservations-list/reservations-list.page";
import {MatMomentDateModule} from "@angular/material-moment-adapter";
import {ChangeDateDialog} from "./dialogs/change-date/change-date.dialog";
import {ReservationsService} from "../shared/modules/reservations/services/reservations.service";
import {AdminNavMenuPage} from "./pages/admin-nav-menu/admin-nav-menu.page";

const modules = [
  AdminPagesRoutingModule,
  CommonModule,
  MatCheckboxModule,
  HttpClientModule,
  MatDialogModule,
  ReactiveFormsModule,
  NgxMaterialTimepickerModule,
  MatDatepickerModule,
  MatSelectModule,
  MatMomentDateModule,
  FormsModule,
  MatRadioModule
];

const views = [];

const dialogs = [
  ReservationRemovalDialog,
  EditTimesDialog,
  ChangeDateDialog
];

const pages = [
  ReservationTimeConfigurationsPage,
  ReservationsListPage,
  AdminNavMenuPage
];

const components = [
  ReservationComponent
];

const services = [
  ReservationTimeService,
  ReservationTimeConfigService,
  ReservationsAdminService,
  DatePipe,
  ReservationsService
];

@NgModule({
  providers: [...services],
  imports: [...modules, MatInputModule, MatTooltipModule],
  declarations: [...pages, ...views, ...components, ...dialogs],
  entryComponents: [...dialogs]
})
export class AdminPagesModule {
}
