package com.next.reservations.web.response;

public class ReservationTimeResponse {

    public Long id;
    public String time;

    public ReservationTimeResponse(Long id, String time) {
        this.id = id;
        this.time = time;
    }
}
