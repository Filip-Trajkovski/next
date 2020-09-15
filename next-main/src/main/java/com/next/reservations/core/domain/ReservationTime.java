package com.next.reservations.core.domain;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(schema = "reservations", name = "reservation_time")
public class ReservationTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "time", nullable = false)
    private Time time;

    @ManyToOne
    @JoinColumn(name = "reservation_time_configuration_id", nullable = false)
    private ReservationTimeConfiguration reservationTimeConfiguration;

    public ReservationTime(Time time, ReservationTimeConfiguration reservationTimeConfiguration) {
        this.time = time;
        this.reservationTimeConfiguration = reservationTimeConfiguration;
    }

    public Long getId() {
        return id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public ReservationTimeConfiguration getReservationTimeConfiguration() {
        return reservationTimeConfiguration;
    }

    public void setReservationTimeConfiguration(ReservationTimeConfiguration reservationTimeConfiguration) {
        this.reservationTimeConfiguration = reservationTimeConfiguration;
    }
}
