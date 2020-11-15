package com.next.reservations.core.event

import com.next.reservations.core.domain.Reservation
import org.springframework.context.ApplicationEvent

class ReservationEvent(
        val reservation: Reservation
) : ApplicationEvent(reservation)
