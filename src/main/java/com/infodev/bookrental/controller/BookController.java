package com.infodev.bookrental.controller;

import com.infodev.bookrental.dto.BookDto;
import com.infodev.bookrental.dto.ResponseDto;
import com.infodev.bookrental.serviceImpl.AuthorServiceImpl;
import com.infodev.bookrental.serviceImpl.BookServiceImpl;
import com.infodev.bookrental.serviceImpl.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;




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

    /**
     * returns list of books or all book stored in database
     * @param model
     * @return booksetup.html file
     */
    @GetMapping("/setup")
    public String getAllBook(Model model) {
        model.addAttribute("albooks", bookService.showAll());
        return "book/booksetup";
    }

    /**
     * send BookDto , CategoryDto and authorDto to bookCreate.html file
     *
     * @param model
     * @return bookcreate.html file
     */
    @GetMapping("/create")
    public String getCreateBook(Model model) {
        model.addAttribute("book", new BookDto());
        model.addAttribute("categories", categoryService.showAll());
        model.addAttribute("authors", authorService.showAll());
        return "/book/bookcreate";
    }

    /**
     * @param bookDto details to be stored in database as book
     * @param bindingResult
     * @param model
     * @return redirect to /book/setup which shows book details
     */
    @PostMapping("/create")
    public String postCreateBook(@Valid @ModelAttribute("book") BookDto bookDto
            , BindingResult bindingResult, Model model)  {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.showAll());
            model.addAttribute("authors", authorService.showAll());
            return "/book/bookcreate";
        }
        ResponseDto responseDto = bookService.create(bookDto);
        if (responseDto.isResponseStatus()) {
            return "redirect:/book/setup";
        }
        model.addAttribute("categories", categoryService.showAll());
        model.addAttribute("authors", authorService.showAll());
        model.addAttribute("errormessage", responseDto.getResponse());
        return "/book/bookcreate";
    }

    /**
     * delete book using id
     *
     * @param id    book id
     * @param model
     * @return book/setup page
     */
    @GetMapping("/delete/{id}")
    public String removeBookById(@PathVariable Integer id, Model model) {
        ResponseDto responseDto = bookService.deleteById(id);
        if (responseDto.isResponseStatus()) {
            return "redirect:/book/setup";
        }
        model.addAttribute("errormessage", responseDto.getResponse());
        return "book/booksetup";

    }

    /**
     * update book using book id
     *
     * @param id    book id
     * @param model
     * @return
     */
    @GetMapping("/update/{id}")
    public String updateBookByID(@PathVariable Integer id, Model model) {
        ResponseDto responseDto = bookService.findById(id);
        if (responseDto.isResponseStatus()) {
            model.addAttribute("book", responseDto.getBookDto());
            model.addAttribute("categories", categoryService.showAll());
            model.addAttribute("authors", authorService.showAll());
            return "/book/bookcreate";
        }
        model.addAttribute("errormessage", responseDto.getResponse());
        return "book/booksetup";
    }

    /**
     * show  particular book's details based on book' id
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/book-details/{id}")
    public String showBookDetails(@PathVariable Integer id, Model model) {
        ResponseDto responseDto = bookService.findById(id);
        if (responseDto.isResponseStatus()) {
            model.addAttribute("bookDetails", responseDto.getBookDto());
            return "/book/viewBook";
        }
        model.addAttribute("errormessage", responseDto.getResponse());
        return "book/booksetup";

    }

}
