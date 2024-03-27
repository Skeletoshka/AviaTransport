package org.profit.avia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.profit.avia.response.DataResponse;
import org.profit.avia.service.BaseService;
import org.profit.avia.service.FlightService;
import org.profit.avia.utils.GridDataOption;
import org.profit.avia.view.CityView;
import org.profit.avia.view.StatisticView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Контроллер для публичного контура", description = "Контроллер для получения данных неавторизованному пользователю")
@RequestMapping(value = "/public",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
public class PublicController {

    static class GridDataOptionStatistics extends GridDataOption {
        @Schema(description = "" +
                "<ul>" +
                "<ul>")
        public List<NamedFilter> getNamedFilters(){
            return super.getNamedFilters();
        }
    }

    @Autowired
    private FlightService flightService;

    @Operation(summary = "Возвращает список объектов \"Статистика\"",
            description = "Возращает список объектов согласно переданным фильтрам")
    @RequestMapping(value = "getstatistic", method = RequestMethod.POST)
    public DataResponse<StatisticView> getStatistic(@RequestBody GridDataOptionStatistics gridDataOption){
        List<StatisticView> result = flightService.getStatistic(gridDataOption);
        Integer count = flightService.getStatisticCount(gridDataOption);
        return BaseService.buildResponse(result, gridDataOption, count);
    }
}
