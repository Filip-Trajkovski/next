package com.next.reservations.core.service

import com.next.reservations.core.domain.Reservation
import com.next.reservations.core.domain.ReservationDetails
import com.next.reservations.core.domain.ReservationStatus
import com.next.reservations.core.domain.ReservationTime
import com.next.reservations.core.repository.ReservationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class ReservationService(private val repository: ReservationRepository) {

    val activeReservationStatuses = listOf(ReservationStatus.ACCEPTED, ReservationStatus.PENDING)

    val inactiveReservationStatuses = listOf(ReservationStatus.REMOVED, ReservationStatus.REJECTED,
            ReservationStatus.FINISHED)


    fun create(status: ReservationStatus, reservationDetails: ReservationDetails, reservationTime: ReservationTime,
               reservationDate: LocalDate): Reservation {
        val reservation = Reservation(status, reservationDetails,
                reservationDate, reservationTime, null, null)

        return repository.save(reservation)
    }

    @Transactional
    fun removeReservationsByIds(ids: List<Long>) {
        ids.forEach { removeReservation(it) }
    }

    fun removeReservation(id: Long): Reservation {
        val reservation = findById(id)
        reservation.status = ReservationStatus.REMOVED
        setPreviousReservationDateTime(reservation)
        return repository.save(reservation)
    }

    fun setPreviousReservationDateTime(reservation: Reservation) {
        reservation.previousReservationDate = reservation.reservationDate
        reservation.previousReservationTime = reservation.reservationTime!!.time
        reservation.reservationDate = null
        reservation.reservationTime = null
        repository.save(reservation)
    }

    fun rejectReservation(id: Long): Reservation {
        val reservation = findById(id)
        setPreviousReservationDateTime(reservation)
        reservation.status = ReservationStatus.REJECTED
        return repository.save(reservation)
    }

    fun acceptReservation(id: Long): Reservation {
        val reservation = findById(id)
        reservation.status = ReservationStatus.ACCEPTED
        return repository.save(reservation)
    }

    fun setNewDateTime(id: Long, date: LocalDate, time: ReservationTime): Reservation {
        val reservation = findById(id)
        reservation.reservationTime = time
        reservation.reservationDate = date
        return repository.save(reservation)
    }

    fun findAllByStatusAndDateAfter(status: ReservationStatus, date: LocalDate): List<Reservation> {
        return if(status in activeReservationStatuses)
            repository.findAllByStatusAndReservationDateAfter(status, date)
        else repository.findAllByStatusAndPreviousReservationDateAfter(status, date)
    }

    fun findAllByStatusInAndReservationDate(status: List<ReservationStatus>, date: LocalDate): List<Reservation> {
        return repository.findAllByStatusInAndReservationDate(status, date)
    }

    fun findById(id: Long): Reservation {
        return repository.findById(id).orElseThrow()
    }

    fun findAllByStatusInAndReservationTimeIdInAndReservationDateAfter(status: List<ReservationStatus>,
                                                                       reservationTimeIds: List<Long>,
                                                                       afterDate: LocalDate): List<Reservation> {
        return repository.findAllByStatusInAndReservationTimeIdInAndReservationDateAfter(status, reservationTimeIds, afterDate)
    }
}
