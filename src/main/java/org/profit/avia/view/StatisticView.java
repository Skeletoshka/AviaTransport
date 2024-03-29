package org.profit.avia.view;

import javax.persistence.Column;

public class StatisticView {

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "rating_departure")
    private Double ratingDeparture;

    @Column(name = "rating_arrival")
    private Double ratingArrival;

    @Column(name = "flight_count")
    private Integer flightCount;

    public StatisticView() {
    }

    public StatisticView(Integer companyId,
                         String companyName,
                         Double ratingDeparture,
                         Double ratingArrival) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.ratingDeparture = ratingDeparture;
        this.ratingArrival = ratingArrival;
    }

    public Integer getFlightCount() {
        return flightCount;
    }

    public void setFlightCount(Integer flightCount) {
        this.flightCount = flightCount;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getRatingDeparture() {
        return ratingDeparture;
    }

    public void setRatingDeparture(Double ratingDeparture) {
        this.ratingDeparture = ratingDeparture;
    }

    public Double getRatingArrival() {
        return ratingArrival;
    }

    public void setRatingArrival(Double ratingArrival) {
        this.ratingArrival = ratingArrival;
    }
}
