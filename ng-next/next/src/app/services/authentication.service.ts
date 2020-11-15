import {Injectable} from "@angular/core";
import {User} from "../interfaces/user.interface";

@Injectable()
export class AuthenticationService {

  loggedIn: boolean;

  constructor() {
  }

  authenticate(user: User): boolean {
    if (user.username === "mefo110" && user.password === "mztskopje") {
      sessionStorage.setItem('username', user.username);
      this.loggedIn = true;
      return true;
    } else {
      this.loggedIn = false;
      return false;
    }
  }

  isUserLoggedIn(): boolean {
    console.log(this.loggedIn);
    return this.loggedIn;
  }

}
