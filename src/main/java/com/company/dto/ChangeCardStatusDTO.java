package com.company.dto;

import com.company.enums.GeneralStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChangeCardStatusDTO {
    @NotBlank(message = "uuid required")
    private String uuid;
    @NotNull(message = "status required")
    private GeneralStatus status;
}
