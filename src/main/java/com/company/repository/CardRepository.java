package com.company.repository;

import com.company.entity.CardEntity;
import com.company.enums.GeneralStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, String> {
    Optional<CardEntity> findByNumber(String number);

    List<CardEntity> findAllByClientId(Sort sort, String clientId);
    List<CardEntity> findAllByPhone(Sort sort, @Param("phone") String phone);

    @Transactional
    @Modifying
    @Query("update CardEntity set status=:status where id=:uuid")
    void changeStatus(@Param("status") GeneralStatus status, @Param("uuid") String uuid);

    @Transactional
    @Modifying
    @Query("update CardEntity set lastModifiedDate=:lastModifiedDate where id=:uuid")
    void updateLastModifiedDate(@Param("lastModifiedDate") LocalDateTime lastModifiedDate,
                                @Param("uuid") String uuid);
}