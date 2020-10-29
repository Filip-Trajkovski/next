package com.next.reservations.core.service

import com.next.reservations.core.domain.Reservation
import com.next.reservations.core.domain.ReservationStatus
import com.next.reservations.core.utils.Utils
import com.next.reservations.web.request.ReservationRequest
import org.springframework.stereotype.Service
import java.time.LocalDate
import javax.transaction.Transactional

@Service
class ReservationManagingService(private val reservationService: ReservationService,
                                 private val reservationDetailsService: ReservationDetailsService,
                                 private val reservationTimeService: ReservationTimeService,
                                 private val reservationTimeConfigurationService: ReservationTimeConfigurationService) {

    @Transactional
    fun createReservation(reservationRequest: ReservationRequest): Reservation {

        val reservationDetails = reservationDetailsService.create(reservationRequest.fullName, reservationRequest.email,
                reservationRequest.phoneNumber, reservationRequest.comment, reservationRequest.numberOfPlayers)

        val reservationTime = reservationTimeService.findById(reservationRequest.reservationTimeId)

        val dateParts = reservationRequest.date.split("-").map { it.toInt() }

        val localDate = LocalDate.of(dateParts[0], dateParts[1], dateParts[2])

        return reservationService.create(ReservationStatus.values()[reservationRequest.status],
                reservationDetails, reservationTime, localDate)
    }


    fun findAllActiveReservationsByConfig(configId: Long): List<Reservation> {
        val reservationTimeIds = reservationTimeService.findAllByReservationTimeConfigId(configId).map { it.id }

        val reservations =
                reservationService.findAllByStatusInAndReservationTimeIdInAndReservationDateAfter(listOf(ReservationStatus.ACCEPTED, ReservationStatus.PENDING),
                        reservationTimeIds, LocalDate.now().minusDays(1))

        return reservations
    }

    fun findAllActiveReservationsByReservationTime(reservationTimeId: Long): List<Reservation> {
        val reservations =
                reservationService.findAllByStatusInAndReservationTimeIdInAndReservationDateAfter(listOf(ReservationStatus.ACCEPTED, ReservationStatus.PENDING),
                        listOf(reservationTimeId), LocalDate.now().minusDays(1))

        return reservations
    }

    fun findAllInvalidByNewFutureDefaultDate(date: LocalDate): List<Reservation> {

        val currentFutureDateConfig = reservationTimeConfigurationService.findFutureDefault()
        val defaultConfig = reservationTimeConfigurationService.findByDefaultConfigTrue()

        val futureDateReservations = if (currentFutureDateConfig != null) {
            val currentFutureDateReservationTimes = reservationTimeService.findAllByReservationTimeConfigId(currentFutureDateConfig.id).map { it.id }
            val futureDateReservations = reservationService.findAllByStatusInAndReservationTimeIdInAndReservationDateAfter(
                    listOf(ReservationStatus.ACCEPTED, ReservationStatus.PENDING), currentFutureDateReservationTimes, LocalDate.now())

            futureDateReservations
        } else listOf()

        val defaultReservationTimes = reservationTimeService.findAllByReservationTimeConfigId(defaultConfig.id).map{it.id}
        val defaultInvalidReservations = reservationService.findAllByStatusInAndReservationTimeIdInAndReservationDateAfter(
                listOf(ReservationStatus.ACCEPTED, ReservationStatus.PENDING), defaultReservationTimes, date.minusDays(1))

        return futureDateReservations.union(defaultInvalidReservations).toList()
    }

    @Transactional
    fun changeDefaultConfig(newDefaultConfigId: Long) {
        val currentDefault = reservationTimeConfigurationService.findByDefaultConfigTrue()
        val reservationsForRemoval = findAllActiveReservationsByConfig(currentDefault.id).map { it.id }
        reservationService.removeReservationsByIds(reservationsForRemoval)
        reservationTimeConfigurationService.setAsDefault(newDefaultConfigId)
    }

    @Transactional
    fun setNewFutureDefault(id: Long, newFutureDefaultDate: LocalDate) {
        val reservationsForRemoval = findAllInvalidByNewFutureDefaultDate(newFutureDefaultDate)
        reservationService.removeReservationsByIds(reservationsForRemoval.map { it.id })
        reservationTimeConfigurationService.removeFutureDefaultStartDate()
        reservationTimeConfigurationService.setNewFutureDefaultStartDate(id, newFutureDefaultDate)
    }

}
