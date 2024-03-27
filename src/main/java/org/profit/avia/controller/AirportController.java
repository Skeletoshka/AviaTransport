package org.profit.avia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.profit.avia.dto.AirportDTO;
import org.profit.avia.model.Airport;
import org.profit.avia.response.DataResponse;
import org.profit.avia.service.BaseService;
import org.profit.avia.service.AirportService;
import org.profit.avia.utils.GridDataOption;
import org.profit.avia.view.AirportView;
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
@Tag(name = "Контроллер для аэропортов", description = "Контроллер для работы с аэропортами")
@RequestMapping(value = "/v1/apps",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
public class AirportController {

    static class GridDataOptionAirport extends GridDataOption {
        @Schema(description = "" +
                "<ul>" +
                    "<li> cityId - ИД города" +
                "<ul>")
        public List<NamedFilter> getNamedFilters(){
            return super.getNamedFilters();
        }
    }

    @Autowired
    private AirportService airportService;

    @Operation(summary = "Возвращает список объектов \"Аэропорт\"",
            description = "Вовзращает список объектов согласно переданным фильтрам")
    @RequestMapping(value = "airport/getlist", method = RequestMethod.POST)
    public DataResponse<AirportView> getList(@RequestBody GridDataOptionAirport gridDataOption){
        boolean findCity = gridDataOption.filterExist("cityId");
        if(!findCity){
            gridDataOption.getNamedFilters().add(new GridDataOption.NamedFilter("cityId", -1));
        }
        List<AirportView> result = airportService.getAll(gridDataOption);
        Integer count = airportService.getCount(gridDataOption);
        return BaseService.buildResponse(result, gridDataOption, count);
    }

    @Operation(summary = "Получение одной записи \"Аэропорт\" по ее идентификатору",
            description = "Получение одной записи \"Аэропорт\" по ее идентификатору. " +
                    "Если идентификатора нет, возвращается запись с полями по умолчанию")
    @RequestMapping(value = "airport/get", method = RequestMethod.POST)
    public AirportDTO get(@RequestBody(required = false) Integer id){
        if (id == null){
            return new AirportDTO();
        }else{
            AirportView view = airportService.getOne(id);
            AirportDTO dto = new AirportDTO();
            BeanUtils.copyProperties(view, dto);
            return dto;
        }
    }

    @Operation(summary = "Сохранение одной записи \"Аэропорт\"",
            description = "Сохранение одной записи \"Аэропорт\". " +
                    "Если идентификатора нет, то запись вставляется, иначе обновляется")
    @RequestMapping(value = "airport/save", method = RequestMethod.POST)
    public AirportView save(@RequestBody AirportDTO airportDTO){
        Airport result;
        if(airportDTO.getAirportId() == null){
            result = airportService.add(airportDTO.toEntity());
        }else{
            result = airportService.edit(airportDTO.toEntity());
        }
        return airportService.getOne(result.getAirportId());
    }

    @Operation(summary = "Удаление записей \"Аэропорт\"",
            description = "Удаление записей \"Аэропорт\". " +
                    "Удаление записей по их идентификаторам")
    @RequestMapping(value = "airport/delete", method = RequestMethod.POST)
    public String delete(@RequestBody int[] ids){
        airportService.delete(ids);
        return BaseService.STANDARD_SUCCESS;
    }
}
