package com.next.reservations.core.domain

import com.next.shared.domain.BaseEntity
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(schema = "reservations", name = "reservations")
class Reservation (
    @Column(name = "status")
    var status: ReservationStatus,

    @ManyToOne
    @JoinColumn(name = "reservation_details_id")
    var reservationDetails: ReservationDetails,

    @Column(name = "reservation_date")
    var reservationDate: LocalDate?,

    @ManyToOne
    @JoinColumn(name = "reservation_time_id")
    var reservationTime: ReservationTime?,

    @Column(name = "previous_reservation_date")
    var previousReservationDate: LocalDate?,

    @Column(name = "previous_reservation_time")
    var previousReservationTime: LocalTime?
): BaseEntity()
