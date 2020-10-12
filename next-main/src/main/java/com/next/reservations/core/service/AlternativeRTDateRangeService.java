/*
package com.next.reservations.core.service;

import com.next.reservations.core.domain.AlternativeReservationTimeDateRange;
import com.next.reservations.core.domain.ReservationTimeConfiguration;
import com.next.reservations.core.repository.AlternativeRTDateRangeRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class AlternativeRTDateRangeService {

    private AlternativeRTDateRangeRepository repository;

    public AlternativeRTDateRangeService(AlternativeRTDateRangeRepository repository) {
        this.repository = repository;
    }

    public AlternativeReservationTimeDateRange createOrUpdate(Long id, LocalDate fromDate, LocalDate toDate,
                                                              ReservationTimeConfiguration reservationTimeConfiguration){
        if(id == null){
            return create(fromDate, toDate, reservationTimeConfiguration);
        } else {
            return update(id, fromDate, toDate, reservationTimeConfiguration);
        }
    }

    private AlternativeReservationTimeDateRange create(LocalDate fromDate, LocalDate toDate,
                                                       ReservationTimeConfiguration reservationTimeConfiguration){
        final AlternativeReservationTimeDateRange alternativeReservationTimeDateRange =
                new AlternativeReservationTimeDateRange(fromDate, toDate, reservationTimeConfiguration);

        return repository.save(alternativeReservationTimeDateRange);
    }

    private AlternativeReservationTimeDateRange update(Long id, LocalDate fromDate, LocalDate toDate,
                                                       ReservationTimeConfiguration reservationTimeConfiguration){
        final AlternativeReservationTimeDateRange alternativeReservationTimeDateRange = findById(id);

        alternativeReservationTimeDateRange.setFromDate(fromDate);
        alternativeReservationTimeDateRange.setToDate(toDate);
        alternativeReservationTimeDateRange.setReservationTimeConfiguration(reservationTimeConfiguration);

        return repository.save(alternativeReservationTimeDateRange);
    }

    public AlternativeReservationTimeDateRange findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
*/
