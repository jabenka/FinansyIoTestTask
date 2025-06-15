package com.zxcjabka.finansyio.persistence.repository;

import com.zxcjabka.finansyio.persistence.entity.BalanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BalanceRepository extends JpaRepository<BalanceEntity,UUID> {
    Optional<BalanceEntity> findBalanceEntityByName(String name);

    boolean existsBalanceEntitiesByName(String name);
}
