package com.next.reservations.core.repository;

import com.next.reservations.core.domain.Reservation;
import com.next.reservations.core.domain.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByStatus(ReservationStatus status);

    List<Reservation> findAllByStatusAndReservationDate(ReservationStatus status, Date date);
}