package com.infodev.bookrental.repo;

import com.infodev.bookrental.dto.TransactionDto;
import com.infodev.bookrental.enums.RentType;
import com.infodev.bookrental.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
    List<Transaction> findAllByRentType(RentType rentType);
    Transaction findTransactionByCode(String code );
}
