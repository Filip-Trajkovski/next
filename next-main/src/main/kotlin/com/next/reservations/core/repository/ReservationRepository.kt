package com.next.reservations.core.repository

import com.next.reservations.core.domain.Reservation
import com.next.reservations.core.domain.ReservationStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface ReservationRepository : JpaRepository<Reservation, Long> {
    fun findAllByStatus(status: ReservationStatus): List<Reservation>
    fun findAllByStatusInAndReservationDate(status: List<ReservationStatus>, date: LocalDate): List<Reservation>

    fun findAllByStatusInAndReservationTimeIdInAndReservationDateAfter(status: List<ReservationStatus>,
                                                                       reservationDateIds: List<Long>,
                                                                       reservationDate: LocalDate): List<Reservation>

    fun findAllByStatusAndReservationDateAfter(status: ReservationStatus, date: LocalDate): List<Reservation>

    fun findAllByStatusAndPreviousReservationDateAfter(status: ReservationStatus, date: LocalDate): List<Reservation>
}
