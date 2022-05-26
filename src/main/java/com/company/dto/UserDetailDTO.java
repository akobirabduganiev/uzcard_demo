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
public class UserDetailDTO {
    @NotBlank(message = "name required!")
    private String name;
    @NotBlank(message = "surname required!")
    private String surname;

    private String phone;
    @NotNull(message = "status required!")
    private GeneralStatus status;

}
