package org.profit.avia.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridDataOption {
    private List<NamedFilter> namedFilters = new ArrayList<>();
    private Integer rowCount;
    private Integer page;
    private String orderBy;

    public List<NamedFilter> getNamedFilters() {
        return namedFilters;
    }

    public void setNamedFilters(List<NamedFilter> namedFilters) {
        this.namedFilters = namedFilters;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean filterExist(String filterName){
        return namedFilters.stream().anyMatch(namedFilter -> namedFilter.name.equals(filterName));
    }

    public boolean filterExist(String filterName, Object filterDefaultValue){
        return namedFilters.stream().anyMatch(namedFilter -> namedFilter.name.equals(filterName)
                && !namedFilter.getValue().equals(filterDefaultValue));
    }

    /**Метод, для внедрения в запрос LIMIT*/
    public String buildPageRequest(){
        if(rowCount != null && rowCount != -1){
            return "LIMIT " + rowCount + " OFFSET " + (page - 1) * rowCount;
        }
        else{
            return "";
        }
    }

    public Map<String, Object> buildParams(){
        Map<String, Object> params = new HashMap<>();
        namedFilters.stream().forEach(nf -> {
            params.put(nf.getName(), nf.getValue());
        });
        return params;
    }

    public static class Builder{
        private List<NamedFilter> namedFilters;
        private String orderBy;
        private Integer rowCount;
        private Integer page;
        private String search;

        public Builder(){
            namedFilters = new ArrayList<>();
        }

        public Builder setParam(String name, Object value){
            namedFilters.add(new NamedFilter(name, value));
            return this;
        }

        public Builder setOrderBy(String orderBy){
            this.orderBy=orderBy;
            return this;
        }

        public Builder setRowCount(Integer rowCount){
            this.rowCount=rowCount;
            return this;
        }

        public Builder setPage(Integer page){
            this.page = page;
            return this;
        }

        public Builder setSearch(String search) {
            this.search = search;
            return this;
        }

        public GridDataOption build(){
            GridDataOption gridDataOption = new GridDataOption();
            gridDataOption.setRowCount(rowCount==null?10:rowCount);
            gridDataOption.setOrderBy(orderBy);
            gridDataOption.setNamedFilters(namedFilters);
            gridDataOption.setPage(page==null?1:page);
            return gridDataOption;
        }
    }

    public static class NamedFilter{
        private String name;
        private Object value;

        public NamedFilter() {
        }

        public NamedFilter(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
