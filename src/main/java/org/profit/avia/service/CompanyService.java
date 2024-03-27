package org.profit.avia.service;

import org.profit.avia.model.Company;
import org.profit.avia.repository.CompanyRepository;
import org.profit.avia.utils.GridDataOption;
import org.profit.avia.utils.Query;
import org.profit.avia.validator.CompanyValidator;
import org.profit.avia.view.CompanyView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CompanyService extends BaseService<Company>{

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyValidator companyValidator;

    @Value("classpath:/script/company/mainSql.sql")
    Resource mainSQL;

    @Value("classpath:/script/company/mainSqlForOne.sql")
    Resource mainSQLForOne;

    @PostConstruct
    protected void init() {
        super.init(companyRepository, companyValidator);
    }

    public List<CompanyView> getAll(GridDataOption gridDataOption){
        return new Query.QueryBuilder<CompanyView>(mainSQL)
                .forClass(CompanyView.class, "m0")
                .setParams(gridDataOption.buildParams())
                .setOrderBy(gridDataOption.getOrderBy())
                .setLimit(gridDataOption.buildPageRequest())
                .build()
                .execute();
    }

    public Integer getCount(GridDataOption gridDataOption){
        return new Query.QueryBuilder<CompanyView>(mainSQL)
                .forClass(CompanyView.class, "m0")
                .setParams(gridDataOption.buildParams())
                .setOrderBy(gridDataOption.getOrderBy())
                .build()
                .count();
    }

    public CompanyView getOne(Integer id){
        return new Query.QueryBuilder<CompanyView>(mainSQLForOne)
                .forClass(CompanyView.class, "m0")
                .build()
                .executeOne(id);
    }
}
