package com.unicomer.prueba.tecnica.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.unicomer.prueba.tecnica.dto.SalesmanDtoRequest;
import com.unicomer.prueba.tecnica.dto.SalesmanDtoResponse;
import com.unicomer.prueba.tecnica.entity.Salesman;
/**
 * Class Mapper for Salesman, between entity to response and request to entity
 * @author Juan Pablo Duran
 * @version 1.0
 * @since 2022-26-2022
 */
public class SalesmanMapper {

    /**
     * Mapper Entity to DtoResponse
     * @param request
     * @return
     */
    public static Salesman toEntity(SalesmanDtoRequest request){
        if(request == null){
            return null;
        }
        return Salesman.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .birthday(request.getBirthday())
                .gender(request.getGender())
                .cellPhone(request.getCellPhone())
                .homePhone(request.getHomePhone())
                .addressHome(request.getAddressHome())
                .profession(request.getProfession())
                .incomes(request.getIncomes())
                .build();
    }

    /**
     * Mapper Dto Request To Entity
     * @param entity
     * @return
     */
    public static SalesmanDtoResponse toResponse(Salesman entity){
        if(entity == null){
            return null;
        }
        return SalesmanDtoResponse.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthday(entity.getBirthday())
                .gender(entity.getGender())
                .cellPhone(entity.getCellPhone())
                .homePhone(entity.getHomePhone())
                .addressHome(entity.getAddressHome())
                .profession(entity.getProfession())
                .incomes(entity.getIncomes())
                .build();
    }

    public static List<SalesmanDtoResponse> toListResponse(List<Salesman> list){
        if(list == null){
            return null;
        }
        return list.stream()
                .map(salesman ->SalesmanMapper.toResponse(salesman))
                .collect(Collectors.toList());

    }
}