package com.next.reservations.web.mapper

import com.next.reservations.core.domain.ReservationTime
import com.next.reservations.core.domain.ReservationTimeConfiguration
import com.next.reservations.core.service.ReservationTimeConfigurationService
import com.next.reservations.core.service.ReservationTimeManagingService
import com.next.reservations.core.service.ReservationTimeService
import com.next.reservations.core.utils.Utils
import com.next.reservations.web.request.ReservationTimeRequest
import com.next.reservations.web.response.ReservationTimeResponse
import com.next.shared.domain.Option
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime

@Service
class ReservationTimeMapper(private val reservationTimeService: ReservationTimeService,
                            private val reservationTimeConfigurationService: ReservationTimeConfigurationService,
                            private val reservationTimeManagingService: ReservationTimeManagingService) {

    fun findAllReservationTimesByConfigId(configId: Long): List<ReservationTimeResponse> {
        return reservationTimeService.findAllByReservationTimeConfigId(configId)
                .map { reservationTime: ReservationTime -> convertReservationTimeToReservationTimeResponse(reservationTime) }
    }

    fun createOrUpdateReservationTime(reservationTimeRequest: ReservationTimeRequest): Long {

        val hoursAndMinutes = reservationTimeRequest.time.split(":").map { it.toInt() }

        val reservationTimeConfiguration = reservationTimeConfigurationService.findById(reservationTimeRequest.reservationTimeConfigurationId)

        return reservationTimeService
                .createOrUpdate(reservationTimeRequest.id, LocalTime.of(hoursAndMinutes[0], hoursAndMinutes[1]), reservationTimeConfiguration)
                .id
    }

    fun getTimesForDate(date: String): List<Option> {

        val dateParts = date.split("-").map { it.toInt() }

        val chosenDate = LocalDate.of(dateParts[0], dateParts[1], dateParts[2])

        val todaysDate = LocalDate.now()

        if(chosenDate.isBefore(todaysDate)){
            return listOf()
        }

        val configForDate = reservationTimeManagingService.findConfigurationForDate(chosenDate)

        return reservationTimeManagingService.findUnreservedTimesForDate(chosenDate, configForDate)
                .map { Option(it.id, it.time.toString()) }
    }

    fun deleteById(id: Long) {
        reservationTimeManagingService.deleteReservationTime(id)
    }

    private fun convertReservationTimeToReservationTimeResponse(reservationTime: ReservationTime): ReservationTimeResponse {
        return ReservationTimeResponse(reservationTime.id, reservationTime.time.toString())
    }

}
