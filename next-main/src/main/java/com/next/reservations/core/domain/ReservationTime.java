/*
package com.next.reservations.core.domain;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(schema = "reservations", name = "reservation_times")
public class ReservationTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "time_of_day", nullable = false)
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "reservation_time_configuration_id", nullable = false)
    private ReservationTimeConfiguration reservationTimeConfiguration;

    public ReservationTime(){}

    public ReservationTime(LocalTime time, ReservationTimeConfiguration reservationTimeConfiguration) {
        this.time = time;
        this.reservationTimeConfiguration = reservationTimeConfiguration;
    }

    public Long getId() {
        return id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public ReservationTimeConfiguration getReservationTimeConfiguration() {
        return reservationTimeConfiguration;
    }

    public void setReservationTimeConfiguration(ReservationTimeConfiguration reservationTimeConfiguration) {
        this.reservationTimeConfiguration = reservationTimeConfiguration;
    }
}
*/
