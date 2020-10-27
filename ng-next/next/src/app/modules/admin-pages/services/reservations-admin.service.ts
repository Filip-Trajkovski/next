import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Reservation} from "../../shared/interfaces/reservation.interface";

@Injectable()
export class ReservationsAdminService {
  readonly path = 'api/reservations';

  constructor(private _http: HttpClient) {}

  findAllActiveByConfigId(configId: number): Observable<Reservation[]> {
    return this._http.get<Reservation[]>(`${this.path}/by-time-config/${configId}`);
  }

  findAllActiveByTimeId(timeId: number): Observable<Reservation[]> {
    return this._http.get<Reservation[]>(`${this.path}/by-reservation-time/${timeId}`);
  }

  findAllByCurrentDefault(): Observable<Reservation[]> {
    return this._http.get<Reservation[]>(`${this.path}/default`);
  }

  findAllInvalidByNewFutureDefaultDate(futureDefault: string): Observable<Reservation[]> {
    return this._http.get<Reservation[]>(`${this.path}/by-new-future-default/${futureDefault}`);
  }
}
