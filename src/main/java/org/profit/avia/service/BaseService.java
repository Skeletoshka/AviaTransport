package org.profit.avia.service;

import org.profit.avia.repository.TableRepository;
import org.profit.avia.response.DataResponse;
import org.profit.avia.rowmapper.RowMapForObject;
import org.profit.avia.utils.GridDataOption;
import org.profit.avia.utils.OrmUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Map;

public abstract class BaseService<T> {

    private static Logger logger = LoggerFactory.getLogger(BaseService.class);

    public static String STANDARD_SUCCESS = "{\"status\":\"success\"}";
    TableRepository<T> tableRepository;
    Validator validator;
    protected abstract void init();

    public void init(TableRepository<T> tableRepository, Validator validator){
        this.tableRepository=tableRepository;
        this.validator = validator;
    }
    protected Object findQuery(String sql, Map<String, Object> params, Class cls){
        NamedParameterJdbcTemplate jdbc = OrmUtils.getJDBC();
        RowMapForObject rowMapper = new RowMapForObject(cls);
        OrmUtils.loggerSql(sql);
        return jdbc.query(sql, params, rowMapper);
    }

    public T add(T obj){
        beforeValidate(obj);
        validate(obj);
        Integer id = tableRepository.insert(obj);
        return tableRepository.get(id);
    }

    public T edit(T obj){
        beforeValidate(obj);
        validate(obj);
        Integer id = tableRepository.update(obj);
        return tableRepository.get(id);
    }

    public void delete(int[] ids){
        for(int id: ids){
            delete(id);
        }
    }

    public void delete(int id){
        tableRepository.delete(id);
    }

    private void validate(T obj){
        DataBinder dataBinder = new DataBinder(obj);
        dataBinder.addValidators(this.validator);
        dataBinder.validate();
        if(dataBinder.getBindingResult().hasErrors()){
            logger.error(dataBinder.getBindingResult().getAllErrors().toString());
            throw new RuntimeException(dataBinder.getBindingResult().getAllErrors().toString());
        }
    }

    protected void beforeValidate(T obj){}

    public static <V> DataResponse<V> buildResponse(List<V> data, GridDataOption gridDataOption, Integer count){
        return new DataResponse<V>(data, gridDataOption.getRowCount(), gridDataOption.getPage(), count);
    }
}
