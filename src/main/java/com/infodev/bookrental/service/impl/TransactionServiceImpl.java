package com.infodev.bookrental.service.impl;

import com.infodev.bookrental.dto.TransactionDto;
import com.infodev.bookrental.model.Transaction;
import com.infodev.bookrental.repo.BookRepo;
import com.infodev.bookrental.repo.MemberRepo;
import com.infodev.bookrental.repo.TransactionRepo;
import com.infodev.bookrental.service.TransactionService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rawalokes
 * Date:3/5/22
 * Time:1:45 PM
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepo transactionRepo;
    private final MemberRepo memberRepo;
    private final BookRepo bookRepo;

    public TransactionServiceImpl(TransactionRepo transactionRepo, MemberRepo memberRepo, BookRepo bookRepo) {
        this.transactionRepo = transactionRepo;
        this.memberRepo = memberRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public TransactionDto create(TransactionDto transactionDto) throws IOException {
        Transaction transaction = dtoToTransaction(transactionDto);
        transaction = transactionRepo.save(transaction);
        return transactionToDto(transaction);
    }

    @Override
    public List<TransactionDto> showAll() {
        List<Transaction> transactionList = transactionRepo.findAll();
        return transactionList.stream()
                .map(transaction -> transactionToDto(transaction))
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer integer) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    private Transaction dtoToTransaction(TransactionDto transactionDto) {
        return Transaction.builder()
                .transactionId(transactionDto.getTransactionId())
                .code(transactionDto.getCode())
                .fromDate(transactionDto.getFromDate())
                .toDate(transactionDto.getToDate())
                .member(memberRepo.findById(transactionDto.getMember_id()).get())
                .book(bookRepo.findById(transactionDto.getBook_id()).get())
                .build();
    }

    private TransactionDto transactionToDto(Transaction transaction) {
        return TransactionDto.builder()
                .transactionId(transaction.getTransactionId())
                .code(transaction.getCode())
                .fromDate(transaction.getFromDate())
                .toDate(transaction.getToDate())
                .member_id(transaction.getMember().getId())
                .book_id(transaction.getBook().getBookId())
                .build();
    }
}
