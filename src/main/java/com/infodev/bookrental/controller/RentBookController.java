package com.infodev.bookrental.controller;

import com.infodev.bookrental.dto.ResponseDto;
import com.infodev.bookrental.dto.TransactionDto;
import com.infodev.bookrental.enums.RentType;
import com.infodev.bookrental.serviceImpl.BookServiceImpl;
import com.infodev.bookrental.serviceImpl.MemberServiceImpl;
import com.infodev.bookrental.serviceImpl.TransactionServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        model.addAttribute("rentedBook", transactionService.findTransactionsByRentType(RentType.RENT));

        return "/transaction/rent/rentbook";
    }


    @GetMapping("/create")
    public String getCreateRent(Model model) {
        model.addAttribute("rentBook", new TransactionDto());
        model.addAttribute("members", memberService.showAll());
        model.addAttribute("books", bookService.showAll());
        return "/transaction/rent/rentbookcreate";
    }

    @PostMapping("/create")
    public String postCreateRent(@Valid @ModelAttribute("rentBook") TransactionDto transactionDto
            , BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("members", memberService.showAll());
            model.addAttribute("books", bookService.showAll());
            return "/transaction/rent/rentbookcreate";
        }

        transactionDto.setRentType(RentType.RENT);
        ResponseDto responseDto = transactionService.create(transactionDto);
        if (responseDto.isResponseStatus()){ return "redirect:/rent/setup";}

        model.addAttribute("members", memberService.showAll());
        model.addAttribute("books", bookService.showAll());
        model.addAttribute("errormessage", responseDto.getResponse());
        return "/transaction/rent/rentbookcreate";
    }

    @GetMapping("/update/{id}")
    public String updateBookByID(@PathVariable Integer id, Model model) {
//        TransactionDto transactionDto=transactionService.findById(id);
//        model.addAttribute("rentBook",transactionDto);
        model.addAttribute("members", memberService.showAll());
        model.addAttribute("books", bookService.showAll());
        return "/transaction/rent/rentbookcreate";
    }
}
