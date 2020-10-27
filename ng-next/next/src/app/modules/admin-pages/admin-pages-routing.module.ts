import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {ReservationTimeConfigurationsPage} from "./pages/reservation-time-configurations/reservation-time-configurations.page";

const routes: Routes = [
  {
    path: 'reservation-time-configurations',
    component: ReservationTimeConfigurationsPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminPagesRoutingModule {
}
