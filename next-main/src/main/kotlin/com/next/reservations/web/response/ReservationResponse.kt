package com.next.reservations.web.response

class ReservationResponse (
        val id: Long,
        val fullName: String,
        val email: String,
        val phoneNumber: String,
        val numberOfPlayers: Int,
        val comment: String?,
        val previousReservationTimeDate: String?,
        val currentReservationTimeDate: String?,
        val status: Int
)
