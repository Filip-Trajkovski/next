package com.next.reservations.core.repository;

import com.next.reservations.core.domain.ReservationTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationTimeRepository extends JpaRepository<ReservationTime, Long> {
}
