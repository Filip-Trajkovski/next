import {Component} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  templateUrl: "admin-nav-menu.page.html",
  styleUrls: ["admin-nav-menu.page.scss"]
})
export class AdminNavMenuPage {

  constructor(private _router: Router,
              private _route: ActivatedRoute) {
  }


  navigateTo(url) {
    this._router.navigate([url], {relativeTo: this._route})
  }
}
