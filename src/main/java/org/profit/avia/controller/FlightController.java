package org.profit.avia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.profit.avia.dto.FlightDTO;
import org.profit.avia.model.Flight;
import org.profit.avia.response.DataResponse;
import org.profit.avia.service.BaseService;
import org.profit.avia.service.FlightService;
import org.profit.avia.utils.GridDataOption;
import org.profit.avia.view.FlightView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Контроллер для рейсов", description = "Контроллер для работы с рейсами")
@RequestMapping(value = "/v1/apps/",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
public class FlightController {

    static class GridDataOptionFlight extends GridDataOption {
        @Schema(description = "" +
                "<ul>" +
                    "<li> companyId - ИД компании " +
                    "<li> airportDepartureId - ИД аэропорта вылета " +
                    "<li> cityDepartureId - ИД города вылета " +
                    "<li> airportArrivalId - ИД аэропорта прилета " +
                    "<li> cityArrivalId - ИД города прилета " +
                "<ul>")
        public List<NamedFilter> getNamedFilters(){
            return super.getNamedFilters();
        }
    }

    @Autowired
    private FlightService flightService;

    @Operation(summary = "Возвращает список объектов \"Рейс\"",
            description = "Вовзращает список объектов согласно переданным фильтрам")
    @RequestMapping(value = "flight/getlist", method = RequestMethod.POST)
    public DataResponse<FlightView> getList(@RequestBody GridDataOptionFlight gridDataOption){
        boolean findCompany = gridDataOption.filterExist("companyId");
        if(!findCompany){
            gridDataOption.getNamedFilters().add(new GridDataOption.NamedFilter("companyId", -1));
        }
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
        List<FlightView> result = flightService.getAll(gridDataOption);
        Integer count = flightService.getCount(gridDataOption);
        return BaseService.buildResponse(result, gridDataOption, count);
    }

    @Operation(summary = "Получение одной записи \"Рейс\" по ее идентификатору",
            description = "Получение одной записи \"Рейс\" по ее идентификатору. " +
                    "Если идентификатора нет, возвращается запись с полями по умолчанию")
    @RequestMapping(value = "flight/get", method = RequestMethod.POST)
    public FlightDTO get(@RequestBody(required = false) Integer id){
        if (id == null){
            return new FlightDTO();
        }else{
            FlightView view = flightService.getOne(id);
            FlightDTO dto = new FlightDTO();
            BeanUtils.copyProperties(view, dto);
            return dto;
        }
    }

    @Operation(summary = "Сохранение одной записи \"Рейс\"",
            description = "Сохранение одной записи \"Рейс\". " +
                    "Если идентификатора нет, то запись вставляется, иначе обновляется")
    @RequestMapping(value = "flight/save", method = RequestMethod.POST)
    public FlightView save(@RequestBody FlightDTO flightDTO){
        Flight result;
        if(flightDTO.getFlightId() == null){
            result = flightService.add(flightDTO.toEntity());
        }else{
            result = flightService.edit(flightDTO.toEntity());
        }
        return flightService.getOne(result.getFlightId());
    }

    @Operation(summary = "Удаление записей \"Рейс\"",
            description = "Удаление записей \"Рейс\". " +
                    "Удаление записей по их идентификаторам")
    @RequestMapping(value = "flight/delete", method = RequestMethod.POST)
    public String delete(@RequestBody int[] ids){
        flightService.delete(ids);
        return BaseService.STANDARD_SUCCESS;
    }
}
