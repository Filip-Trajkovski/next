package com.next.reservations.web.mapper

import com.next.reservations.core.service.ReservationDetailsService
import com.next.reservations.core.service.ReservationManagingService
import com.next.reservations.core.service.ReservationService
import com.next.reservations.web.request.ReservationRequest
import org.springframework.stereotype.Service

@Service
class ReservationMapper(private val reservationService: ReservationService,
                        private val reservationDetailsService: ReservationDetailsService,
                        private val reservationManagingService: ReservationManagingService) {

    fun createReservation(reservationRequest: ReservationRequest): Long {
        return reservationManagingService.createReservation(reservationRequest).id
    }

}
