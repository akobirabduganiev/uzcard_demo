package com.company.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChangeClientPhoneDTO {
    @NotBlank(message = "client id required!")
    private String clientId;
    @NotBlank(message = "new phone required!")
    private String newPhone;
}
