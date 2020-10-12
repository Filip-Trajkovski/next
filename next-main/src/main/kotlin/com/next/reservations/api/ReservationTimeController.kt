package com.next.reservations.api

import com.next.reservations.web.mapper.ReservationTimeMapper
import com.next.reservations.web.request.ReservationTimeConfigurationRequest
import com.next.reservations.web.request.ReservationTimeRequest
import com.next.reservations.web.response.ReservationTimeResponse
import com.next.shared.domain.Option
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/reservation-times")
class ReservationTimeController(private val mapper: ReservationTimeMapper) {
    @PostMapping
    fun createOrUpdateTime(@RequestBody reservationTimeRequest: ReservationTimeRequest): Long {
        return mapper.createOrUpdateReservationTime(reservationTimeRequest)
    }

    @GetMapping("{configId}/by-config")
    fun findAllReservationTimes(@PathVariable configId: Long): List<ReservationTimeResponse> {
        return mapper.findAllReservationTimesByConfigId(configId)
    }

    @PostMapping("/config")
    fun createOrUpdateConfig(@RequestBody reservationTimeConfigurationRequest: ReservationTimeConfigurationRequest): Long {
        return mapper.createOrUpdateConfiguration(reservationTimeConfigurationRequest)
    }

    @GetMapping("by-date/{date}")
    fun findAllByDate(@PathVariable date: String): List<Option> {
        return mapper.getTimesForDate(date)
    }

}
