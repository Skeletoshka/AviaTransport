package org.profit.avia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @Column(name = "flight_id", nullable = false)
    private Integer flightId;

    @Column(name = "flight_code", nullable = false)
    @Size(max = 15, message = "Поле \"Код рейса\" не может иметь более {max} символов")
    @NotNull(message = "Поле \"Код рейса\" не может быть пустым")
    private String flightCode;

    @Column(name = "company_id", nullable = false)
    @NotNull(message = "Поле \"ИД компании\" не может быть пустым")
    private Integer companyId;

    @Column(name = "airport_departure_id", nullable = false)
    @NotNull(message = "Поле \"ИД аэропорта вылета\" не может быть пустым")
    private Integer airportDepartureId;

    @Column(name = "airport_arrival_id", nullable = false)
    @NotNull(message = "Поле \"ИД аэропорта вылета\" не может быть пустым")
    private Integer airportArrivalId;

    @Column(name = "flight_plandeparture", nullable = false)
    @NotNull(message = "Поле \"Плановая дата вылета\" не может быть пустым")
    private Instant flightPlanDeparture;

    @Column(name = "flight_planarrival", nullable = false)
    @NotNull(message = "Поле \"Плановая дата прилета\" не может быть пустым")
    private Instant flightPlanArrival;

    @Column(name = "flight_factdeparture", nullable = true)
    private Instant flightFactDeparture;

    @Column(name = "flight_factarrival", nullable = true)
    private Instant flightFactArrival;

    public Flight() {
    }

    public Flight(Integer flightId,
                  String flightCode,
                  Integer companyId,
                  Integer airportDepartureId,
                  Integer airportArrivalId,
                  Instant flightPlanDeparture,
                  Instant flightPlanArrival,
                  Instant flightFactDeparture,
                  Instant flightFactArrival) {
        this.flightId = flightId;
        this.companyId = companyId;
        this.flightCode = flightCode;
        this.airportDepartureId = airportDepartureId;
        this.airportArrivalId = airportArrivalId;
        this.flightPlanDeparture = flightPlanDeparture;
        this.flightPlanArrival = flightPlanArrival;
        this.flightFactDeparture = flightFactDeparture;
        this.flightFactArrival = flightFactArrival;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getAirportDepartureId() {
        return airportDepartureId;
    }

    public void setAirportDepartureId(Integer airportDepartureId) {
        this.airportDepartureId = airportDepartureId;
    }

    public Integer getAirportArrivalId() {
        return airportArrivalId;
    }

    public void setAirportArrivalId(Integer airportArrivalId) {
        this.airportArrivalId = airportArrivalId;
    }

    public Instant getFlightPlanDeparture() {
        return flightPlanDeparture;
    }

    public void setFlightPlanDeparture(Instant flightPlanDeparture) {
        this.flightPlanDeparture = flightPlanDeparture;
    }

    public Instant getFlightPlanArrival() {
        return flightPlanArrival;
    }

    public void setFlightPlanArrival(Instant flightPlanArrival) {
        this.flightPlanArrival = flightPlanArrival;
    }

    public Instant getFlightFactDeparture() {
        return flightFactDeparture;
    }

    public void setFlightFactDeparture(Instant flightFactDeparture) {
        this.flightFactDeparture = flightFactDeparture;
    }

    public Instant getFlightFactArrival() {
        return flightFactArrival;
    }

    public void setFlightFactArrival(Instant flightFactArrival) {
        this.flightFactArrival = flightFactArrival;
    }
}
