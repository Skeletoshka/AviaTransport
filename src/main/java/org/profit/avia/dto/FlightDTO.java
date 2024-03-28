package org.profit.avia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.profit.avia.model.Flight;
import java.time.Instant;

public class FlightDTO {

    @Schema(description = "ИД полета")
    private Integer flightId;

    @Schema(description = "Код рейса")
    private String flightCode;

    @Schema(description = "ИД авиакомпании")
    private Integer companyId;

    @Schema(description = "Наименование авиакомпании")
    private String companyName;

    @Schema(description = "ИД аэропорта вылета")
    private Integer airportDepartureId;

    @Schema(description = "Наименование аэропорта вылета")
    private String airportDepartureName;

    @Schema(description = "ИД города вылета")
    private Integer cityDepartureId;

    @Schema(description = "Наименование города вылета")
    private String cityDepartureName;

    @Schema(description = "ИД аэропорта прилета")
    private Integer airportArrivalId;

    @Schema(description = "Наименование аэропорта прилета")
    private String airportArrivalName;

    @Schema(description = "ИД города прилета")
    private Integer cityArrivalId;

    @Schema(description = "Наименование города прилета")
    private String cityArrivalName;

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
                     String flightCode,
                     Integer companyId,
                     Integer airportDepartureId,
                     Integer airportArrivalId,
                     Instant flightPlanDeparture,
                     Instant flightPlanArrival,
                     Instant flightFactDeparture,
                     Instant flightFactArrival) {
        this.flightId = flightId;
        this.flightCode = flightCode;
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
        entity.setFlightCode(this.flightCode);
        return entity;
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

    public String getAirportDepartureName() {
        return airportDepartureName;
    }

    public void setAirportDepartureName(String airportDepartureName) {
        this.airportDepartureName = airportDepartureName;
    }

    public Integer getCityDepartureId() {
        return cityDepartureId;
    }

    public void setCityDepartureId(Integer cityDepartureId) {
        this.cityDepartureId = cityDepartureId;
    }

    public String getCityDepartureName() {
        return cityDepartureName;
    }

    public void setCityDepartureName(String cityDepartureName) {
        this.cityDepartureName = cityDepartureName;
    }

    public String getAirportArrivalName() {
        return airportArrivalName;
    }

    public void setAirportArrivalName(String airportArrivalName) {
        this.airportArrivalName = airportArrivalName;
    }

    public Integer getCityArrivalId() {
        return cityArrivalId;
    }

    public void setCityArrivalId(Integer cityArrivalId) {
        this.cityArrivalId = cityArrivalId;
    }

    public String getCityArrivalName() {
        return cityArrivalName;
    }

    public void setCityArrivalName(String cityArrivalName) {
        this.cityArrivalName = cityArrivalName;
    }

}
