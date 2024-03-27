package org.profit.avia.utils;

import java.lang.reflect.Method;

public class ColumnMetadata {

    private String columnName;
    private String varName;
    private boolean nullable;
    private String nullableMessage;
    private Integer textSize;
    private String textSizeMessage;
    private Method getMethod;
    private Method setMethod;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public String getNullableMessage() {
        return nullableMessage;
    }

    public void setNullableMessage(String nullableMessage) {
        this.nullableMessage = nullableMessage;
    }

    public Integer getTextSize() {
        return textSize;
    }

    public void setTextSize(Integer textSize) {
        this.textSize = textSize;
    }

    public String getTextSizeMessage() {
        return textSizeMessage;
    }

    public void setTextSizeMessage(String textSizeMessage) {
        this.textSizeMessage = textSizeMessage;
    }

    public Method getGetMethod() {
        return getMethod;
    }

    public void setGetMethod(Method getMethod) {
        this.getMethod = getMethod;
    }

    public Method getSetMethod() {
        return setMethod;
    }

    public void setSetMethod(Method setMethod) {
        this.setMethod = setMethod;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }
}
