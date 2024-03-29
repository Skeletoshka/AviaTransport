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

import java.util.Date;
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
                    "<li> cityDepartureId - город вылета " +
                    "<li> cityArrivalId - город прилета " +
                    "<li> airportDepartureId - аэропорт вылета "+
                    "<li> airportArrivalId - аэропорт прилета " +
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
        boolean findAirportDepartureId = gridDataOption.filterExist("airportDepartureId");
        if(!findAirportDepartureId){
            gridDataOption.getNamedFilters().add(new GridDataOption.NamedFilter("airportDepartureId", -1));
        }
        boolean findCityDepartureId = gridDataOption.filterExist("cityDepartureId");
        if(!findCityDepartureId){
            gridDataOption.getNamedFilters().add(new GridDataOption.NamedFilter("cityDepartureId", -1));
        }
        boolean findAirportArrivalId = gridDataOption.filterExist("airportArrivalId");
        if(!findAirportArrivalId){
            gridDataOption.getNamedFilters().add(new GridDataOption.NamedFilter("airportArrivalId", -1));
        }
        boolean findCityArrivalId = gridDataOption.filterExist("cityArrivalId");
        if(!findCityArrivalId){
            gridDataOption.getNamedFilters().add(new GridDataOption.NamedFilter("cityArrivalId", -1));
        }
        boolean dateRangeArrivalFound = gridDataOption.filterExist("dateRangeArrival");
        if(!dateRangeArrivalFound){
            gridDataOption.getNamedFilters().add(new GridDataOption.NamedFilter("dateRangeArrival", -1));
        }else {
            List<Long> dateRange = (List<Long>) gridDataOption.getNamedFilters()
                    .stream()
                    .filter(nf -> nf.getName().equals("dateRangeArrival"))
                    .findFirst()
                    .orElseThrow()
                    .getValue();
            gridDataOption.getNamedFilters().add(new GridDataOption.NamedFilter("dateStartArrival", new Date(dateRange.get(0))));
            gridDataOption.getNamedFilters().add(new GridDataOption.NamedFilter("dateEndArrival", new Date(dateRange.get(1))));
        }
        boolean dateRangeDepartureFound = gridDataOption.filterExist("dateRangeDeparture");
        if(!dateRangeDepartureFound){
            gridDataOption.getNamedFilters().add(new GridDataOption.NamedFilter("dateRangeDeparture", -1));
        }else {
            List<Long> dateRange = (List<Long>) gridDataOption.getNamedFilters()
                    .stream()
                    .filter(nf -> nf.getName().equals("dateRangeDeparture"))
                    .findFirst()
                    .orElseThrow()
                    .getValue();
            gridDataOption.getNamedFilters().add(new GridDataOption.NamedFilter("dateStartDeparture", new Date(dateRange.get(0))));
            gridDataOption.getNamedFilters().add(new GridDataOption.NamedFilter("dateEndDeparture", new Date(dateRange.get(1))));
        }
        List<StatisticView> result = flightService.getStatistic(gridDataOption);
        Integer count = flightService.getStatisticCount(gridDataOption);
        return BaseService.buildResponse(result, gridDataOption, count);
    }
}
