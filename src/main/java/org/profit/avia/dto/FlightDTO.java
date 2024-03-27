package org.profit.avia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.profit.avia.model.Flight;

import java.time.Instant;
import java.util.Date;

public class FlightDTO {

    @Schema(description = "ИД полета")
    private Integer flightId;

    @Schema(description = "ИД авиакомпании")
    private Integer companyId;

    @Schema(description = "Наименование авиакомпании")
    private String companyName;

    @Schema(description = "ИД аэропорта вылета")
    private Integer airportDepartureId;

    @Schema(description = "Наименование аэропорта вылета")
    private Integer airportDepartureName;

    @Schema(description = "ИД города вылета")
    private Integer cityDepartureId;

    @Schema(description = "Наименование города вылета")
    private Integer cityDepartureName;

    @Schema(description = "ИД аэропорта прилета")
    private Integer airportArrivalId;

    @Schema(description = "Наименование аэропорта прилета")
    private Integer airportArrivalName;

    @Schema(description = "ИД города прилета")
    private Integer cityArrivalId;

    @Schema(description = "Наименование города прилета")
    private Integer cityArrivalName;

    @Schema(description = "Плановая дата вылета")
    private Instant flightPlanDeparture;

    @Schema(description = "Плановая дата прилета")
    private Instant flightPlanArrival;

    @Schema(description = "Фактическая дата вылета")
    private Instant flightFactDeparture;

    @Schema(description = "Фактическая дата прилета")
    private Instant flightFactArrival;

    public FlightDTO() {
    }

    public FlightDTO(Integer flightId,
                      Integer companyId,
                      Integer airportDepartureId,
                      Integer airportArrivalId,
                     Instant flightPlanDeparture,
                     Instant flightPlanArrival,
                     Instant flightFactDeparture,
                     Instant flightFactArrival) {
        this.flightId = flightId;
        this.companyId = companyId;
        this.airportDepartureId = airportDepartureId;
        this.airportArrivalId = airportArrivalId;
        this.flightPlanDeparture = flightPlanDeparture;
        this.flightPlanArrival = flightPlanArrival;
        this.flightFactDeparture = flightFactDeparture;
        this.flightFactArrival = flightFactArrival;
    }

    public Flight toEntity(){
        return toEntity(new Flight());
    }

    public Flight toEntity(Flight entity){
        entity.setAirportArrivalId(this.airportArrivalId);
        entity.setFlightId(this.flightId);
        entity.setAirportDepartureId(this.airportDepartureId);
        entity.setCompanyId(this.companyId);
        entity.setFlightFactArrival(this.flightFactArrival);
        entity.setFlightFactDeparture(this.flightFactDeparture);
        entity.setFlightPlanArrival(this.flightPlanArrival);
        entity.setFlightPlanDeparture(this.flightPlanDeparture);
        return entity;
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
