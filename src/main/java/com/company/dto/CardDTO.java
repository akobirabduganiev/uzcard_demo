package com.company.dto;

import com.company.entity.ClientEntity;
import com.company.enums.GeneralStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDTO {
    private String id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private GeneralStatus status;
    private String number;
    private LocalDate expiredDate;
    private String balance;
    private String clientUuid;
    private ClientEntity client;
}
