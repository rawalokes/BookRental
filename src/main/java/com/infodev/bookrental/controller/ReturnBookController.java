package com.infodev.bookrental.controller;

import com.infodev.bookrental.dto.TransactionDto;
import com.infodev.bookrental.enums.RentType;
import com.infodev.bookrental.serviceImpl.BookServiceImpl;
import com.infodev.bookrental.serviceImpl.MemberServiceImpl;
import com.infodev.bookrental.serviceImpl.TransactionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
        model.addAttribute("bookTransactionDtoList",
                transactionService.findTransactionsByRentType(RentType.RETURN));
        return "transaction/return/returnbooksetup";
    }

    @GetMapping("/code/{id}")
    public String getCode(@PathVariable("id") Integer id, Model model ){
        model.addAttribute("selected", true);
        model.addAttribute("returnData", transactionService.findById(id));
        return "transaction/return/view";
    }

    @GetMapping("return")
    public String openAddReturnPage( Model model){
        model.addAttribute("transactions", transactionService.showAll());
        return "transaction/return/returnbookcreate";
    }

//    @GetMapping("/create")
//    public String getCreateReturn(Model model){
//        model.addAttribute("returnData",new TransactionDto());
//        model.addAttribute("transactions", transactionService.showAll());
//        return "transaction/return/returnbookcreate";
//    }
    @GetMapping(value = "save/{id}")
    public String saveReturn( @PathVariable("id") Integer id) throws IOException {
//        TransactionDto transactionDto = transactionService.findById(id);

//        transactionDto.setRentType(RentType.RETURN);
//        transactionService.create(transactionDto);
        return "redirect:/return/setup";
    }

}
