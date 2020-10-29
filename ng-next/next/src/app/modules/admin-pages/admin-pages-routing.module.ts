import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {ReservationTimeConfigurationsPage} from "./pages/reservation-time-configurations/reservation-time-configurations.page";
import {ReservationsListPage} from "./pages/reservations-list/reservations-list.page";

const routes: Routes = [
  {
    path: 'reservation-time-configurations',
    component: ReservationTimeConfigurationsPage
  },
  {
    path: 'reservations',
    component: ReservationsListPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminPagesRoutingModule {
}
