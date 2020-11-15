import {Component} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";

@Component({
  templateUrl: "login.page.html",
  styleUrls: ["login.page.scss"]
})
export class LoginPage {

  form = this._formDefinition;


  constructor(private _builder: FormBuilder,
              private _authService: AuthenticationService,
              private _router: Router) {
  }

  private get _formDefinition(): FormGroup {
    return this._builder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    })
  }


  onSubmit() {
    if (this._authService.authenticate(this.form.value)) {
      this._router.navigate(['admin-pages']);
    }
  }
}
