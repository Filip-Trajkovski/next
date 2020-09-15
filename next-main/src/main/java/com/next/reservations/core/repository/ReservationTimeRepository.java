package com.next.reservations.core.repository;

import com.next.reservations.core.domain.ReservationTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationTimeRepository extends JpaRepository<ReservationTime, Long> {
    List<ReservationTime> findAllByReservationTimeConfigurationId(Long reservationTimeConfigId);
}
