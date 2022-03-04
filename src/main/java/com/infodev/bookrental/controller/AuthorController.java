package com.infodev.bookrental.controller;

import com.infodev.bookrental.dto.AuthorDto;
import com.infodev.bookrental.service.impl.AuthorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getAddAuthor(Model model) {
        model.addAttribute("authorDetails", new AuthorDto());
        return "author/authorCreate";
    }

    @PostMapping("/create")
    public String postAddAuthor(@ModelAttribute("authorDetails") AuthorDto authorDto) {

        authorService.create(authorDto);
        return "redirect:/author/getall";

    }

    @GetMapping("/delete/{id}")
    public String removeAuthorByI(@PathVariable Integer id) {
        authorService.deleteById(id);
        return "redirect:/author/getall";
    }

    @GetMapping("/update/{id}")
    public String updateAuthorByI(@PathVariable Integer id, Model model) {
        AuthorDto authorDto = authorService.findById(id);
        model.addAttribute("authorDetails",authorDto);

        return "/author/authorCreate";
    }

}