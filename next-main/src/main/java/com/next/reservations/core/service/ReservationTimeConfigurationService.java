/*
package com.next.reservations.core.service;

import com.next.reservations.core.domain.Reservation;
import com.next.reservations.core.domain.ReservationTimeConfiguration;
import com.next.reservations.core.repository.ReservationTimeConfigurationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ReservationTimeConfigurationService {

    private ReservationTimeConfigurationRepository repository;

    public ReservationTimeConfigurationService(ReservationTimeConfigurationRepository repository) {
        this.repository = repository;
    }

    public ReservationTimeConfiguration createOrUpdate(Long id, String name, LocalDate defaultStartDate) {

        if(id == null){
            return create(name, defaultStartDate);
        } else {
            return update(id, name, defaultStartDate);
        }
    }

    private ReservationTimeConfiguration create(String name, LocalDate defaultStartDate) {
        final ReservationTimeConfiguration reservationTimeConfiguration =
                new ReservationTimeConfiguration(name, false, defaultStartDate);

        return repository.save(reservationTimeConfiguration);
    }

    private ReservationTimeConfiguration update(Long id, String name, LocalDate defaultStartDate) {
        final ReservationTimeConfiguration reservationTimeConfiguration = findById(id);

        reservationTimeConfiguration.setName(name);
        reservationTimeConfiguration.setDefaultStartDate(defaultStartDate);

        return repository.save(reservationTimeConfiguration);
    }

    @Transactional
    public void setAsDefault(Long id) {
        removePreviousDefault();

        final ReservationTimeConfiguration newDefault = findById(id);
        newDefault.setDefaultConfig(true);
        repository.save(newDefault);
    }


    private void removePreviousDefault() {
        final ReservationTimeConfiguration currentDefault = findByDefaultConfigTrue();

        currentDefault.setDefaultConfig(false);
        repository.save(currentDefault);
    }

    public ReservationTimeConfiguration findByDefaultConfigTrue() {
        return repository.findByDefaultConfigTrue();
    }

    public ReservationTimeConfiguration findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Optional<ReservationTimeConfiguration> findFutureDefault(){
        return repository.findByDefaultStartDateIsNotNull();
    }

}
*/
