/*
package com.next.reservations.core.service;

import com.next.reservations.core.domain.ReservationDetails;
import com.next.reservations.core.repository.ReservationDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationDetailsService {

    private ReservationDetailsRepository repository;

    private ReservationDetailsService(ReservationDetailsRepository repository) {
        this.repository = repository;
    }

    public ReservationDetails create(String fullName, String email, String phoneNumber,
                                     String comment, int numberOfPlayers) {
        final ReservationDetails reservationDetails = new ReservationDetails(fullName, email, phoneNumber, comment, numberOfPlayers);

        return repository.save(reservationDetails);
    }

    public ReservationDetails findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

}
*/
