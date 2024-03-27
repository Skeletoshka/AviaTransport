package org.profit.avia.service;

import org.profit.avia.model.Flight;
import org.profit.avia.repository.FlightRepository;
import org.profit.avia.utils.GridDataOption;
import org.profit.avia.utils.Query;
import org.profit.avia.validator.FlightValidator;
import org.profit.avia.view.FlightView;
import org.profit.avia.view.StatisticView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class FlightService extends BaseService<Flight>{

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightValidator flightValidator;

    @Value("classpath:/script/flight/mainSql.sql")
    Resource mainSQL;

    @Value("classpath:/script/flight/mainSqlForOne.sql")
    Resource mainSQLForOne;

    @Value("classpath:/script/flight/statistic.sql")
    Resource statisticSQL;

    @PostConstruct
    protected void init() {
        super.init(flightRepository, flightValidator);
    }

    public List<FlightView> getAll(GridDataOption gridDataOption){
        boolean findCompany = gridDataOption.filterExist("companyId", -1);
        boolean findAirportDepartureId = gridDataOption.filterExist("airportDepartureId", -1);
        boolean findCityDepartureId = gridDataOption.filterExist("cityDepartureId", -1);
        boolean findAirportArrivalId = gridDataOption.filterExist("airportArrivalId", -1);
        boolean findCityArrivalId = gridDataOption.filterExist("cityArrivalId", -1);
        return new Query.QueryBuilder<FlightView>(mainSQL)
                .forClass(FlightView.class, "m0")
                .setParams(gridDataOption.buildParams())
                .setOrderBy(gridDataOption.getOrderBy())
                .setLimit(gridDataOption.buildPageRequest())
                .injectSqlIf(findCompany, "/*COMPANY_PLACEHOLDER*/", "AND CO.company_id = :company_id")
                .injectSqlIf(findAirportDepartureId, "/*AIRPORT_DEPARTURE_PLACEHOLDER*/", "AND AD.airport_id = :airportDepartureId")
                .injectSqlIf(findCityDepartureId, "/*CITY_DEPARTURE_PLACEHOLDER*/", "AND CD.city_id = :cityDepartureId")
                .injectSqlIf(findAirportArrivalId, "/*AIRPORT_ARRIVAL_PLACEHOLDER*/", "AND AA.airport_id = :airportArrivalId")
                .injectSqlIf(findCityArrivalId, "/*CITY_ARRIVAL_PLACEHOLDER*/", "AND CA.city_id = :cityArrivalId")
                .build()
                .execute();
    }

    public Integer getCount(GridDataOption gridDataOption){
        boolean findCompany = gridDataOption.filterExist("companyId", -1);
        boolean findAirportDepartureId = gridDataOption.filterExist("airportDepartureId", -1);
        boolean findCityDepartureId = gridDataOption.filterExist("cityDepartureId", -1);
        boolean findAirportArrivalId = gridDataOption.filterExist("airportArrivalId", -1);
        boolean findCityArrivalId = gridDataOption.filterExist("cityArrivalId", -1);
        return new Query.QueryBuilder<FlightView>(mainSQL)
                .forClass(FlightView.class, "m0")
                .setParams(gridDataOption.buildParams())
                .setOrderBy(gridDataOption.getOrderBy())
                .injectSqlIf(findCompany, "/*COMPANY_PLACEHOLDER*/", "AND CO.company_id = :company_id")
                .injectSqlIf(findAirportDepartureId, "/*AIRPORT_DEPARTURE_PLACEHOLDER*/", "AND AD.airport_id = :airportDepartureId")
                .injectSqlIf(findCityDepartureId, "/*CITY_DEPARTURE_PLACEHOLDER*/", "AND CD.city_id = :cityDepartureId")
                .injectSqlIf(findAirportArrivalId, "/*AIRPORT_ARRIVAL_PLACEHOLDER*/", "AND AA.airport_id = :airportArrivalId")
                .injectSqlIf(findCityArrivalId, "/*CITY_ARRIVAL_PLACEHOLDER*/", "AND CA.city_id = :cityArrivalId")
                .build()
                .count();
    }

    public FlightView getOne(Integer id){
        return new Query.QueryBuilder<FlightView>(mainSQLForOne)
                .forClass(FlightView.class, "m0")
                .build()
                .executeOne(id);
    }

    public List<StatisticView> getStatistic(GridDataOption gridDataOption){
        return new Query.QueryBuilder<StatisticView>(statisticSQL)
                .forClass(StatisticView.class, "m0")
                .setParams(gridDataOption.buildParams())
                .setOrderBy(gridDataOption.getOrderBy())
                .setLimit(gridDataOption.buildPageRequest())
                .build()
                .execute();
    }

    public Integer getStatisticCount(GridDataOption gridDataOption){
        return new Query.QueryBuilder<StatisticView>(statisticSQL)
                .forClass(StatisticView.class, "m0")
                .setParams(gridDataOption.buildParams())
                .setOrderBy(gridDataOption.getOrderBy())
                .build()
                .count();
    }
}
