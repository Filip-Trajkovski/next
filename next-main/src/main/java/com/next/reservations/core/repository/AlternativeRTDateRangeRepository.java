package com.next.reservations.core.repository;

import com.next.reservations.core.domain.AlternativeReservationTimeDateRange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlternativeRTDateRangeRepository extends JpaRepository<AlternativeReservationTimeDateRange, Long> {
}
