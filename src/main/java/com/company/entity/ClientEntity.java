package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "client")
public class ClientEntity extends BaseEntity {
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String phone;
    @Column
    private String profileName;
}