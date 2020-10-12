package com.next.reservations.core.service

import com.next.reservations.core.domain.ReservationDetails
import com.next.reservations.core.repository.ReservationDetailsRepository
import org.springframework.stereotype.Service

@Service
class ReservationDetailsService(private val repository: ReservationDetailsRepository) {

    fun create(fullName: String, email: String, phoneNumber: String,
               comment: String?, numberOfPlayers: Int): ReservationDetails {
        val reservationDetails = ReservationDetails(fullName, email, phoneNumber, comment, numberOfPlayers)
        return repository.save(reservationDetails)
    }

    fun findById(id: Long): ReservationDetails {
        return repository.findById(id).orElseThrow()
    }

}
