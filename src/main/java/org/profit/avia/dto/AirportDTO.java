package org.profit.avia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.profit.avia.model.Airport;

public class AirportDTO {

    @Schema(description = "ИД аэропорта")
    private Integer airportId;

    @Schema(description = "Код аэропорта")
    private String airportCode;

    @Schema(description = "Наименование аэропорта")
    private String airportName;

    @Schema(description = "ИД города")
    private Integer cityId;

    @Schema(description = "Наименование города")
    private String cityName;

    @Schema(description = "Широта")
    private Double airportLatitude;

    @Schema(description = "Долгота")
    private Double airportLongitude;

    @Schema(description = "Временная зона")
    private String airportTimeZone;

    public AirportDTO() {
    }

    public AirportDTO(Integer airportId,
                      String airportCode,
                      String airportName,
                      Integer cityId,
                      Double airportLatitude,
                      Double airportLongitude,
                      String airportTimeZone) {
        this.airportId = airportId;
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.cityId = cityId;
        this.airportLatitude = airportLatitude;
        this.airportLongitude = airportLongitude;
        this.airportTimeZone = airportTimeZone;
    }

    public Airport toEntity(){
        return toEntity(new Airport());
    }

    public Airport toEntity(Airport entity){
        entity.setAirportId(this.airportId);
        entity.setAirportCode(this.airportCode);
        entity.setAirportLatitude(this.airportLatitude);
        entity.setAirportLongitude(this.airportLongitude);
        entity.setAirportName(this.airportName);
        entity.setCityId(this.cityId);
        entity.setAirportTimeZone(this.airportTimeZone);
        return entity;
    }

    public Integer getAirportId() {
        return airportId;
    }

    public void setAirportId(Integer airportId) {
        this.airportId = airportId;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Double getAirportLatitude() {
        return airportLatitude;
    }

    public void setAirportLatitude(Double airportLatitude) {
        this.airportLatitude = airportLatitude;
    }

    public Double getAirportLongitude() {
        return airportLongitude;
    }

    public void setAirportLongitude(Double airportLongitude) {
        this.airportLongitude = airportLongitude;
    }

    public String getAirportTimeZone() {
        return airportTimeZone;
    }

    public void setAirportTimeZone(String airportTimeZone) {
        this.airportTimeZone = airportTimeZone;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
