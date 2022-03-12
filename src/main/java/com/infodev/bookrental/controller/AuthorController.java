package com.infodev.bookrental.controller;

import com.infodev.bookrental.components.SendEmailComponents;
import com.infodev.bookrental.dto.AuthorDto;
import com.infodev.bookrental.dto.ResponseDto;
import com.infodev.bookrental.serviceImpl.AuthorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author rawalokes
 * Date:2/22/22
 * Time:12:28 PM
 */
@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorServiceImpl authorService;
    private SendEmailComponents sendEmailComponents;

    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/getall")
    public String getAuthorDetails(Model model) {
        model.addAttribute("authorList", authorService.showAll());
        return "author/authorSetup";
    }

    @GetMapping("/create")
    public String getAddAuthor(Model model) {
        model.addAttribute("authorDetails", new AuthorDto());
        return "author/authorCreate";
    }

    @PostMapping("/create")
    public String postAddAuthor(@Valid  @ModelAttribute("authorDetails")AuthorDto authorDto
            , BindingResult bindingResult,Model model) {

        if (bindingResult.hasErrors()) {
           return "author/authorCreate";
        }

        ResponseDto responseDto=authorService.create(authorDto);
        System.out.println(responseDto.isResponseStatus());
        if (responseDto.isResponseStatus()) {
            return "redirect:/author/getall";
        }
        model.addAttribute("errormessage",responseDto.getResponse());
        return "author/authorCreate";
    }

    @GetMapping("/delete/{id}")
    public String removeAuthorByI(@PathVariable Integer id,Model model) {
        ResponseDto responseDto= authorService.deleteById(id);
       if (responseDto.isResponseStatus()){
           return "redirect:/author/getall";
       }
        model.addAttribute("errormessage",responseDto.getResponse());
        return "author/authorCreate";

    }

    @GetMapping("/update/{id}")
    public String updateAuthorByI(@PathVariable Integer id, Model model) {
        ResponseDto responseDto= authorService.findById(id);
        if (responseDto.isResponseStatus()){
            model.addAttribute("authorDetails", responseDto.getAuthorDto());
            return "/author/authorCreate";
        }
        model.addAttribute("errormessage",responseDto.getResponse());
        return "author/authorCreate";
    }

}