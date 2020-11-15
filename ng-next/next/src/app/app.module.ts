import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgxMaterialTimepickerModule} from "ngx-material-timepicker";
import {LoginPage} from "./pages/login/login.page";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule, MatSelectModule} from "@angular/material";
import {AuthenticationService} from "./services/authentication.service";
import {AuthGuardService} from "./services/auth-guard.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginPage
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgxMaterialTimepickerModule,
    FormsModule,
    MatSelectModule,
    MatInputModule,
    ReactiveFormsModule
  ],
  providers: [AuthenticationService, AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
