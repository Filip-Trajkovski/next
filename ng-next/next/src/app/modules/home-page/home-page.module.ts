import {NgModule} from "@angular/core";
import {HomePagePage} from "./pages/home-page/home-page.page";
import {ReservationsModule} from "../shared/modules/reservations/reservations.module";
import {HomePageRoutingModule} from "./home-page-routing.module";
import {NavigationBarView} from "./views/navigation-bar/navigation-bar.view";
import {AboutUsView} from "./views/about-us/about-us.view";
import {UpperHomePageSectionView} from "./views/upper-home-page-section/upper-home-page-section.view";
import {ReservationHomePageSectionView} from "./views/reservation-home-page-section/reservation-home-page-section.view";
import {ContactUsView} from "./views/contact-us/contact-us.view";

const modules = [
  HomePageRoutingModule,
  ReservationsModule
];

const pages = [
  HomePagePage
];

const views = [
  NavigationBarView,
  AboutUsView,
  UpperHomePageSectionView,
  ReservationHomePageSectionView,
  ContactUsView
];

const services = [];

@NgModule({
  declarations: [...pages, ...views],
  imports: [...modules],
  providers: [...services],
  entryComponents: []
})
export class HomePageModule{}
