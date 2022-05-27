package com.unicomer.prueba.tecnica.dto;

import java.io.Serializable;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
/**
 * Class DTO Response for Salesman
 * @author Juan Pablo Duran
 * @version 1.0
 * @since 2022-26-2022
 */
@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(  description = "Class DTO Response for salesman",value ="SalesmanDtoResponse" )
public class SalesmanDtoResponse implements Serializable {
    
    private static final long serialVersionUID = 8490836394419255896L;

    @ApiModelProperty(name = "id", example = "1" ,position = 0)
    private int id;

    @ApiModelProperty(name = "firstName", example = "Juan" ,position = 1)
    private String firstName;

    @ApiModelProperty(name = "lastName", example = "Parra" ,position = 2)
    private String lastName;

    @ApiModelProperty(name = "birthday", example = "1980-05-27" ,position = 3)
    private LocalDate birthday;
    
    @ApiModelProperty(name = "gender", example = "Male" ,position = 4)
    private String gender;

    @ApiModelProperty(name = "cellPhone", example = "999999999" ,position = 5)
    private String cellPhone;

    @ApiModelProperty(name = "homePhone", example = "555555555" ,position = 6)
    private String homePhone;

    @ApiModelProperty(name = "addressHome", example = "Evergreen Terrace 742" ,position = 7)
    private String addressHome;

    @ApiModelProperty(name = "profession", example = "Engineer" ,position = 8)
    private String profession;

    @ApiModelProperty(name = "incomes", example = "180000" ,position = 9)
    private int incomes;
}