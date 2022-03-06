package com.infodev.bookrental.controller;

import com.infodev.bookrental.enums.RentType;
import com.infodev.bookrental.service.TransactionService;
import com.infodev.bookrental.serviceImpl.BookServiceImpl;
import com.infodev.bookrental.serviceImpl.MemberServiceImpl;
import com.infodev.bookrental.serviceImpl.TransactionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rawalokes
 * Date:2/26/22
 * Time:11:50 AM
 */
@Controller
@RequestMapping("/return")
public class ReturnBookController {
    private final TransactionServiceImpl transactionService;
    private final MemberServiceImpl memberService;
    private final BookServiceImpl bookService;

    public ReturnBookController(TransactionServiceImpl transactionService, MemberServiceImpl memberService, BookServiceImpl bookService) {
        this.transactionService = transactionService;
        this.memberService = memberService;
        this.bookService = bookService;
    }

    @GetMapping("/setup")
    public String getAll(Model model) {
        model.addAttribute("bookTransactionDtoList", transactionService.findTransactionsByRentType(RentType.RETURN));
        return "transaction/return/returnbooksetup";
    }
}
