package com.next.reservations.core.service

import com.next.reservations.core.domain.Reservation
import com.next.reservations.core.domain.ReservationStatus
import com.next.reservations.web.request.ReservationRequest
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*
import java.util.stream.Collectors
import javax.transaction.Transactional

@Service
class ReservationManagingService(private val reservationService: ReservationService,
                                 private val reservationDetailsService: ReservationDetailsService,
                                 private val reservationTimeService: ReservationTimeService) {

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

}
