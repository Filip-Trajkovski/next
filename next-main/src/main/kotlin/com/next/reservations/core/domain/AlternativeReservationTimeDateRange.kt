package com.next.reservations.core.domain

import com.next.shared.domain.BaseEntity
import java.time.LocalDate
import javax.persistence.*


@Entity
@Table(schema = "reservations", name = "alternative_reservation_time_date_ranges")
class AlternativeReservationTimeDateRange(
        @Column(name = "from_date")
        var fromDate: LocalDate,

        @Column(name = "to_date")
        var toDate: LocalDate,

        @ManyToOne
        @JoinColumn(name = "reservation_time_configuration_id")
        var reservationTimeConfiguration: ReservationTimeConfiguration
): BaseEntity()
