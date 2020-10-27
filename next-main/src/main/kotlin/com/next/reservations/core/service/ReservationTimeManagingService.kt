package com.next.reservations.core.service

import com.next.reservations.core.domain.ReservationStatus
import com.next.reservations.core.domain.ReservationTime
import com.next.reservations.core.domain.ReservationTimeConfiguration
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.stream.Collectors
import javax.transaction.Transactional


@Service
class ReservationTimeManagingService(private val reservationTimeService: ReservationTimeService,
                                     private val reservationTimeConfigurationService: ReservationTimeConfigurationService,
                                     private val reservationService: ReservationService,
                                     private val reservationManagingService: ReservationManagingService) {

    fun findUnreservedTimesForDate(date: LocalDate, timeConfiguration: ReservationTimeConfiguration): List<ReservationTime> {

        val reservedTimeIds = reservationService.findAllByStatusInAndReservationDate(listOf(ReservationStatus.ACCEPTED, ReservationStatus.PENDING), date)
                .stream().map { it.reservationTime!!.id }.collect(Collectors.toList())

        val availableTimes = reservationTimeService.findAllByReservationTimeConfigId(timeConfiguration.id)

        return availableTimes.stream().filter { !reservedTimeIds.contains(it.id) }.collect(Collectors.toList())
    }

    fun findConfigurationForDate(date: LocalDate): ReservationTimeConfiguration {

        val futureDefault: ReservationTimeConfiguration? = reservationTimeConfigurationService.findFutureDefault()

        return if (futureDefault != null && !date.isBefore(futureDefault.defaultStartDate))
            futureDefault
        else
            reservationTimeConfigurationService.findByDefaultConfigTrue()

    }

    @Transactional
    fun deleteReservationTimeConfig(id: Long) {
        reservationTimeConfigurationService.validateCanDelete(id)

        val reservationTimes = reservationTimeService.findAllByReservationTimeConfigId(id)

        reservationTimes.forEach { deleteReservationTime(it.id) }

        reservationTimeConfigurationService.deleteById(id)
    }

    @Transactional
    fun deleteReservationTime(reservationTimeId: Long) {
        val reservationsForRemoval = reservationManagingService.findAllActiveReservationsByReservationTime(reservationTimeId)
                .map { it.id }

        reservationService.removeReservationsByIds(reservationsForRemoval)

        reservationTimeService.delete(reservationTimeId)
    }

}
