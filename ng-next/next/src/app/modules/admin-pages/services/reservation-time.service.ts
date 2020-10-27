import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {ReservationTime} from "../interfaces/reservation-time.interface";
import {Observable} from "rxjs";

@Injectable()
export class ReservationTimeService {

  readonly path = "api/reservation-times";

  constructor(private _http: HttpClient){}

  createOrUpdate(reservationTime: ReservationTime): Observable<number> {
    return this._http.post<number>(`${this.path}`, reservationTime);
  }

  findAllByConfigId(configId: number): Observable<ReservationTime[]> {
    return this._http.get<ReservationTime[]>(`${this.path}/${configId}/by-config`);
  }

  deleteById(id: number): Observable<void> {
    return this._http.delete<void>(`${this.path}/${id}`);
  }
}
