package org.profit.avia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "airport")
public class Airport {

    @Id
    @Column(name = "airport_id", nullable = false)
    private Integer airportId;

    @Column(name = "airport_code", nullable = false)
    @Size(max = 15, message = "Поле \"Код аэропорта\" не может иметь более {max} символов")
    @NotNull(message = "Поле \"Код аэропорта\" не может быть пустым")
    private String airportCode;

    @Column(name = "airport_name", nullable = false)
    @Size(max = 255, message = "Поле \"Наименование аэропорта\" не может иметь более {max} символов")
    @NotNull(message = "Поле \"Наименование аэропорта\" не может быть пустым")
    private String airportName;

    @Column(name = "city_id", nullable = false)
    @NotNull(message = "Поле \"ИД города\" не может быть пустым")
    private Integer cityId;

    @Column(name = "airport_latitude", nullable = false)
    @NotNull(message = "Поле \"Широта\" не может быть пустым")
    private Double airportLatitude;

    @Column(name = "airport_longitude", nullable = false)
    @NotNull(message = "Поле \"Долгота\" не может быть пустым")
    private Double airportLongitude;

    @Column(name = "airport_timezone", nullable = false)
    @Size(max = 255, message = "Поле \"Временная зона\" не может иметь более {max} символов")
    @NotNull(message = "Поле \"Временная зона\" не может быть пустым")
    private String airportTimeZone;

    public Airport() {
    }

    public Airport(Integer airportId,
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
}
