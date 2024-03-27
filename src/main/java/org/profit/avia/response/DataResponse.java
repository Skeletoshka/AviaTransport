package org.profit.avia.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Класс для ответа на getlist")
public class DataResponse <T>{
    @Schema(description = "Возвращаемые данные")
    private List<T> result;

    @Schema(description = "Количество строк на странице на клиенте")
    private Integer rowCount;

    @Schema(description = "Страница на клиенте")
    private Integer page;

    @Schema(description = "Всего строк")
    private Integer allRowCount;

    public DataResponse() {
    }

    public DataResponse(List<T> result,
                        Integer rowCount,
                        Integer page,
                        Integer allRowCount) {
        this.result = result;
        this.rowCount = rowCount;
        this.page = page;
        this.allRowCount = allRowCount;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
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

    public Integer getAllRowCount() {
        return allRowCount;
    }

    public void setAllRowCount(Integer allRowCount) {
        this.allRowCount = allRowCount;
    }
}
