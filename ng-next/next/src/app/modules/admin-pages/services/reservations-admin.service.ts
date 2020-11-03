import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Reservation} from "../../shared/interfaces/reservation.interface";
import {ReservationStatus} from "../../shared/enums/reservation-status.enum";
import {Option} from "../../shared/interfaces/option.interface";
import {TimeChange} from "../interfaces/time-change.interface";

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

  findAllByStatusAndDateAfter(status: string, date: string):Observable<Reservation[]> {
    return this._http.get<Reservation[]>(`${this.path}/find-all-by-status?status=${status}&date=${date}`);
  }

  findAllStatuses(): Observable<Option[]> {
    return this._http.get<Option[]>(`${this.path}/statuses`);
  }

  acceptReservation(id: number): Observable<void> {
    return this._http.post<void>(`${this.path}/accept/${id}`, {});
  }

  rejectReservation(id: number): Observable<void> {
    return this._http.post<void>(`${this.path}/reject/${id}`, {});
  }

  changeDateAndAccept(timeChange: TimeChange): Observable<void>{
    return this._http.post<void>(`${this.path}/change-time`, timeChange);
  }
}
