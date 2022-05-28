package com.company.dto;

import com.company.enums.GeneralStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardFilterDTO {
    private String clientId;
    private String cardNumber;
    private Integer cardId;

    private Long fromAmount;
    private Long toAmount;
    private String profileName;
    private GeneralStatus status;

}
