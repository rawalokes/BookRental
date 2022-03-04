package com.infodev.bookrental.controller;

import com.infodev.bookrental.dto.AuthorDto;
import com.infodev.bookrental.dto.BookDto;
import com.infodev.bookrental.dto.CategoryDto;
import com.infodev.bookrental.service.impl.AuthorServiceImpl;
import com.infodev.bookrental.service.impl.BookServiceImpl;
import com.infodev.bookrental.service.impl.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author rawalokes
 * Date:2/26/22
 * Time:11:49 AM
 */
@Controller
@RequestMapping("/book")
public class BookController {
    private final BookServiceImpl bookService;
    private final AuthorServiceImpl authorService;
    private final CategoryServiceImpl categoryService;

    public BookController(BookServiceImpl bookService, AuthorServiceImpl authorService, CategoryServiceImpl categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @GetMapping("/create")
    public String getCreateBook(Model model){
        model.addAttribute("book",new BookDto());
        model.addAttribute("categories",categoryService.showAll());
        model.addAttribute("authors",authorService.showAll());
        return "/book/bookcreate";
    }
    @PostMapping("/create")
    public String postCreateBook(@ModelAttribute("book") BookDto bookDto){
       bookService.create(bookDto);
        return "/book/bookcreate";
    }
}
