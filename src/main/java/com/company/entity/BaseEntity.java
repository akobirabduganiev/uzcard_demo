package com.company.entity;

import com.company.enums.GeneralStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;

    @Column
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Column
    @Enumerated(EnumType.STRING)
    private GeneralStatus status;
}