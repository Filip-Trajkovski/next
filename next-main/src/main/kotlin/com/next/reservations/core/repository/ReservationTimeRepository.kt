package com.next.reservations.core.repository

import com.next.reservations.core.domain.ReservationTime
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationTimeRepository : JpaRepository<ReservationTime, Long> {
    fun findAllByReservationTimeConfigurationId(reservationTimeConfigId: Long): List<ReservationTime>
}
