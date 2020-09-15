package com.next.reservations.core.service;

import com.next.reservations.core.domain.ReservationTimeConfiguration;
import com.next.reservations.core.repository.ReservationTimeConfigurationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.Optional;

@Service
public class ReservationTimeConfigurationService {

    private ReservationTimeConfigurationRepository repository;

    public ReservationTimeConfigurationService(ReservationTimeConfigurationRepository repository) {
        this.repository = repository;
    }

    public ReservationTimeConfiguration createOrUpdate(Long id, String name, Boolean defaultConfig, Date defaultStartDate) {
        if(defaultConfig && checkIfDefaultExists()){
            throw new RuntimeException("Vekje postoi default torko");
        }

        if(id == null){
            return create(name, defaultConfig, defaultStartDate);
        } else {
            return update(id, name, defaultConfig, defaultStartDate);
        }
    }

    private ReservationTimeConfiguration create(String name, Boolean defaultConfig, Date defaultStartDate) {
        final ReservationTimeConfiguration reservationTimeConfiguration =
                new ReservationTimeConfiguration(name, defaultConfig, defaultStartDate);

        return repository.save(reservationTimeConfiguration);
    }

    private ReservationTimeConfiguration update(Long id, String name, Boolean defaultConfig, Date defaultStartDate) {
        final ReservationTimeConfiguration reservationTimeConfiguration = findById(id);

        reservationTimeConfiguration.setName(name);
        reservationTimeConfiguration.setDefaultConfig(defaultConfig);
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

    private boolean checkIfDefaultExists() {
        return findByDefaultConfigTrue().isPresent();
    }

    private void removePreviousDefault() {
        final Optional<ReservationTimeConfiguration> currentDefault = findByDefaultConfigTrue();

        currentDefault.ifPresent(it -> {
            it.setDefaultConfig(false);
            repository.save(it);
        });
    }

    public Optional<ReservationTimeConfiguration> findByDefaultConfigTrue() {
        return repository.findByDefaultConfigTrue();
    }

    public ReservationTimeConfiguration findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
