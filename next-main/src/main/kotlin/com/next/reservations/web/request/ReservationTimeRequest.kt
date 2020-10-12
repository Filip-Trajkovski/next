package com.next.reservations.web.request

import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
class ReservationTimeRequest (
    val id: Long,
    val time: String,
    val reservationTimeConfigurationId: Long
)
