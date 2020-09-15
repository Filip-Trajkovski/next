package com.next.reservations.core.service;

import com.next.reservations.core.domain.Reservation;
import com.next.reservations.core.domain.ReservationDetails;
import com.next.reservations.core.domain.ReservationStatus;
import com.next.reservations.core.domain.ReservationTime;
import com.next.reservations.web.request.ReservationRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationManagingService {

    private ReservationService reservationService;
    private ReservationDetailsService reservationDetailsService;
    private ReservationTimeService reservationTimeService;

    public ReservationManagingService(ReservationService reservationService,
                                      ReservationDetailsService reservationDetailsService,
                                      ReservationTimeService reservationTimeService) {
        this.reservationService = reservationService;
        this.reservationDetailsService = reservationDetailsService;
        this.reservationTimeService = reservationTimeService;
    }

    @Transactional
    public Reservation createReservation(ReservationRequest reservationRequest) {
        final ReservationDetails reservationDetails =
                reservationDetailsService.create(reservationRequest.fullName, reservationRequest.email,
                        reservationRequest.phoneNumber, reservationRequest.comment, reservationRequest.numberOfPlayers);

        final ReservationTime reservationTime = reservationTimeService.findById(reservationRequest.reservationTimeId);

        final List<Integer> dateParts = Arrays.stream(reservationRequest.date.split("/"))
                .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        final LocalDate localDate = LocalDate.of(dateParts.get(0), dateParts.get(1), dateParts.get(2));

        return reservationService.create(ReservationStatus.values()[reservationRequest.status],
                reservationDetails, reservationTime, localDate);
    }
}
