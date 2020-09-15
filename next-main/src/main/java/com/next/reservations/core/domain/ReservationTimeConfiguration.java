package com.next.reservations.core.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(schema = "reservations", name = "reservation_time_configurations")
public class ReservationTimeConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "default_config", nullable = false)
    private boolean defaultConfig;

    @Column(name = "default_start_date")
    private LocalDate defaultStartDate;

    public ReservationTimeConfiguration(){}

    public ReservationTimeConfiguration(String name, boolean defaultConfig, LocalDate defaultStartDate) {
        this.name = name;
        this.defaultConfig = defaultConfig;
        this.defaultStartDate = defaultStartDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefaultConfig() {
        return defaultConfig;
    }

    public void setDefaultConfig(boolean defaultConfig) {
        this.defaultConfig = defaultConfig;
    }

    public LocalDate getDefaultStartDate() {
        return defaultStartDate;
    }

    public void setDefaultStartDate(LocalDate defaultStartDate) {
        this.defaultStartDate = defaultStartDate;
    }
}
