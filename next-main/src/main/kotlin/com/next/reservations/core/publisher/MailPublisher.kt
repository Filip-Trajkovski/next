package com.next.reservations.core.publisher

import com.next.reservations.core.domain.Reservation
import com.next.reservations.core.event.ReservationEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class MailPublisher(private val eventPublisher: ApplicationEventPublisher) {

    fun publishCustomEvent(reservation: Reservation) {
        val reservationEvent = ReservationEvent(reservation)
        eventPublisher.publishEvent(reservationEvent)
    }
}
