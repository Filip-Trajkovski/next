import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Option} from "../../../interfaces/option.interface";
import {Observable} from "rxjs";
import {Reservation} from "../../../interfaces/reservation.interface";

@Injectable()
export class ReservationsService {
  private readonly path = 'api/public/reservations';

  constructor(private _http: HttpClient){}

  findUnreservedTimesForDate(date: string): Observable<Option[]> {
    return this._http.get<Option[]>(`${this.path}/times-by-date/${date}`);
  }

  makeReservation(reservation: Reservation): Observable<number> {
    return this._http.post<number>(`${this.path}`, reservation);
  }

  findAllNumberOfPlayers(): Observable<Option[]> {
    return this._http.get<Option[]>(`assets/data/number-of-players.json`);
  }
}
