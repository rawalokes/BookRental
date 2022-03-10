package com.infodev.bookrental.serviceImpl;

import com.infodev.bookrental.dto.TransactionDto;
import com.infodev.bookrental.enums.RentType;
import com.infodev.bookrental.model.Book;
import com.infodev.bookrental.model.Transaction;
import com.infodev.bookrental.repo.BookRepo;
import com.infodev.bookrental.repo.MemberRepo;
import com.infodev.bookrental.repo.TransactionRepo;
import com.infodev.bookrental.service.TransactionService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
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
    public TransactionDto   create(TransactionDto transactionDto) throws IOException {
        Transaction transaction = dtoToTransaction(transactionDto);
        transaction = transactionRepo.save(transaction);
        Book book= bookRepo.findById(transactionDto.getBook_id()).get();
        book.setStockCount(book.getStockCount()-1);
        bookRepo.save(book);
        return transactionToDto(transaction);
    }

    @Override
    public List<TransactionDto> showAll() {
        List<Transaction> transactionList = transactionRepo.findAll();
        return transactionList.stream().map(transaction -> transactionToDto(transaction)).collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer integer) {
         Optional<Transaction> transaction= transactionRepo.findById(integer);
           if (transaction.isPresent()){
               Transaction tran=transaction.get() ;
               return transactionToDto(tran);
           }
           return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public List<TransactionDto> findTransactionsByRentType(RentType rentType) {
        List<Transaction> books = transactionRepo.findAllByRentType(rentType);
        return books.stream().map(book -> transactionToDto(book)).collect(Collectors.toList());
    }

    @Override
    public TransactionDto findTransactionByCode(String code) {
        Transaction transactionCode = transactionRepo.findTransactionByCode(code);
        return transactionToDto(transactionCode);
    }

    private Transaction dtoToTransaction(TransactionDto transactionDto) {
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = fromDate.plusDays(transactionDto.getNoOfDays());
        return Transaction.builder().transactionId(transactionDto.getTransactionId())
                .code(transactionDto.getCode())
                .fromDate(fromDate).toDate(toDate)
                .rentType(transactionDto.getRentType())
                .member(memberRepo.findById(transactionDto.getMember_id()).get()).book(bookRepo.findById(transactionDto.getBook_id()).get()).build();
    }

    private TransactionDto transactionToDto(Transaction transaction) {
        Period period = Period.between(transaction.getToDate(), transaction.getFromDate());
        int diff = period.getDays();
        return TransactionDto.builder()
                .transactionId(transaction.getTransactionId())
                .code(transaction.getCode())
                .rentType(transaction.getRentType())
                .noOfDays(diff).rentType(transaction.getRentType())
                .member_id(transaction.getMember().getId()).book_id(transaction.getBook().getBookId()).book(transaction.getBook()).build();
    }


}
