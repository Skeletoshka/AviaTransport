package org.profit.avia.utils;

import java.lang.reflect.Field;
import java.util.List;

public class TableMetadata {

    //Имя таблицы
    private String tableName;
    //Имя класса
    private Class modelClass;
    //Первычный ключ с аннотацией @Id
    private Field idField;
    //Поля таблицы
    private List<ColumnMetadata> fields;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Class getModelClass() {
        return modelClass;
    }

    public void setModelClass(Class modelClass) {
        this.modelClass = modelClass;
    }

    public Field getIdField() {
        return idField;
    }

    public void setIdField(Field idField) {
        this.idField = idField;
    }

    public List<ColumnMetadata> getFields() {
        return fields;
    }

    public void setFields(List<ColumnMetadata> fields) {
        this.fields = fields;
    }
}
