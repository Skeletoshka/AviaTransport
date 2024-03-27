package org.profit.avia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class UploadFlightDTO {

    @JsonProperty("airline_iata_code")
    private String airlineIAtaCode;

    @JsonProperty("flight")
    private String flight;

    @JsonProperty("departure_airport")
    private String departureAirport;

    @JsonProperty("arrival_airport")
    private String arrivalAirport;

    @JsonProperty("plan_departure")
    private Instant planDeparture;

    @JsonProperty("plan_arrival")
    private Instant planArrival;

    @JsonProperty("fact_departure")
    private Instant factDeparture;

    @JsonProperty("fact_arrival")
    private Instant factArrival;

    public UploadFlightDTO() {
    }

    public UploadFlightDTO(String airlineIAtaCode,
                           String flight,
                           String departureAirport,
                           String arrivalAirport,
                           Instant planDeparture,
                           Instant planArrival,
                           Instant factDeparture,
                           Instant factArrival) {
        this.airlineIAtaCode = airlineIAtaCode;
        this.flight = flight;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.planDeparture = planDeparture;
        this.planArrival = planArrival;
        this.factDeparture = factDeparture;
        this.factArrival = factArrival;
    }

    public String getAirlineIAtaCode() {
        return airlineIAtaCode;
    }

    public void setAirlineIAtaCode(String airlineIAtaCode) {
        this.airlineIAtaCode = airlineIAtaCode;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Instant getPlanDeparture() {
        return planDeparture;
    }

    public void setPlanDeparture(Instant planDeparture) {
        this.planDeparture = planDeparture;
    }

    public Instant getPlanArrival() {
        return planArrival;
    }

    public void setPlanArrival(Instant planArrival) {
        this.planArrival = planArrival;
    }

    public Instant getFactDeparture() {
        return factDeparture;
    }

    public void setFactDeparture(Instant factDeparture) {
        this.factDeparture = factDeparture;
    }

    public Instant getFactArrival() {
        return factArrival;
    }

    public void setFactArrival(Instant factArrival) {
        this.factArrival = factArrival;
    }
}
