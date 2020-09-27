import {RouterModule, Routes} from "@angular/router";
import {HomePagePage} from "./pages/home-page/home-page.page";
import {NgModule} from "@angular/core";

const routes: Routes = [
  {
    path: '',
    component: HomePagePage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomePageRoutingModule {}
