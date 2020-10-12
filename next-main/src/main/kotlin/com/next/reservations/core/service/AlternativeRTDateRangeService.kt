package com.next.reservations.core.service

import com.next.reservations.core.domain.AlternativeReservationTimeDateRange
import com.next.reservations.core.domain.ReservationTimeConfiguration
import com.next.reservations.core.repository.AlternativeRTDateRangeRepository
import org.springframework.stereotype.Service
import java.time.LocalDate


@Service
class AlternativeRTDateRangeService(private val repository: AlternativeRTDateRangeRepository) {
    fun createOrUpdate(id: Long?, fromDate: LocalDate, toDate: LocalDate,
                       reservationTimeConfiguration: ReservationTimeConfiguration): AlternativeReservationTimeDateRange {
        return id?.let { update(it, fromDate, toDate, reservationTimeConfiguration) }
                ?: create(fromDate, toDate, reservationTimeConfiguration)
    }

    private fun create(fromDate: LocalDate, toDate: LocalDate,
                       reservationTimeConfiguration: ReservationTimeConfiguration): AlternativeReservationTimeDateRange {
        val alternativeReservationTimeDateRange = AlternativeReservationTimeDateRange(fromDate, toDate, reservationTimeConfiguration)
        return repository.save(alternativeReservationTimeDateRange)
    }

    private fun update(id: Long, fromDate: LocalDate, toDate: LocalDate,
                       reservationTimeConfiguration: ReservationTimeConfiguration): AlternativeReservationTimeDateRange {
        val alternativeReservationTimeDateRange = findById(id)
        alternativeReservationTimeDateRange.fromDate = fromDate
        alternativeReservationTimeDateRange.toDate = toDate
        alternativeReservationTimeDateRange.reservationTimeConfiguration = reservationTimeConfiguration
        return repository.save(alternativeReservationTimeDateRange)
    }

    fun findById(id: Long): AlternativeReservationTimeDateRange {
        return repository.findById(id).orElseThrow()
    }

}
