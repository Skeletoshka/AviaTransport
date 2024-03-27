package org.profit.avia.service;

import org.profit.avia.model.City;
import org.profit.avia.repository.CityRepository;
import org.profit.avia.utils.GridDataOption;
import org.profit.avia.utils.Query;
import org.profit.avia.validator.CityValidator;
import org.profit.avia.view.CityView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CityService extends BaseService<City>{

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityValidator cityValidator;

    @Value("classpath:/script/city/mainSql.sql")
    Resource mainSQL;

    @Value("classpath:/script/city/mainSqlForOne.sql")
    Resource mainSQLForOne;

    @PostConstruct
    protected void init() {
        super.init(cityRepository, cityValidator);
    }

    public List<CityView> getAll(GridDataOption gridDataOption){
        return new Query.QueryBuilder<CityView>(mainSQL)
                .forClass(CityView.class, "m0")
                .setParams(gridDataOption.buildParams())
                .setOrderBy(gridDataOption.getOrderBy())
                .setLimit(gridDataOption.buildPageRequest())
                .build()
                .execute();
    }

    public Integer getCount(GridDataOption gridDataOption){
        return new Query.QueryBuilder<CityView>(mainSQL)
                .forClass(CityView.class, "m0")
                .setParams(gridDataOption.buildParams())
                .setOrderBy(gridDataOption.getOrderBy())
                .build()
                .count();
    }

    public CityView getOne(Integer id){
        return new Query.QueryBuilder<CityView>(mainSQLForOne)
                .forClass(CityView.class, "m0")
                .build()
                .executeOne(id);
    }
}
