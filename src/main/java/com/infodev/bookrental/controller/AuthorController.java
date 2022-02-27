package com.infodev.bookrental.controller;

import com.infodev.bookrental.dto.AuthorDto;
import com.infodev.bookrental.service.impl.AuthorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/getall")
    public String getAuthorDetails(Model model) {
        model.addAttribute("authorList", authorService.showAll());
        return "author/authorSetup";
    }

    @GetMapping("/create")
    public String addAuthor(Model model) {
        model.addAttribute("authorDetails", new AuthorDto());
        return "author/authorCreate";
    }

    @PostMapping("/create")
    public String addAuthor( @ModelAttribute("authorDetails") AuthorDto authorDto) {
//
        authorService.create(authorDto);
        return "redirect:/author/getall";

    }
}