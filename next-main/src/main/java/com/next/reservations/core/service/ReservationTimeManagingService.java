/*
package com.next.reservations.core.service;

import com.next.reservations.core.domain.Reservation;
import com.next.reservations.core.domain.ReservationStatus;
import com.next.reservations.core.domain.ReservationTime;
import com.next.reservations.core.domain.ReservationTimeConfiguration;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationTimeManagingService {

    private ReservationTimeService reservationTimeService;
    private ReservationTimeConfigurationService reservationTimeConfigurationService;
    private ReservationService reservationService;

    public ReservationTimeManagingService(ReservationTimeService reservationTimeService, ReservationTimeConfigurationService reservationTimeConfigurationService, ReservationService reservationService) {
        this.reservationTimeService = reservationTimeService;
        this.reservationTimeConfigurationService = reservationTimeConfigurationService;
        this.reservationService = reservationService;
    }

    public List<ReservationTime> findUnreservedTimesForDate(LocalDate date, ReservationTimeConfiguration timeConfiguration) {
        final List<Long> reservedTimeIds = reservationService.findAllByStatusInAndReservationDate(List.of(ReservationStatus.ACCEPTED, ReservationStatus.PENDING), date)
                .stream().map(it -> it.getReservationTime().getId()).collect(Collectors.toList());

        final List<ReservationTime> availableTimes = reservationTimeService.findAllByReservationTimeConfigId(timeConfiguration.getId());

        return availableTimes.stream().filter(it -> !reservedTimeIds.contains(it.getId())).collect(Collectors.toList());
    }

    public ReservationTimeConfiguration findConfigurationForDate(LocalDate date) {
        final Optional<ReservationTimeConfiguration> futureDefault = reservationTimeConfigurationService.findFutureDefault();

        if (futureDefault.isPresent() && !date.isBefore(futureDefault.get().getDefaultStartDate())) {
            return futureDefault.get();
        } else {
            return reservationTimeConfigurationService.findByDefaultConfigTrue();
        }
    }
}
*/
