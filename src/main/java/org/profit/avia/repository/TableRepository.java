package org.profit.avia.repository;

import org.profit.avia.rowmapper.RowMapForObject;
import org.profit.avia.utils.OrmUtils;
import org.profit.avia.utils.TableMetadata;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.stream.Collectors;

@Transactional(propagation = Propagation.REQUIRED)
public interface TableRepository<T> {
    /**Класс содержит основные методы для доступа к бд. Возвращаются данные из ОДНОЙ таблицы.
     * Методы: List<T> getAll(), Integer count(), void insert(T obj)
     * */

    //Заполняется при запуске приложения. См. config/InitApp
    Map<String, TableMetadata> metaDataMap = new HashMap<>();

    default List<T> getAll(){
        NamedParameterJdbcTemplate jdbc = OrmUtils.getJDBC();
        String sql = "SELECT * FROM " + OrmUtils.getTableName(this.getClass());
        OrmUtils.loggerSql(sql);
        RowMapForObject rowMapper = new RowMapForObject(metaDataMap.get(OrmUtils.getTableName(this.getClass()).toLowerCase(Locale.ROOT)).getModelClass());
        return (List<T>) jdbc.query(sql, rowMapper);
    }

    default T get(Integer id){
        return findWhere(OrmUtils.getTableName(this.getClass()) + "_id = :id", Map.of("id", id)).get(0);
    }

    default Integer count(){
        NamedParameterJdbcTemplate jdbc = OrmUtils.getJDBC();
        String sql = "SELECT COUNT(*) FROM " + OrmUtils.getTableName(this.getClass());
        OrmUtils.loggerSql(sql);
        return jdbc.queryForObject(sql, (Map<String, ?>) null, Integer.class);
    }

