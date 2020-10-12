package com.next.reservations.core.repository

import com.next.reservations.core.domain.ReservationTimeConfiguration
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationTimeConfigurationRepository : JpaRepository<ReservationTimeConfiguration, Long> {
    fun findByDefaultConfigTrue(): ReservationTimeConfiguration
    fun findByDefaultStartDateIsNotNull(): ReservationTimeConfiguration?
}
