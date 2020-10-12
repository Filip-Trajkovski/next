/*
package com.next.reservations.core.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(schema = "reservations", name = "alternative_reservation_time_date_ranges")
public class AlternativeReservationTimeDateRange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date", nullable = false)
    private LocalDate toDate;

    @ManyToOne
    @JoinColumn(name = "reservation_time_configuration_id", nullable = false)
    private ReservationTimeConfiguration reservationTimeConfiguration;

    public AlternativeReservationTimeDateRange(){}

    public AlternativeReservationTimeDateRange(LocalDate fromDate, LocalDate toDate, ReservationTimeConfiguration reservationTimeConfiguration) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reservationTimeConfiguration = reservationTimeConfiguration;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public ReservationTimeConfiguration getReservationTimeConfiguration() {
        return reservationTimeConfiguration;
    }

    public void setReservationTimeConfiguration(ReservationTimeConfiguration reservationTimeConfiguration) {
        this.reservationTimeConfiguration = reservationTimeConfiguration;
    }
}
*/
