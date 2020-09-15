package com.next.reservations.web.mapper;

import com.next.reservations.core.domain.ReservationTime;
import com.next.reservations.core.domain.ReservationTimeConfiguration;
import com.next.reservations.core.service.ReservationTimeConfigurationService;
import com.next.reservations.core.service.ReservationTimeService;
import com.next.reservations.web.request.ReservationTimeConfigurationRequest;
import com.next.reservations.web.request.ReservationTimeRequest;
import com.next.reservations.web.response.ReservationTimeResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationTimeMapper {

    private ReservationTimeService reservationTimeService;
    private ReservationTimeConfigurationService reservationTimeConfigurationService;

    public ReservationTimeMapper(ReservationTimeService reservationTimeService, ReservationTimeConfigurationService reservationTimeConfigurationService) {
        this.reservationTimeService = reservationTimeService;
        this.reservationTimeConfigurationService = reservationTimeConfigurationService;
    }

    public List<ReservationTimeResponse> findAllReservationTimesByConfigId(Long configId){
        return reservationTimeService.findAllByReservationTimeConfigId(configId).stream()
                .map(this::convertReservationTimeToReservationTimeResponse).collect(Collectors.toList());
    }

    public Long createOrUpdateReservationTime(ReservationTimeRequest reservationTimeRequest) {
        final List<Integer> hoursAndMinutes = Arrays.stream(reservationTimeRequest.time.split(":")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        final ReservationTimeConfiguration reservationTimeConfiguration = reservationTimeConfigurationService.findById(reservationTimeRequest.reservationTimeConfigurationId);

        return reservationTimeService
                .createOrUpdate(reservationTimeRequest.id, LocalTime.of(hoursAndMinutes.get(0), hoursAndMinutes.get(1)), reservationTimeConfiguration)
                .getId();
    }

    public Long createOrUpdateConfiguration(ReservationTimeConfigurationRequest reservationTimeConfigurationRequest) {
        final LocalDate date;

        if(reservationTimeConfigurationRequest.defaultStartDate == null){
            date = null;
        } else {
            final List<Integer> dateParts = Arrays.stream(reservationTimeConfigurationRequest.defaultStartDate.split("/"))
                    .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
            date = LocalDate.of(dateParts.get(0), dateParts.get(1), dateParts.get(2));
        }

        return reservationTimeConfigurationService.createOrUpdate(reservationTimeConfigurationRequest.id,
                reservationTimeConfigurationRequest.name, date).getId();
    }


    private ReservationTimeResponse convertReservationTimeToReservationTimeResponse(ReservationTime reservationTime){
        return new ReservationTimeResponse(reservationTime.getId(), reservationTime.getTime().toString());
    }
}
