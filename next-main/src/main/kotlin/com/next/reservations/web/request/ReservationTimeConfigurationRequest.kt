package com.next.reservations.web.request

import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
class ReservationTimeConfigurationRequest(
        val id: Long?,
        val name: String,
        val defaultStartDate: String?
)
