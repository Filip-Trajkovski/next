package com.next.reservations.core.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(schema = "reservations", name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "status", nullable = false)
    private ReservationStatus status;

    @ManyToOne
    @JoinColumn(name = "reservation_details_id", nullable = false)
    private ReservationDetails reservationDetails;

    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @ManyToOne
    @JoinColumn(name = "reservation_time_id")
    private ReservationTime reservationTime;

    @Column(name = "previous_reservation_date")
    private LocalDate previousReservationDate;

    @Column(name = "previous_reservation_time")
    private LocalTime previousReservationTime;

    public Reservation(){}

    public Reservation(ReservationStatus status, ReservationDetails reservationDetails, LocalDate reservationDate, ReservationTime reservationTime) {
        this.status = status;
        this.reservationDetails = reservationDetails;
        this.reservationDate = reservationDate;
        this.reservationTime = reservationTime;
    }

    public Long getId() {
        return id;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public ReservationDetails getReservationDetails() {
        return reservationDetails;
    }

    public void setReservationDetails(ReservationDetails reservationDetails) {
        this.reservationDetails = reservationDetails;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(ReservationTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public LocalDate getPreviousReservationDate() {
        return previousReservationDate;
    }

    public void setPreviousReservationDate(LocalDate previousReservationDate) {
        this.previousReservationDate = previousReservationDate;
    }

    public LocalTime getPreviousReservationTime() {
        return previousReservationTime;
    }

    public void setPreviousReservationTime(LocalTime previousReservationTime) {
        this.previousReservationTime = previousReservationTime;
    }
}
