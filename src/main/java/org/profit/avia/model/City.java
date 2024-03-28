package org.profit.avia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "city")
public class City {

    @Id
    @Column(name = "city_id", nullable = false)
    private Integer cityId;

    @Column(name = "city_name", nullable = false)
    @Size(max = 255, message = "Поле \"Наименование города\" не может иметь более {max} символов")
    @NotNull(message = "Поле \"Наименование города\" не может быть пустым")
    private String cityName;

    public City() {
    }

    public City(Integer cityId, String cityName) {
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
