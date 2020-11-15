package com.next.reservations.api

import com.next.reservations.web.mapper.ReservationMapper
import com.next.reservations.web.request.ReservationRequest
import com.next.reservations.web.request.TimeChangeRequest
import com.next.reservations.web.response.ReservationResponse
import com.next.shared.domain.Option
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/reservations")
class ReservationController(private val mapper: ReservationMapper) {

    @GetMapping("/by-time-config/{configId}")
    fun findAllActiveByConfigId(@PathVariable configId: Long): List<ReservationResponse> =
            mapper.findAllActiveReservationsByConfig(configId)

    @GetMapping("/by-reservation-time/{resTimeId}")
    fun findAllActiveByReservationTimeId(@PathVariable resTimeId: Long): List<ReservationResponse> =
            mapper.findAllActiveReservationsByReservationTime(resTimeId)

    @GetMapping("/default")
    fun findAllByDefaultConfig(): List<ReservationResponse> = mapper.findAllByDefaultConfig()

    @GetMapping("/by-new-future-default/{newFutureDefault}")
    fun findAllInvalidByNewFutureDefault(@PathVariable newFutureDefault: String): List<ReservationResponse> =
            mapper.findAllInvalidByFutureDefaultDate(newFutureDefault)

    @GetMapping("find-all-by-status")
    fun findByStatus(@RequestParam("status") status: String, @RequestParam("date") date: String): List<ReservationResponse> =
            mapper.findAllByStatusAndDateAfter(status, date)

    @GetMapping("statuses")
    fun findAllStatuses(): List<Option> = mapper.getAllStatuses()

    @PostMapping("accept/{id}")
    fun acceptReservation(@PathVariable id: Long) = mapper.acceptReservation(id)

    @PostMapping("reject/{id}")
    fun rejectReservation(@PathVariable id: Long) = mapper.rejectReservation(id)

    @PostMapping("change-time")
    fun changeTimeAndAccept(@RequestBody request: TimeChangeRequest) = mapper.changeTimeAndAccept(request)
}
