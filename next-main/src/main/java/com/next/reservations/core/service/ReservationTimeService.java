package com.next.reservations.core.service;

import com.next.reservations.core.domain.ReservationTime;
import com.next.reservations.core.domain.ReservationTimeConfiguration;
import com.next.reservations.core.repository.ReservationTimeRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;

@Service
public class ReservationTimeService {

    private ReservationTimeRepository repository;

    public ReservationTimeService(ReservationTimeRepository repository) {
        this.repository = repository;
    }

    public ReservationTime create(Time time, ReservationTimeConfiguration reservationTimeConfiguration){
        final ReservationTime reservationTime = new ReservationTime(time, reservationTimeConfiguration);

        return repository.save(reservationTime);
    }

    public ReservationTime updateTime(Long id, Time time){
        final ReservationTime reservationTime = findById(id);

        reservationTime.setTime(time);

        return repository.save(reservationTime);
    }

    public ReservationTime findById(Long id){
        return repository.findById(id).orElseThrow();
    }

    public void delete(Long id){
        repository.delete(findById(id));
    }
}
