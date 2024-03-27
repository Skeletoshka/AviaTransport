package org.profit.avia.utils;

import org.profit.avia.repository.TableRepository;
import org.profit.avia.rowmapper.RowMapForObject;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.*;

public class Query<T> {
    private Class cls;
    private String sql;
    private Map<String, Object> params;
    private Map<String, String> replace;

    public Query(QueryBuilder queryBuilder){
        this.cls = queryBuilder.cls;
        this.sql = queryBuilder.sql;
        this.params = queryBuilder.params;
        this.replace = queryBuilder.replace;
    }

    public List<T> execute(){
        NamedParameterJdbcTemplate jdbc = OrmUtils.getJDBC();
        RowMapForObject rowMapper = new RowMapForObject(cls);
        for(Map.Entry<String, String> replacement:replace.entrySet()){
            while (sql.contains(replacement.getKey())) {
                sql = sql.replace(replacement.getKey(), replacement.getValue());
            }
        }
        OrmUtils.loggerSql(sql);
        if (params != null) {
            return (List<T>) jdbc.query(sql, params, rowMapper);
        }else{
            return (List<T>) jdbc.query(sql, rowMapper);
        }
    }

    public Integer count(){
        for(Map.Entry<String, String> replacement:replace.entrySet()){
            while (sql.contains(replacement.getKey())) {
                sql = sql.replace(replacement.getKey(), replacement.getValue());
            }
        }
        sql = "SELECT COUNT(*) FROM (" + sql + ") as t";
        NamedParameterJdbcTemplate jdbc = OrmUtils.getJDBC();
        OrmUtils.loggerSql(sql);
        return jdbc.queryForObject(sql, params, Integer.class);
    }

    public T executeOne(Integer id){
        params.put("id", id);
        List<T> objects = execute();
        if(objects == null || objects.isEmpty()) {
            throw new RuntimeException(String.format("Запись с id = %d не найдена", params.get("id") ));
        }else{
            return execute().get(0);
        }
    }

    public static class QueryBuilder <T>{

        private Class cls;
        private String sql;
        private String mainAlias;
        private Map<String, Object> params = new HashMap<>();
        private Map<String, String> replace = new HashMap<>();

        private static final String ORDERBY_PLACEHOLDER="/*ORDERBY_PLACEHOLDER*/";
        private static final String LIMIT_PLACEHOLDER="/*LIMIT_PLACEHOLDER*/";
        private static final String FROM_PLACEHOLDER="/*FROM_PLACEHOLDER*/";
        private static final String WHERE_PLACEHOLDER="/*WHERE_PLACEHOLDER*/";

        @Deprecated
        public QueryBuilder(String sql){
            this.sql = sql;
        }

        public QueryBuilder(Resource resource){
            this.sql = OrmUtils.loadResource(resource);
        }

        public QueryBuilder<T> forClass(Class cls, String mainAlias){
            this.cls = cls;
            this.mainAlias = mainAlias;
            return this;
        }

        public QueryBuilder<T> setParams(Map<String, Object> params){
            this.params.putAll(params);
            return this;
        }

        public QueryBuilder<T> setParam(String paramName, Object paramValue){
            params.put(paramName, paramValue);
            return this;
        }

        /**
         * Для выполнения сортировки в запросе должен быть ORDERBY_PLACEHOLDER
         * @param orderBy */
        public QueryBuilder<T> setOrderBy(String orderBy){
            Field orderByField = Arrays.stream(this.cls.getDeclaredFields())
                    .filter(field -> field.getName().toLowerCase(Locale.ROOT).equals(orderBy.toLowerCase(Locale.ROOT)))
                    .findFirst().orElse(null);
            if(orderByField!=null) {
                this.replace.put(ORDERBY_PLACEHOLDER, "ORDER BY " +
                        (orderByField.getAnnotationsByType(Column.class).length>0 ?
                                orderByField.getAnnotationsByType(Column.class)[0].name() : orderByField.getName()));
            }
            return this;
        }

        public QueryBuilder<T> setLimit(String limitPlaceholder){
            this.replace.put(LIMIT_PLACEHOLDER, limitPlaceholder);
            return this;
        }

        public QueryBuilder<T> injectSql(String placeholder, String sql){
            this.replace.put(placeholder, sql);
            return this;
        }

        public QueryBuilder<T> setSearch(String search){
            if(search != null && !search.isEmpty()){
                TableMetadata metaDataMap = TableRepository.metaDataMap.get(cls.getSimpleName().toLowerCase(Locale.ROOT).replace("view", ""));
                String tableName = metaDataMap.getTableName();
                String primaryKeyName = metaDataMap.getIdField().getAnnotationsByType(Column.class)[0].name();
                this.params.put("search", search);
                this.replace.put(FROM_PLACEHOLDER, "INNER JOIN " + tableName + "_ft ft ON " +
                        this.mainAlias + "." + primaryKeyName + " = ft." + primaryKeyName);
                this.replace.put(WHERE_PLACEHOLDER, "AND lower(ft.fulltext) LIKE lower(CONCAT('%', :search, '%'))");
            }
            return this;
        }

        public QueryBuilder<T> injectSqlIf(boolean condition, String placeholder, String sql){
            if(condition){
                injectSql(placeholder, sql);
            }
            return this;
        }

        public Query<T> build(){
            return new Query<>(this);
        }

    }
}
