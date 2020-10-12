package com.next.reservations.core.service

import com.next.reservations.core.domain.Reservation
import com.next.reservations.core.domain.ReservationDetails
import com.next.reservations.core.domain.ReservationStatus
import com.next.reservations.core.domain.ReservationTime
import com.next.reservations.core.repository.ReservationRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ReservationService(private val repository: ReservationRepository) {
    fun create(status: ReservationStatus, reservationDetails: ReservationDetails, reservationTime: ReservationTime,
               reservationDate: LocalDate): Reservation {
        val reservation = Reservation(status, reservationDetails,
                reservationDate, reservationTime, null, null)

        return repository.save(reservation)
    }

    fun removeReservationDate(id: Long): Reservation {
        val reservation = findById(id)
        reservation.previousReservationDate = reservation.reservationDate
        reservation.previousReservationTime = reservation.reservationTime!!.time
        reservation.reservationDate = null
        reservation.reservationTime = null
        return repository.save(reservation)
    }

    fun rejectReservation(id: Long): Reservation {
        val reservation = findById(id)
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

    fun findAllByStatus(status: ReservationStatus): List<Reservation> {
        return repository.findAllByStatus(status)
    }

    fun findAllByStatusInAndReservationDate(status: List<ReservationStatus>, date: LocalDate): List<Reservation> {
        return repository.findAllByStatusInAndReservationDate(status, date)
    }

    fun findById(id: Long): Reservation {
        return repository.findById(id).orElseThrow()
    }

}