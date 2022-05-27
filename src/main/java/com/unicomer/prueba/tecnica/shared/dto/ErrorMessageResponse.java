package com.unicomer.prueba.tecnica.shared.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "ErrorMessageResponse")
public class ErrorMessageResponse {
    @ApiModelProperty(notes = "The unique uri identifier that categorizes the error", name = "type",
            required = true, example = "/errors/authentication/not-authorized")
    private String type;
    @ApiModelProperty(notes = "A brief, human-readable message about the error", name = "title",
            required = true, example = "The user does not have authorization")
    private String title;
    @ApiModelProperty(notes = "The unique error code", name = "code",
            required = false, example = "192")
    private String code;
    @ApiModelProperty(notes = "A human-readable explanation of the error", name = "detail",
            required = true, example = "The user does not have the properly permissions to access")
    private String detail;
    @ApiModelProperty(notes = "A URI that identifies the specific occurrence of the error", name = "detail",
            required = true, example = "/errors/authentication/not-authorized/01")
    private String instance;

}
