package com.next.reservations.web.mapper;

import com.next.reservations.core.service.ReservationDetailsService;
import com.next.reservations.core.service.ReservationManagingService;
import com.next.reservations.core.service.ReservationService;
import com.next.reservations.web.request.ReservationRequest;
import org.springframework.stereotype.Service;

@Service
public class ReservationMapper {

    private ReservationService reservationService;
    private ReservationDetailsService reservationDetailsService;
    private ReservationManagingService reservationManagingService;

    public ReservationMapper(ReservationService reservationService,
                             ReservationDetailsService reservationDetailsService,
                             ReservationManagingService reservationManagingService) {
        this.reservationService = reservationService;
        this.reservationDetailsService = reservationDetailsService;
        this.reservationManagingService = reservationManagingService;
    }


    public Long createReservation(ReservationRequest reservationRequest){
        return reservationManagingService.createReservation(reservationRequest).getId();
    }
}
