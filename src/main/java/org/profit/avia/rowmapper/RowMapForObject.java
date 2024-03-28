package org.profit.avia.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import javax.persistence.Column;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Locale;

public class RowMapForObject implements RowMapper<Object> {
    private Class cls;
    private final Constructor defaultConstructor;

    public RowMapForObject(Class cls){
        this.cls = cls;
        try {
            defaultConstructor = cls.getConstructor(null);
        }catch (NoSuchMethodException e){
            throw new RuntimeException("Класс не имеет конструктора по умолчанию", e);
        }
    }

    //основной компонент для преобразования
    @Override
    public Object mapRow(ResultSet resultSet, int i) {
        Object obj = null;
        try {
            obj = defaultConstructor.newInstance(null);
            Field[] fields = obj.getClass().getDeclaredFields();
            ResultSetMetaData metadata = resultSet.getMetaData();
            for (int j = 1; j <= metadata.getColumnCount(); j++) {
                String columnName = metadata.getColumnName(j);
                Field resultField = Arrays.stream(fields)
                        .filter(field -> field.getAnnotationsByType(Column.class)[0].name().equals(columnName))
                        .findFirst().orElse(null);
                if (resultField != null) {
                    Method setMethod = Arrays.stream(obj.getClass().getMethods())
                            .filter(method -> method.getName().toLowerCase(Locale.ROOT).equals("set" + resultField.getName().toLowerCase(Locale.ROOT)))
                            .findFirst().orElseThrow();
                    Object obj1 = resultSet.getObject(j) instanceof Timestamp?((Timestamp)resultSet.getObject(j)).toInstant():resultSet.getObject(j);
                    setMethod.invoke(obj, resultSet.getObject(j) instanceof Timestamp?((Timestamp)resultSet.getObject(j)).toInstant():resultSet.getObject(j));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return obj;
    }
}
