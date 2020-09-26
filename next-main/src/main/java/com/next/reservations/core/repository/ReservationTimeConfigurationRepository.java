package com.next.reservations.core.repository;

import com.next.reservations.core.domain.ReservationTimeConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationTimeConfigurationRepository extends JpaRepository<ReservationTimeConfiguration, Long> {
    ReservationTimeConfiguration findByDefaultConfigTrue();
    Optional<ReservationTimeConfiguration> findByDefaultStartDateIsNotNull();
}
