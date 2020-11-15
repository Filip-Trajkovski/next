package com.next.reservations.api

import com.next.reservations.web.mapper.ReservationMapper
import com.next.reservations.web.mapper.ReservationTimeMapper
import com.next.reservations.web.request.ReservationRequest
import com.next.shared.domain.Option
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/public/reservations")
class ReservationPublicController(private val mapper: ReservationMapper,
                                  private val timeMapper: ReservationTimeMapper) {

    @PostMapping
    fun createReservation(@RequestBody request: ReservationRequest): Long {
        return mapper.createReservation(request)
    }


    @GetMapping("times-by-date/{date}")
    fun findAllByDate(@PathVariable date: String): List<Option> {
        return timeMapper.getTimesForDate(date)
    }
}
