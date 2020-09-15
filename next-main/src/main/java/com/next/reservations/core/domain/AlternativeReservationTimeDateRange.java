package com.next.reservations.core.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "reservations", name = "alternative_reservation_time_date_ranges")
public class AlternativeReservationTimeDateRange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "from_date", nullable = false)
    private Date fromDate;

    @Column(name = "to_date", nullable = false)
    private Date toDate;

    @ManyToOne
    @JoinColumn(name = "reservation_time_configuration_id", nullable = false)
    private ReservationTimeConfiguration reservationTimeConfiguration;

    public AlternativeReservationTimeDateRange(Date fromDate, Date toDate, ReservationTimeConfiguration reservationTimeConfiguration) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.reservationTimeConfiguration = reservationTimeConfiguration;
    }

    public Long getId() {
        return id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public ReservationTimeConfiguration getReservationTimeConfiguration() {
        return reservationTimeConfiguration;
    }

    public void setReservationTimeConfiguration(ReservationTimeConfiguration reservationTimeConfiguration) {
        this.reservationTimeConfiguration = reservationTimeConfiguration;
    }
}
