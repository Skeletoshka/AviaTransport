package org.profit.avia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.profit.avia.model.City;

public class CityDTO {

    @Schema(description = "ИД города")
    private Integer cityId;

    @Schema(description = "Наименование города")
    private String cityName;

    public CityDTO() {
    }

    public CityDTO(Integer cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public City toEntity(){
        return toEntity(new City());
    }

    public City toEntity(City entity){
        entity.setCityName(this.cityName);
        entity.setCityId(this.cityId);
        return entity;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
