package org.profit.avia.view;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;

public class AirportView {

    @Column(name = "airport_id")
    @Schema(description = "ИД аэропорта")
    private Integer airportId;

    @Column(name = "airport_code")
    @Schema(description = "Код аэропорта")
    private String airportCode;

    @Column(name = "airport_name")
    @Schema(description = "Наименование аэропорта")
    private String airportName;

    @Column(name = "city_id")
    @Schema(description = "ИД города")
    private Integer cityId;

    @Column(name = "city_name")
    @Schema(description = "Наименование города")
    private Integer cityName;

    @Column(name = "airport_latitude")
    @Schema(description = "Широта")
    private Double airportLatitude;

    @Column(name = "airport_longitude")
    @Schema(description = "Долгота")
    private Double airportLongitude;

    @Column(name = "airport_timezone")
    @Schema(description = "Временная зона")
    private String airportTimeZone;

    public AirportView() {
    }

    public AirportView(Integer airportId,
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

    public Integer getCityName() {
        return cityName;
    }

    public void setCityName(Integer cityName) {
        this.cityName = cityName;
    }
}
