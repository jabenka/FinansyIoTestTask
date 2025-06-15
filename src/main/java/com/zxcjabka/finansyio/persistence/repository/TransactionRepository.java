package com.zxcjabka.finansyio.persistence.repository;

import com.zxcjabka.finansyio.persistence.entity.BalanceEntity;
import com.zxcjabka.finansyio.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findAllByBalanceOrderByTimestampDesc(BalanceEntity balance);
}
