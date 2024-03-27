package org.profit.avia.service;

import org.profit.avia.model.Airport;
import org.profit.avia.repository.AirportRepository;
import org.profit.avia.utils.GridDataOption;
import org.profit.avia.utils.Query;
import org.profit.avia.validator.AirportValidator;
import org.profit.avia.view.AirportView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class AirportService extends BaseService<Airport>{

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirportValidator airportValidator;

    @Value("classpath:/script/airport/mainSql.sql")
    Resource mainSQL;

    @Value("classpath:/script/airport/mainSqlForOne.sql")
    Resource mainSQLForOne;

    @PostConstruct
    protected void init() {
        super.init(airportRepository, airportValidator);
    }

    public List<AirportView> getAll(GridDataOption gridDataOption){
        boolean findCity = gridDataOption.filterExist("cityId", -1);
        return new Query.QueryBuilder<AirportView>(mainSQL)
                .forClass(AirportView.class, "m0")
                .setParams(gridDataOption.buildParams())
                .setOrderBy(gridDataOption.getOrderBy())
                .setLimit(gridDataOption.buildPageRequest())
                .injectSqlIf(findCity, "/*CITY_PLACEHOLDER*/", "AND C.city_id = :cityId")
                .build()
                .execute();
    }

    public Integer getCount(GridDataOption gridDataOption){
        boolean findCity = gridDataOption.filterExist("cityId", -1);
        return new Query.QueryBuilder<AirportView>(mainSQL)
                .forClass(AirportView.class, "m0")
                .setParams(gridDataOption.buildParams())
                .setOrderBy(gridDataOption.getOrderBy())
                .injectSqlIf(findCity, "/*CITY_PLACEHOLDER*/", "AND C.city_id = :cityId")
                .build()
                .count();
    }

    public AirportView getOne(Integer id){
        return new Query.QueryBuilder<AirportView>(mainSQLForOne)
                .forClass(AirportView.class, "m0")
                .build()
                .executeOne(id);
    }
}
