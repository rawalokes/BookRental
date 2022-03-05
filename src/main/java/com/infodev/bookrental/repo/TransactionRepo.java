package com.infodev.bookrental.repo;

import com.infodev.bookrental.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {
}
