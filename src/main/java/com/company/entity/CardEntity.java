package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "card")
public class CardEntity extends BaseEntity {
    @Column
    private String number;
    @Column
    private LocalDate expiredDate = LocalDate.now().plusYears(5);
    @Column
    private Long balance;
    @Column
    private String phone;

    @Column
    private String clientUuid;
    @ManyToOne
    @JoinColumn(name = "client_uuid")
    private ClientEntity client;


}