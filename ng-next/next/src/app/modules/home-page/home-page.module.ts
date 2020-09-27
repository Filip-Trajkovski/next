import {NgModule} from "@angular/core";
import {HomePagePage} from "./pages/home-page/home-page.page";
import {ReservationsModule} from "../shared/modules/reservations/reservations.module";
import {HomePageRoutingModule} from "./home-page-routing.module";

const modules = [
  HomePageRoutingModule,
  ReservationsModule
];

const pages = [
  HomePagePage
];

const views = [];

const services = [];

@NgModule({
  declarations: [...pages, ...views],
  imports: [...modules],
  providers: [...services],
  entryComponents: []
})
export class HomePageModule{}
