package com.next.reservations.web.request

import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
class ReservationRequest (
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val comment: String?,
    val numberOfPlayers: Int,
    val reservationTimeId: Long,
    val date: String,
    val status: Int
)
