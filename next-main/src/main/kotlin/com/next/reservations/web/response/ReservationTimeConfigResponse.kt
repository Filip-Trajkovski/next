package com.next.reservations.web.response

class ReservationTimeConfigResponse(
        val id: Long,
        val name: String,
        val default: Boolean,
        val defaultStartDate: String?
)
