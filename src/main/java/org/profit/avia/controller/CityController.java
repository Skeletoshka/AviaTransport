package org.profit.avia.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.profit.avia.dto.CityDTO;
import org.profit.avia.model.City;
import org.profit.avia.response.DataResponse;
import org.profit.avia.service.BaseService;
import org.profit.avia.service.CityService;
import org.profit.avia.utils.GridDataOption;
import org.profit.avia.view.CityView;
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
@Tag(name = "Контроллер для городов", description = "Контроллер для работы с городами")
@RequestMapping(value = "/v1/apps",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@Transactional
public class CityController {

    static class GridDataOptionCity extends GridDataOption {
        @Schema(description = "" +
                "<ul>" +
                "<ul>")
        public List<NamedFilter> getNamedFilters(){
            return super.getNamedFilters();
        }
    }

    @Autowired
    private CityService cityService;

    @Operation(summary = "Возвращает список объектов \"Город\"",
            description = "Возвращает список объектов согласно переданным фильтрам")
    @RequestMapping(value = "city/getlist", method = RequestMethod.POST)
    public DataResponse<CityView> getList(@RequestBody GridDataOptionCity gridDataOption){
        List<CityView> result = cityService.getAll(gridDataOption);
        Integer count = cityService.getCount(gridDataOption);
        return BaseService.buildResponse(result, gridDataOption, count);
    }

    @Operation(summary = "Получение одной записи \"Город\" по ее идентификатору",
            description = "Получение одной записи \"Город\" по ее идентификатору. " +
                    "Если идентификатора нет, возвращается запись с полями по умолчанию")
    @RequestMapping(value = "city/get", method = RequestMethod.POST)
    public CityDTO get(@RequestBody(required = false) Integer id){
        if (id == null){
            return new CityDTO();
        }else{
            CityView view = cityService.getOne(id);
            CityDTO dto = new CityDTO();
            BeanUtils.copyProperties(view, dto);
            return dto;
        }
    }

    @Operation(summary = "Сохранение одной записи \"Город\"",
            description = "Сохранение одной записи \"Город\". " +
                    "Если идентификатора нет, то запись вставляется, иначе обновляется")
    @RequestMapping(value = "city/save", method = RequestMethod.POST)
    public CityView save(@RequestBody CityDTO cityDTO){
        City result;
        if(cityDTO.getCityId() == null){
            result = cityService.add(cityDTO.toEntity());
        }else{
            result = cityService.edit(cityDTO.toEntity());
        }
        return cityService.getOne(result.getCityId());
    }

    @Operation(summary = "Удаление записей \"Город\"",
            description = "Удаление записей \"Город\". " +
                    "Удаление записей по их идентификаторам")
    @RequestMapping(value = "city/delete", method = RequestMethod.POST)
    public String delete(@RequestBody int[] ids){
        cityService.delete(ids);
        return BaseService.STANDARD_SUCCESS;
    }
}
