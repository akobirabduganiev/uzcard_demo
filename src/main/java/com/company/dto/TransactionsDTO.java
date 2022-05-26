package com.company.dto;

import com.company.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionsDTO {
    private String uuid;
    private String fromCard;
    private String toCard;
    private Long amount;
    private LocalDateTime createdDate;
    private TransactionStatus status;
    private String profileName;
}
