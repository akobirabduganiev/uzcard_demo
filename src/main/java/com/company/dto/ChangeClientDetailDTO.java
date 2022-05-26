package com.company.dto;

import com.company.enums.GeneralStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChangeClientDetailDTO {
    private String uuid;
    private String name;
    private String surname;
    private GeneralStatus status;
}
