package com.next.reservations.web.mapper

import com.next.reservations.core.domain.Reservation
import com.next.reservations.core.service.*
import com.next.reservations.web.request.ReservationRequest
import com.next.reservations.web.response.ReservationResponse
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ReservationMapper(private val reservationTimeService: ReservationTimeService,
                        private val reservationService: ReservationService,
                        private val reservationDetailsService: ReservationDetailsService,
                        private val reservationTimeConfigurationService: ReservationTimeConfigurationService,
                        private val reservationManagingService: ReservationManagingService) {

    fun createReservation(reservationRequest: ReservationRequest): Long {
        return reservationManagingService.createReservation(reservationRequest).id
    }

    fun findAllActiveReservationsByConfig(configId: Long): List<ReservationResponse> {
        return reservationManagingService.findAllActiveReservationsByConfig(configId)
                .map { convertReservationToResponse(it) }
    }

    fun findAllActiveReservationsByReservationTime(reservationTimeId: Long): List<ReservationResponse> {
        return reservationManagingService.findAllActiveReservationsByReservationTime(reservationTimeId)
                .map { convertReservationToResponse(it) }
    }

    fun findAllByDefaultConfig(): List<ReservationResponse> {
        val defaultConfigId = reservationTimeConfigurationService.findByDefaultConfigTrue().id
        return findAllActiveReservationsByConfig(defaultConfigId)
    }

    fun findAllInvalidByFutureDefaultDate(date: String): List<ReservationResponse> {
        reservationManagingService.validateDate(date)
        val parts = date.split("-").map { it.toInt() }

        val parsedDate = LocalDate.of(parts[2], parts[1], parts[0])

        return reservationManagingService.findAllInvalidByNewFutureDefaultDate(parsedDate).map { convertReservationToResponse(it) }
    }


    fun convertReservationToResponse(reservation: Reservation): ReservationResponse =
            ReservationResponse(
                    id = reservation.id,
                    fullName = reservation.reservationDetails.fullName,
                    email = reservation.reservationDetails.email,
                    phoneNumber = reservation.reservationDetails.phoneNumber,
                    comment = reservation.reservationDetails.comment,
                    numberOfPlayers = reservation.reservationDetails.numberOfPlayers,
                    currentReservationTimeDate = reservation.getCurrentReservationTimeDate(),
                    previousReservationTimeDate = reservation.getPreviousReservationTimeDate(),
                    status = reservation.status.ordinal
            )
}
