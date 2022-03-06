package com.infodev.bookrental.serviceImpl;

import com.infodev.bookrental.dto.TransactionDto;
import com.infodev.bookrental.enums.RentType;
import com.infodev.bookrental.model.Transaction;
import com.infodev.bookrental.repo.BookRepo;
import com.infodev.bookrental.repo.MemberRepo;
import com.infodev.bookrental.repo.TransactionRepo;
import com.infodev.bookrental.service.TransactionService;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.time.LocalDate;
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
    @Override
    public List<TransactionDto> findTransactionsByRentType(RentType rentType) {
        List<Transaction> books= transactionRepo.findAllByRentType(rentType);
        return books.stream().map(book ->  transactionToDto(book))
                .collect(Collectors.toList());
    }

    private Transaction dtoToTransaction(TransactionDto transactionDto) {
        LocalDate frDate=LocalDate.now();
        LocalDate toDate=frDate.plusDays(transactionDto.getNoOfDays());
        return Transaction.builder()
                .transactionId(transactionDto.getTransactionId())
                .code(transactionDto.getCode())
                .fromDate(frDate)
                .toDate(toDate)
                .rentType(RentType.RENT)
                .member(memberRepo.findById(transactionDto.getMember_id()).get())
                .book(bookRepo.findById(transactionDto.getBook_id()).get())

                .build();
    }

    private TransactionDto transactionToDto(Transaction transaction) {
        return TransactionDto.builder()
                .transactionId(transaction.getTransactionId())
                .code(transaction.getCode())
                .rentType(transaction.getRentType())
                .fromDate(transaction.getFromDate())
                .toDate(transaction.getToDate())
                .rentType(RentType.RENT)
                .member_id(transaction.getMember().getId())
                .book_id(transaction.getBook().getBookId())
                .bookName(transaction.getBook().getName())
                .build();
    }


}
