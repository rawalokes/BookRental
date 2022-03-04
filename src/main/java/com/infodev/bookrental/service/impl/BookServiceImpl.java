package com.infodev.bookrental.service.impl;

import com.infodev.bookrental.components.FileComponents;
import com.infodev.bookrental.dto.BookDto;
import com.infodev.bookrental.dto.ResponseDto;
import com.infodev.bookrental.model.Author;
import com.infodev.bookrental.model.Book;
import com.infodev.bookrental.model.Category;
import com.infodev.bookrental.repo.AuthorRepo;
import com.infodev.bookrental.repo.BookRepo;
import com.infodev.bookrental.repo.CategoryRepo;
import com.infodev.bookrental.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

/**
 * @author rawalokes
 * Date:3/2/22
 * Time:2:56 PM
 */
@Service
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;
    private final CategoryRepo categoryRepo;
    private final AuthorRepo authorRepo;

    public BookServiceImpl(BookRepo bookRepo, CategoryRepo categoryRepo, AuthorRepo authorRepo, FileComponents fileComponents) {
        this.bookRepo = bookRepo;
        this.categoryRepo = categoryRepo;
        this.authorRepo = authorRepo;
        this.fileComponents = fileComponents;
    }

    private final FileComponents fileComponents;



    @Override
    public BookDto create(BookDto bookDto) {
    Book book=dtoToBook(bookDto);
    bookRepo.save(book);
    return bookToDto(book);
    }

    @Override
    public List<BookDto> showAll() {
        return null;
    }

    @Override
    public BookDto findById(Integer integer) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    private Book dtoToBook(BookDto bookDto) {
        ResponseDto responseDto = fileComponents.filePath(bookDto.getMultipartFile());
        if (responseDto.isResponseStatus()) {
            return Book.builder()
                    .bookId(bookDto.getBookId())
                    .name(bookDto.getName())
                    .isbn(bookDto.getIsbn())
                    .noOfPages(bookDto.getNoOfPages())
                    .rating(bookDto.getRating())
                    .publishDate(bookDto.getPublishDate())
                    .photoUrl(responseDto.getResponse())
//                    .category(bookDto.getCategoryId())
                    .build();
        } else
            return null;

    }

    private BookDto bookToDto(Book book) {
        return BookDto.builder()
                .bookId(book.getBookId())
                .name(book.getName())
                .isbn(book.getIsbn())
                .noOfPages(book.getNoOfPages())
                .rating(book.getRating())
                .publishDate(book.getPublishDate())
                .photoUrl(book.getPhotoUrl())


                .build();

    }


}
