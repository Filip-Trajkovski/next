package com.next.reservations.core.service

import com.next.reservations.core.domain.Reservation
import com.next.reservations.core.domain.ReservationDetails
import com.next.reservations.core.domain.ReservationStatus
import com.next.reservations.core.domain.ReservationTime
import com.next.reservations.core.repository.ReservationRepository
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalTime

@Service
@EnableScheduling
class ReservationService(private val repository: ReservationRepository) {

    val activeReservationStatuses = listOf(ReservationStatus.ACCEPTED, ReservationStatus.PENDING)

    val inactiveReservationStatuses = listOf(ReservationStatus.REMOVED, ReservationStatus.REJECTED,
            ReservationStatus.FINISHED)


    fun create(status: ReservationStatus, reservationDetails: ReservationDetails, reservationTime: ReservationTime,
               reservationDate: LocalDate): Reservation {
        validateCanMakeReservation(reservationDate, reservationTime.id)
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
        if (reservation.status !in listOf(ReservationStatus.PENDING, ReservationStatus.ACCEPTED))
            throw RuntimeException("YOU CANNOT REJECT A RESERVATION WITH THE STATUS ${reservation.status}")
        setPreviousReservationDateTime(reservation)
        reservation.status = ReservationStatus.REJECTED
        return repository.save(reservation)
    }

    fun acceptReservation(id: Long): Reservation {
        val reservation = findById(id)
        if (reservation.status != ReservationStatus.PENDING)
            throw RuntimeException("YOU CANNOT ACCEPT A RESERVATION WITH THE STATUS ${reservation.status}")
        reservation.status = ReservationStatus.ACCEPTED
        return repository.save(reservation)
    }

    fun findAllByStatusAndDateAfter(status: ReservationStatus, date: LocalDate): List<Reservation> {
        return if (status in activeReservationStatuses)
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

    fun changeTimeAndAccept(id: Long, date: LocalDate, reservationTime: ReservationTime) {
        validateCanMakeReservation(date, reservationTime.id)
        val reservation = findById(id)
        if (reservation.reservationTime != null && reservation.reservationDate != null) {
            reservation.previousReservationDate = reservation.reservationDate
            reservation.previousReservationTime = reservation.reservationTime!!.time
        }
        reservation.reservationDate = date
        reservation.reservationTime = reservationTime
        reservation.status = ReservationStatus.ACCEPTED
        repository.save(reservation)
    }

    private fun validateCanMakeReservation(date: LocalDate, reservationTimeId: Long) {
        val reservationTimeIds = repository.findAllByStatusInAndReservationDate(activeReservationStatuses, date).map { it.reservationTime!!.id }
        if (reservationTimeId in reservationTimeIds)
            throw RuntimeException("THAT RESERVATION SLOT IS ALREADY TAKEN")
    }


    @Scheduled(fixedDelay = 900000)
    @Transactional
    fun findFinishedReservations() {
        val fiveDaysBeforeToday = LocalDate.now().minusDays(5)
        val currentDate = LocalDate.now()
        val currentTime = LocalTime.now()

        val reservations = repository.findAllByReservationDateAfter(fiveDaysBeforeToday)

        reservations.forEach {
            if (it.reservationDate!! < currentDate ||
                    (it.reservationDate == currentDate && it.reservationTime!!.time.isBefore(currentTime))) {
                it.previousReservationDate = it.reservationDate
                it.previousReservationTime = it.reservationTime!!.time
                it.reservationDate = null
                it.reservationTime = null
                it.status = ReservationStatus.FINISHED
                repository.save(it)
            }
        }
    }
}