    default Integer insert(T obj) {
        NamedParameterJdbcTemplate jdbc = OrmUtils.getJDBC();
        TableMetadata tableMetadata = metaDataMap.get(OrmUtils.getTableName(this.getClass()).toLowerCase(Locale.ROOT));
        String columnsName = tableMetadata.getFields().stream()
                .map(field -> field.getColumnName() + ", ")
                .collect(Collectors.joining()).replaceAll(", $", "");
        String paramsName = tableMetadata.getFields().stream()
                .map(field -> ":" + field.getVarName() + ", ")
                .collect(Collectors.joining()).replaceAll(", $", "");
        String sql = String.format("INSERT INTO " + tableMetadata.getTableName() + "(%s) VALUES (%s)", columnsName, paramsName);
        Map<String, Object> params = new HashMap<>();
        Arrays.stream(obj.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotationsByType(Column.class).length>0)
                .peek(field -> {
                    try {
                        params.put(field.getName(), field.getAnnotationsByType(Id.class).length > 0 ?
                                (Arrays.stream(obj.getClass().getDeclaredMethods())
                                        .filter(method -> method.getName().toLowerCase(Locale.ROOT).equals("get" + field.getName().toLowerCase(Locale.ROOT)))
                                        .findFirst().orElseThrow().invoke(obj, null) == null ?
                                        nextValue(tableMetadata.getIdField().getDeclaredAnnotation(Column.class).name()
                                                + "_gen") :
                                        Arrays.stream(obj.getClass().getDeclaredMethods())
                                                .filter(method -> method.getName().toLowerCase(Locale.ROOT).equals("get" + field.getName().toLowerCase(Locale.ROOT)))
                                                .findFirst().orElseThrow().invoke(obj, null)) :
                                Arrays.stream(obj.getClass().getDeclaredMethods())
                                        .filter(method -> method.getName().toLowerCase(Locale.ROOT).equals("get" + field.getName().toLowerCase(Locale.ROOT)))
                                        .findFirst().orElseThrow().invoke(obj, null));
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                }).collect(Collectors.toList());
        OrmUtils.loggerSql(sql);
        jdbc.execute(sql, params, PreparedStatement::execute);
        return (Integer) params.get(tableMetadata.getIdField().getName());
    }

    default void insert(List<T> objects){
        objects.forEach(this::insert);
    }

    default void update(List<T> objects){
        objects.forEach(this::update);
    }

    default Integer update(T object){
        NamedParameterJdbcTemplate jdbc = OrmUtils.getJDBC();
        TableMetadata tableMetadata = metaDataMap.get(OrmUtils.getTableName(this.getClass()).toLowerCase(Locale.ROOT));
        String sql = "UPDATE " + tableMetadata.getTableName() + " SET ";
        Map<String, Object> params = new HashMap<>();
        sql = sql + tableMetadata.getFields().stream()
                .filter(field -> !field.getVarName().equals(tableMetadata.getIdField().getName()))
                .map(field -> {
                    String columnName = field.getColumnName();
                    try {
                        params.put(columnName, Arrays.stream(object.getClass().getDeclaredMethods())
                                .filter(method -> method.getName().toLowerCase(Locale.ROOT).equals("get" + field.getVarName().toLowerCase(Locale.ROOT)))
                                .findFirst().orElseThrow().invoke(object, null));
                        return columnName + " = :" + columnName;
                    } catch (Exception e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                }).collect(Collectors.joining(","));
        String idFieldName = tableMetadata.getIdField().getAnnotationsByType(Column.class)[0].name();
        try {
            params.put(idFieldName, Arrays.stream(object.getClass().getDeclaredMethods())
                    .filter(method -> method.getName().toLowerCase(Locale.ROOT)
                            .equals("get" + tableMetadata.getIdField().getName().toLowerCase(Locale.ROOT)))
                    .findFirst().orElseThrow().invoke(object, null));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(), e);
        }
        sql += " WHERE " + idFieldName + " = :" + idFieldName;
        OrmUtils.loggerSql(sql);
        jdbc.update(sql, params);
        return (Integer)params.get(idFieldName);
    }

    default int nextValue(String seqName){
        NamedParameterJdbcTemplate jdbc = OrmUtils.getJDBC();
        String sql = "SELECT NEXTVAL(:seqName) ";
        OrmUtils.loggerSql(sql);
        return jdbc.queryForObject(sql, Map.of("seqName", seqName), Integer.class);
    }

    default List<T> findWhere(String wherePlaceholder, String param0Name, Object param0Value){
        Map<String, Object> params = new HashMap<>();
        params.put(param0Name, param0Value);
        return findWhere(wherePlaceholder, params);
    }

    default List<T> findWhere(String wherePlaceholder, Map<String, Object> params){
        NamedParameterJdbcTemplate jdbc = OrmUtils.getJDBC();
        String sql = "SELECT * FROM " + OrmUtils.getTableName(this.getClass()) + " WHERE " + wherePlaceholder;
        RowMapForObject rowMapper = new RowMapForObject(metaDataMap.get(OrmUtils.getTableName(this.getClass()).toLowerCase(Locale.ROOT)).getModelClass());
        OrmUtils.loggerSql(sql);
        return (List<T>) jdbc.query(sql, params, rowMapper);
    }


    default <V> List<V> findListForObject(Resource sql, String firstParamName, Object firstParamValue, Class<V> cls){
        return findListForObject(sql, Map.of(firstParamName, firstParamValue), cls);
    }

    default <V> List<V> findListForObject(Resource sql, Map<String, Object> params, Class<V> cls){
        return findListForObject(OrmUtils.loadResource(sql), params, cls);
    }

    default <V> List<V> findListForObject(String sql, String firstParamName, Object firstParamValue, Class<V> cls){
        return findListForObject(sql, Map.of(firstParamName, firstParamValue), cls);
    }

    default <V> List<V> findListForObject(String sql, Map<String, Object> params, Class<V> cls){
        NamedParameterJdbcTemplate jdbc = OrmUtils.getJDBC();
        RowMapForObject rowMapper = new RowMapForObject(cls);
        OrmUtils.loggerSql(sql);
        return (List<V>)jdbc.query(sql, params, rowMapper);
    }

    default <V> V findForObject(String sql, String firstParamName, Object firstParamValue, Class<V> cls){
        return findForObject(sql, Map.of(firstParamName, firstParamValue), cls);
    }

    default <V> V findForObject(String sql, Map<String, Object> params, Class<V> cls){
        NamedParameterJdbcTemplate jdbc = OrmUtils.getJDBC();
        RowMapForObject rowMapper = new RowMapForObject(cls);
        OrmUtils.loggerSql(sql);
        return ((List<V>)jdbc.query(sql, params, rowMapper)).get(0);
    }

    default void executeSql(String sql){
        JdbcTemplate jdbc = OrmUtils.getJDBCTemplate();
        OrmUtils.loggerSql(sql);
        jdbc.execute(sql);
    }

    default void executeSql(String sql, Map<String, Object> params){
        NamedParameterJdbcTemplate jdbc = OrmUtils.getJDBC();
        OrmUtils.loggerSql(sql);
        jdbc.execute(sql, params, PreparedStatement::execute);
    }

    default void executeSql(String sql, String paramName, Object paramValue){
        executeSql(sql, Map.of(paramName, paramValue));
    }

    default void drop(String[] tables){
        Arrays.stream(tables).forEach(this::drop);
    }
    default void drop(String tableName){
        String sql = "DROP TABLE IF EXISTS %s CASCADE";
        executeSql(String.format(sql, tableName));
        sql = "DROP SEQUENCE IF EXISTS %s_id_gen";
        executeSql(String.format(sql, tableName));
    }

    default void delete(int id){
        String tableName = OrmUtils.getTableName(this.getClass());
        String sql = "DELETE FROM " + tableName + " WHERE " + tableName + "_id = :id";
        executeSql(sql, Map.of("id", id));
    }
}
