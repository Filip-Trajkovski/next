package com.next.reservations.core.service;

import com.next.reservations.core.domain.ReservationTime;
import com.next.reservations.core.domain.ReservationTimeConfiguration;
import com.next.reservations.core.repository.ReservationTimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class ReservationTimeService {

    private ReservationTimeRepository repository;

    public ReservationTimeService(ReservationTimeRepository repository) {
        this.repository = repository;
    }

    public ReservationTime createOrUpdate(Long id, LocalTime time, ReservationTimeConfiguration reservationTimeConfiguration) {
        if(id == null){
            return create(time, reservationTimeConfiguration);
        } else {
            return updateTime(id, time);
        }
    }

    private ReservationTime create(LocalTime time, ReservationTimeConfiguration reservationTimeConfiguration) {
        final ReservationTime reservationTime = new ReservationTime(time, reservationTimeConfiguration);

        return repository.save(reservationTime);
    }

    private ReservationTime updateTime(Long id, LocalTime time) {
        final ReservationTime reservationTime = findById(id);

        reservationTime.setTime(time);

        return repository.save(reservationTime);
    }

    public List<ReservationTime> findAllByReservationTimeConfigId(Long reservationTimeConfigId) {
        return repository.findAllByReservationTimeConfigurationId(reservationTimeConfigId);
    }

    public ReservationTime findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
