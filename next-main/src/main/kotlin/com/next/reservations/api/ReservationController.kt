package com.next.reservations.api

import com.next.reservations.web.mapper.ReservationMapper
import com.next.reservations.web.request.ReservationRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("api/reservations")
class ReservationController(private val mapper: ReservationMapper) {

    @PostMapping
    fun createReservation(@RequestBody request: ReservationRequest): Long {
        return mapper.createReservation(request)
    }

}
