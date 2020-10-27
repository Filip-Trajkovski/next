import {Component} from "@angular/core";
import {ReservationTimeConfigService} from "../../services/reservation-time-config.service";
import {Observable} from "rxjs";
import {ReservationTimeConfig} from "../../interfaces/reservation-time-config.interface";
import {MatDialog} from "@angular/material";
import {ReservationRemovalDialog} from "../../dialogs/reservation-removal/reservation-removal.dialog";
import {EditTimesDialog} from "../../dialogs/edit-times/edit-times.dialog";

@Component({
  templateUrl: "reservation-time-configurations.page.html",
  styleUrls: ["reservation-time-configurations.page.scss"]
})
export class ReservationTimeConfigurationsPage {

  configs: Observable<ReservationTimeConfig[]>;
  newName: string = '';
  addNew: boolean = false;

  constructor(private _service: ReservationTimeConfigService,
              private _dialogRef: MatDialog) {
    this.configs = this._service.getAll();
  }

  openEditTimes(id: number, def: boolean, name: string) {
    const editRef = this._dialogRef.open(EditTimesDialog, {
      data: {id: id, def: def, name: name}
    })

    editRef.afterClosed().subscribe(() =>
      this.configs = this._service.getAll())

  }

  openDelete(id: number) {
    const deleteRef = this._dialogRef.open(ReservationRemovalDialog, {
      data: {id: id, type: 'CONFIG'}
    });

    deleteRef.afterClosed().subscribe(() =>
      this.configs = this._service.getAll())
  }

  openAddNew(){
    this.addNew = !this.addNew;
  }

  addNewConfig(){
    this._service.addNew(this.newName).subscribe();
    this.newName = '';
    this.addNew = false;
    this.configs = this._service.getAll();
  }
}
