package com.next.reservations.api

import com.next.reservations.web.mapper.ReservationTimeConfigMapper
import com.next.reservations.web.request.ReservationTimeConfigurationRequest
import com.next.reservations.web.response.ReservationTimeConfigResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/reservation-time-configs")
class ReservationTimeConfigController(private val mapper: ReservationTimeConfigMapper) {


    @PostMapping
    fun createOrUpdate(@RequestBody reservationTimeConfigurationRequest: ReservationTimeConfigurationRequest): Long {
        return mapper.createOrUpdateConfiguration(reservationTimeConfigurationRequest)
    }

    @GetMapping
    fun getAll(): List<ReservationTimeConfigResponse> =
            mapper.getAllReservationTimeConfigs()

    @PostMapping("new-default/{id}")
    fun changeDefaultConfig(@PathVariable id: Long) =
            mapper.changeDefaultConfig(id)

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) {
        return mapper.delete(id)
    }

    @PostMapping("/{id}/new-name/{name}")
    fun changeName(@PathVariable id: Long, @PathVariable name: String) = mapper.changeName(id, name)

    @PostMapping("/add-new/{name}")
    fun addNew(@PathVariable name: String): Long = mapper.addNew(name)

    @PostMapping("/new-future-default/{id}/{futureDate}")
    fun changeFutureDefault(@PathVariable id: Long, @PathVariable futureDate: String) =
            mapper.changeFutureDefault(id, futureDate)
}
