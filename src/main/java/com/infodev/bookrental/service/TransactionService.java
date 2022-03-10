package com.infodev.bookrental.service;

import com.infodev.bookrental.dto.TransactionDto;
import com.infodev.bookrental.enums.RentType;
import com.infodev.bookrental.model.Transaction;
import com.infodev.bookrental.service.Generics.GenericsService;

import java.util.List;

public interface TransactionService extends GenericsService<TransactionDto,Integer> {
    List<TransactionDto> findTransactionsByRentType(RentType rentType);
    TransactionDto findTransactionByCode(String code );
}
