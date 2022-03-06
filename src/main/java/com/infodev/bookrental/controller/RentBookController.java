package com.infodev.bookrental.controller;

import com.infodev.bookrental.dto.BookDto;
import com.infodev.bookrental.dto.TransactionDto;
import com.infodev.bookrental.enums.RentType;
import com.infodev.bookrental.model.Member;
import com.infodev.bookrental.model.Transaction;
import com.infodev.bookrental.serviceImpl.BookServiceImpl;
import com.infodev.bookrental.serviceImpl.MemberServiceImpl;
import com.infodev.bookrental.serviceImpl.TransactionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rawalokes
 * Date:2/26/22
 * Time:11:50 AM
 */
@Controller
@RequestMapping("/rent")
public class RentBookController {
    private final MemberServiceImpl memberService;
    private final BookServiceImpl bookService;
    private final TransactionServiceImpl transactionService;

    public RentBookController(MemberServiceImpl memberService, BookServiceImpl bookService, TransactionServiceImpl transactionService) {
        this.memberService = memberService;
        this.bookService = bookService;
        this.transactionService = transactionService;
    }

    @GetMapping("/setup")
    public String getAll(Model model) {
        model.addAttribute("rentedBook" , transactionService.findTransactionsByRentType(RentType.RENT));

        return "/transaction/rent/rentbook";
    }


    @GetMapping("/create")
    public String getCreateRent(Model model) {
        model.addAttribute("rentBook" ,new TransactionDto());
        model.addAttribute("members",memberService.showAll());
        model.addAttribute("books",bookService.showAll());
        return "/transaction/rent/rentbookcreate";
    }

    @PostMapping("/create")
    public String postCreateRent(@ModelAttribute ("rentBook") TransactionDto transactionDto) throws IOException {
        transactionService.create(transactionDto);
        return "redirect:/rent/setup";
    }
}
