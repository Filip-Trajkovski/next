package com.next.reservations.core.service

import com.next.reservations.core.domain.ReservationTimeConfiguration
import com.next.reservations.core.repository.ReservationTimeConfigurationRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import javax.transaction.Transactional

@Service
class ReservationTimeConfigurationService(private val repository: ReservationTimeConfigurationRepository) {

    fun createOrUpdate(id: Long?, name: String, defaultStartDate: LocalDate?): ReservationTimeConfiguration {
        return id?.let { update(it, name, defaultStartDate) } ?: create(name, defaultStartDate)
    }

    private fun create(name: String, defaultStartDate: LocalDate?): ReservationTimeConfiguration {
        val reservationTimeConfiguration = ReservationTimeConfiguration(name, false, defaultStartDate)
        return repository.save(reservationTimeConfiguration)
    }

    private fun update(id: Long, name: String, defaultStartDate: LocalDate?): ReservationTimeConfiguration {
        val reservationTimeConfiguration = findById(id)
        reservationTimeConfiguration.name = name
        reservationTimeConfiguration.defaultStartDate = defaultStartDate
        return repository.save(reservationTimeConfiguration)
    }

    @Transactional
    fun setAsDefault(id: Long) {
        removePreviousDefault()
        val newDefault = findById(id)
        newDefault.defaultConfig = true
        repository.save(newDefault)
    }

    private fun removePreviousDefault() {
        val currentDefault = findByDefaultConfigTrue()
        currentDefault.defaultConfig = false
        repository.save(currentDefault)
    }

    fun findByDefaultConfigTrue(): ReservationTimeConfiguration {
        return repository.findByDefaultConfigTrue()
    }

    fun findById(id: Long): ReservationTimeConfiguration {
        return repository.findById(id).orElseThrow()
    }

    fun findFutureDefault(): ReservationTimeConfiguration? {
        return repository.findByDefaultStartDateIsNotNull()
    }

}
