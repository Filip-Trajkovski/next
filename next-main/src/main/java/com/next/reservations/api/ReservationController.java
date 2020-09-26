package com.next.reservations.api;

import com.next.reservations.web.mapper.ReservationMapper;
import com.next.reservations.web.request.ReservationRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    private ReservationMapper mapper;

    public ReservationController(ReservationMapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping
    public Long createReservation(@RequestBody ReservationRequest request) {
        return mapper.createReservation(request);
    }
}
