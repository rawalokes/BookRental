package com.infodev.bookrental.controller;

import com.infodev.bookrental.dto.BookDto;
import com.infodev.bookrental.serviceImpl.AuthorServiceImpl;
import com.infodev.bookrental.serviceImpl.BookServiceImpl;
import com.infodev.bookrental.serviceImpl.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

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

    @GetMapping("/setup")
    public String getAllBook(Model model) {
        model.addAttribute("albooks", bookService.showAll());
        return "book/booksetup";
    }

    @GetMapping("/create")
    public String getCreateBook(Model model) {
        model.addAttribute("book", new BookDto());
        model.addAttribute("categories", categoryService.showAll());
        model.addAttribute("authors", authorService.showAll());
        return "/book/bookcreate";
    }

    @PostMapping("/create")
    public String postCreateBook(@Valid @ModelAttribute("book") BookDto bookDto
            , BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) return "/book/bookcreate";
        bookService.create(bookDto);
        return "redirect:/book/setup";
    }

    @GetMapping("/delete/{id}")
    public String removeBookById(@PathVariable Integer id) {
        bookService.deleteById(id);
        return "redirect:/book/setup";
    }

    @GetMapping("/update/{id}")
    public String updateBookByID(@PathVariable Integer id, Model model) {
        BookDto bookDto = bookService.findById(id);
        model.addAttribute("book", bookDto);
        model.addAttribute("categories", categoryService.showAll());
        model.addAttribute("authors", authorService.showAll());
        return "/book/bookcreate";
    }
}
