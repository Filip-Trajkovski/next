package com.next.reservations.core.service

import com.next.reservations.core.domain.ReservationTime
import com.next.reservations.core.domain.ReservationTimeConfiguration
import com.next.reservations.core.repository.ReservationTimeRepository
import org.springframework.stereotype.Service
import java.time.LocalTime


@Service
class ReservationTimeService(private val repository: ReservationTimeRepository) {
    fun createOrUpdate(id: Long?, time: LocalTime, reservationTimeConfiguration: ReservationTimeConfiguration): ReservationTime {
        return id?.let { updateTime(it, time) } ?: create(time, reservationTimeConfiguration)
    }

    private fun create(time: LocalTime, reservationTimeConfiguration: ReservationTimeConfiguration): ReservationTime {
        val reservationTime = ReservationTime(time = time,
                reservationTimeConfiguration = reservationTimeConfiguration)

        return repository.save(reservationTime)
    }

    private fun updateTime(id: Long, time: LocalTime): ReservationTime {
        val reservationTime = findById(id)
        reservationTime.time = time
        return repository.save(reservationTime)
    }

    fun findAllByReservationTimeConfigId(reservationTimeConfigId: Long): List<ReservationTime> {
        return repository.findAllByReservationTimeConfigurationId(reservationTimeConfigId).sortedBy { it.time }
    }

    fun findById(id: Long): ReservationTime {
        return repository.findById(id).orElseThrow()
    }

    fun delete(id: Long) {
        repository.delete(findById(id))
    }
}
