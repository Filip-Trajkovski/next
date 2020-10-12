package com.next.reservations.core.domain

import com.next.shared.domain.BaseEntity
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(schema = "reservations", name = "reservation_time_configurations")
class ReservationTimeConfiguration(
        @Column(name = "name")
        var name: String,

        @Column(name = "default_config")
        var defaultConfig: Boolean,

        @Column(name = "default_start_date")
        var defaultStartDate: LocalDate?
): BaseEntity()
