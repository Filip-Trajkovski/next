package com.next.reservations.core.repository;

import com.next.reservations.core.domain.ReservationDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationDetailsRepository extends JpaRepository<ReservationDetails, Long> {
}
