package org.profit.avia.view;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import java.time.Instant;

public class FlightView {

    @Column(name = "flight_id")
    @Schema(description = "ИД полета")
    private Integer flightId;

    @Column(name = "flight_code")
    @Schema(description = "Код рейса")
    private String flightCode;

    @Column(name = "company_id")
    @Schema(description = "ИД авиакомпании")
    private Integer companyId;

    @Column(name = "company_name")
    @Schema(description = "Наименование авиакомпании")
    private String companyName;

    @Column(name = "airport_departure_id")
    @Schema(description = "ИД аэропорта вылета")
    private Integer airportDepartureId;

    @Column(name = "airport_departure_name")
    @Schema(description = "Наименование аэропорта вылета")
    private Integer airportDepartureName;

    @Column(name = "city_departure_id")
    @Schema(description = "ИД города вылета")
    private Integer cityDepartureId;

    @Column(name = "city_departure_name")
    @Schema(description = "Наименование города вылета")
    private Integer cityDepartureName;

    @Column(name = "airport_arrival_id")
    @Schema(description = "ИД аэропорта прилета")
    private Integer airportArrivalId;

    @Column(name = "airport_arrival_name")
    @Schema(description = "Наименование аэропорта прилета")
    private Integer airportArrivalName;

    @Column(name = "city_arrival_id")
    @Schema(description = "ИД города прилета")
    private Integer cityArrivalId;

    @Column(name = "city_arrival_name")
    @Schema(description = "Наименование города прилета")
    private Integer cityArrivalName;

    @Column(name = "flight_plandeparture")
    @Schema(description = "Плановая дата вылета")
    private Instant flightPlanDeparture;

    @Column(name = "flight_planarrival")
    @Schema(description = "Плановая дата прилета")
    private Instant flightPlanArrival;

    @Column(name = "flight_factdeparture")
    @Schema(description = "Фактическая дата вылета")
    private Instant flightFactDeparture;

    @Column(name = "flight_factarrival")
    @Schema(description = "Фактическая дата прилета")
    private Instant flightFactArrival;

    public FlightView() {
    }

    public FlightView(Integer flightId,
                  Integer companyId,
                  String flightCode,
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getAirportDepartureName() {
        return airportDepartureName;
    }

    public void setAirportDepartureName(Integer airportDepartureName) {
        this.airportDepartureName = airportDepartureName;
    }

    public Integer getCityDepartureId() {
        return cityDepartureId;
    }

    public void setCityDepartureId(Integer cityDepartureId) {
        this.cityDepartureId = cityDepartureId;
    }

    public Integer getCityDepartureName() {
        return cityDepartureName;
    }

    public void setCityDepartureName(Integer cityDepartureName) {
        this.cityDepartureName = cityDepartureName;
    }

    public Integer getAirportArrivalName() {
        return airportArrivalName;
    }

    public void setAirportArrivalName(Integer airportArrivalName) {
        this.airportArrivalName = airportArrivalName;
    }

    public Integer getCityArrivalId() {
        return cityArrivalId;
    }

    public void setCityArrivalId(Integer cityArrivalId) {
        this.cityArrivalId = cityArrivalId;
    }

    public Integer getCityArrivalName() {
        return cityArrivalName;
    }

    public void setCityArrivalName(Integer cityArrivalName) {
        this.cityArrivalName = cityArrivalName;
    }
}
