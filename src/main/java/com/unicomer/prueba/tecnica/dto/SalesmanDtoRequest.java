package com.unicomer.prueba.tecnica.dto;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
/**
 * Class DTO Request for Salesman
 * @author Juan Pablo Duran
 * @version 1.0
 * @since 2022-26-2022
 */
@Data
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(  description = "Class DTO Request for salesman",value ="SalesmanDtoRequest" )
public class SalesmanDtoRequest implements Serializable {
    
    private static final long serialVersionUID = 8490836394419255896L;


    @NotNull(message = "Is required field")
    @NotBlank(message = "Is required field")
    @ApiModelProperty(name = "firstName", example = "Juan" ,position = 1)
    private String firstName;

    @NotNull(message = "Is required field")
    @NotBlank(message = "Is required field")
    @ApiModelProperty(name = "lastName", example = "Parra" ,position = 2)
    private String lastName;

    @NotNull(message = "Is required field")
    @ApiModelProperty(name = "birthday", example = "1980-01-01" ,position = 3)
    private LocalDate birthday;
    
    @NotNull(message = "Is required field")
    @NotBlank(message = "Is required field")
    @ApiModelProperty(name = "gender", example = "Male" ,position = 4)
    private String gender;

    @NotNull(message = "Is required field")
    @NotBlank(message = "Is required field")
    @ApiModelProperty(name = "cellPhone", example = "999999999" ,position = 5)
    private String cellPhone;

    @NotNull(message = "Is required field")
    @NotBlank(message = "Is required field")
    @ApiModelProperty(name = "homePhone", example = "555555555" ,position = 6)
    private String homePhone;

    @NotNull(message = "Is required field")
    @NotBlank(message = "Is required field")
    @ApiModelProperty(name = "addressHome", example = "Evergreen Terrace 742" ,position = 7)
    private String addressHome;

    @NotNull(message = "Is required field")
    @NotBlank(message = "Is required field")
    @ApiModelProperty(name = "profession", example = "Engineer" ,position = 8)
    private String profession;

    @Min(1)
    @ApiModelProperty(name = "incomes", example = "180000" ,position = 9)
    private int incomes;
}