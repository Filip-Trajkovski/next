package com.next.reservations.core.domain;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(schema = "reservations", name = "reservation")
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
    private Date reservationDate;

    @ManyToOne
    @JoinColumn(name = "reservation_time_id")
    private ReservationTime reservationTime;

    @Column(name = "previous_reservation_date")
    private Date previousReservationDate;

    @Column(name = "previous_reservation_time_id")
    private Time previousReservationTime;

    public Reservation(ReservationStatus status, ReservationDetails reservationDetails, Date reservationDate, ReservationTime reservationTime) {
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

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    public ReservationTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(ReservationTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Date getPreviousReservationDate() {
        return previousReservationDate;
    }

    public void setPreviousReservationDate(Date previousReservationDate) {
        this.previousReservationDate = previousReservationDate;
    }

    public Time getPreviousReservationTime() {
        return previousReservationTime;
    }

    public void setPreviousReservationTime(Time previousReservationTime) {
        this.previousReservationTime = previousReservationTime;
    }
}
