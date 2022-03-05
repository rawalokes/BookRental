package com.infodev.bookrental.controller;

import com.infodev.bookrental.service.impl.BookServiceImpl;
import com.infodev.bookrental.service.impl.MemberServiceImpl;
import com.infodev.bookrental.service.impl.TransactionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("rentList",transactionService.showAll());
        model.addAttribute("memberList",memberService.showAll());
        return "/transaction/rent/rentbook";
    }

    @GetMapping("/create")
    public String getCreateRent(Model model){
//        model.addAttribute("rentedBook",new );
        return "";
    }
}
