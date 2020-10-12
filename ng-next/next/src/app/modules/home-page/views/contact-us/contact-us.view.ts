import {Component} from "@angular/core";

@Component({
  selector: "contact-us",
  templateUrl: "contact-us.view.html",
  styleUrls: ["contact-us.view.scss"]
})
export class ContactUsView {
  mapLink: string = 'https://www.google.com/maps/place/Escape+Room+NEXT/@41.9894673,21.4578213,17z/data=!4m5!3m4!1s0x135415c786811a29:0x9038e493d4f7e9db!8m2!3d41.9897265!4d21.4589907';

  facebookLink: string = 'https://www.facebook.com';
  instagramLink: string = 'https://www.instagram.com';

  constructor(){}

  onLinkClick(link){
    (window as any).open(link, "_blank");
  }
}
