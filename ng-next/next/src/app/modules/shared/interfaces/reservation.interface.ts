export interface Reservation{
  id: number;
  fullName: string;
  email: string;
  phoneNumber: string;
  comment: string;
  numberOfPlayers: number;
  reservationTimeId: number;
  date: string;
  status: number;
  previousReservationTimeDate: string;
  currentReservationTimeDate: string;
}
