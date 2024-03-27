package org.profit.avia.view;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;

public class CityView {

    @Column(name = "city_id")
    @Schema(description = "ИД города")
    private Integer cityId;

    @Column(name = "city_name")
    @Schema(description = "Наименование города")
    private String cityName;

    public CityView() {
    }

    public CityView(Integer cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
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
