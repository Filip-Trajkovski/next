package com.next.reservations.core.domain

import com.next.shared.domain.BaseEntity
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(schema = "reservations", name = "reservation_times")
class ReservationTime (
    @Column(name = "time_of_day")
    var time: LocalTime,

    @ManyToOne
    @JoinColumn(name = "reservation_time_configuration_id")
    var reservationTimeConfiguration: ReservationTimeConfiguration
): BaseEntity()
