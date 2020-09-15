package com.next.reservations.web.request;

import java.util.Optional;

public class ReservationTimeConfigurationRequest {

    public Long id;
    public String name;
    public String defaultStartDate;

    public ReservationTimeConfigurationRequest() {
    }

    public ReservationTimeConfigurationRequest(Long id, String name, boolean defaultConfig, String defaultStartDate) {
        this.id = id;
        this.name = name;
        this.defaultStartDate = defaultStartDate;
    }
}
