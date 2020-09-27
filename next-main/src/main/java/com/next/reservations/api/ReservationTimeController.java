package com.next.reservations.api;

import com.next.reservations.web.mapper.ReservationTimeMapper;
import com.next.reservations.web.request.ReservationTimeConfigurationRequest;
import com.next.reservations.web.request.ReservationTimeRequest;
import com.next.reservations.web.response.ReservationTimeResponse;
import com.next.shared.domain.Option;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservation-times")
public class ReservationTimeController {

    private ReservationTimeMapper mapper;

    public ReservationTimeController(ReservationTimeMapper mapper) {
        this.mapper = mapper;
    }

    @PostMapping
    public Long createOrUpdateTime(@RequestBody ReservationTimeRequest reservationTimeRequest) {
        return mapper.createOrUpdateReservationTime(reservationTimeRequest);
    }

    @GetMapping("{configId}/by-config")
    public List<ReservationTimeResponse> findAllReservationTimes(@PathVariable Long configId){
        return mapper.findAllReservationTimesByConfigId(configId);
    }

    @PostMapping("/config")
    public long createOrUpdateConfig(@RequestBody ReservationTimeConfigurationRequest reservationTimeConfigurationRequest){
        return mapper.createOrUpdateConfiguration(reservationTimeConfigurationRequest);
    }

    @GetMapping("by-date/{date}")
    public List<Option> findAllByDate(@PathVariable String date){
        return mapper.getTimesForDate(date);
    }
}
