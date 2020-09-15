package com.next.reservations.core.service;

import com.next.reservations.core.domain.Reservation;
import com.next.reservations.core.domain.ReservationDetails;
import com.next.reservations.core.domain.ReservationStatus;
import com.next.reservations.core.domain.ReservationTime;
import com.next.reservations.core.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ReservationService {

    private ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public Reservation create(ReservationStatus status, ReservationDetails reservationDetails, ReservationTime reservationTime,
                              Date reservationDate) {
        final Reservation reservation = new Reservation(status, reservationDetails, reservationDate, reservationTime);

        return repository.save(reservation);
    }

    public Reservation removeReservationDate(Long id) {
        final Reservation reservation = findById(id);

        reservation.setPreviousReservationDate(reservation.getReservationDate());
        reservation.setPreviousReservationTime(reservation.getReservationTime().getTime());
        reservation.setReservationDate(null);
        reservation.setReservationTime(null);

        return repository.save(reservation);
    }

    public Reservation rejectReservation(Long id){
        final Reservation reservation = findById(id);
        reservation.setStatus(ReservationStatus.REJECTED);

        return repository.save(reservation);
    }

    public Reservation acceptReservation(Long id){
        final Reservation reservation = findById(id);
        reservation.setStatus(ReservationStatus.ACCEPTED);

        return repository.save(reservation);
    }

    public Reservation setNewDateTime(Long id, Date date, ReservationTime time) {
        final Reservation reservation = findById(id);

        reservation.setReservationTime(time);
        reservation.setReservationDate(date);

        return repository.save(reservation);
    }

    public List<Reservation> findAllByStatus(ReservationStatus status) {
        return repository.findAllByStatus(status);
    }

   public List<Reservation> findAllByStatusAndReservationDate(ReservationStatus status, Date date){
        return repository.findAllByStatusAndReservationDate(status, date);
   }

    public Reservation findById(Long id){
        return repository.findById(id).orElseThrow();
    }

}
