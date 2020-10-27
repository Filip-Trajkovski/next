import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {ReservationTimeConfig} from "../interfaces/reservation-time-config.interface";
import {ObjectUnsubscribedError, Observable} from "rxjs";

@Injectable()
export class ReservationTimeConfigService {

  readonly path = 'api/reservation-time-configs';

  constructor(private _http: HttpClient){}

  createOrUpdate(reservationTimeConfig: ReservationTimeConfig): Observable<number> {
    return this._http.post<number>(`${this.path}`, reservationTimeConfig);
  }

  getAll(): Observable<ReservationTimeConfig[]>{
    return this._http.get<ReservationTimeConfig[]>(`${this.path}`);
  }

  changeDefaultConfig(newDefaultId: number): Observable<void> {
    return this._http.post<void>(`${this.path}/new-default/${newDefaultId}`, {});
  }

  deleteById(id: number): Observable<void> {
    return this._http.delete<void>(`${this.path}/${id}`);
  }

  changeName(id: number, name: string): Observable<void> {
    return this._http.post<void>(`${this.path}/${id}/new-name/${name}`, {})
  }

  addNew(name: string): Observable<number> {
    return this._http.post<number>(`${this.path}/add-new/${name}`, {});
  }

  changeFutureDefault(id: number, date: string): Observable<void> {
    return this._http.post<void>(`${this.path}/new-future-default/${id}/${date}`, {})
  }
}
