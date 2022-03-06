package com.infodev.bookrental.serviceImpl;

import com.infodev.bookrental.components.FileComponents;
import com.infodev.bookrental.dto.BookDto;
import com.infodev.bookrental.dto.CategoryDto;
import com.infodev.bookrental.dto.ResponseDto;
import com.infodev.bookrental.model.Book;
import com.infodev.bookrental.model.Category;
import com.infodev.bookrental.repo.AuthorRepo;
import com.infodev.bookrental.repo.BookRepo;
import com.infodev.bookrental.repo.CategoryRepo;
import com.infodev.bookrental.service.BookService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
    private final FileComponents fileComponents;

    public BookServiceImpl(BookRepo bookRepo, CategoryRepo categoryRepo, AuthorRepo authorRepo, FileComponents fileComponents) {
        this.bookRepo = bookRepo;
        this.categoryRepo = categoryRepo;
        this.authorRepo = authorRepo;
        this.fileComponents = fileComponents;
    }

    @Override
    public BookDto create(BookDto bookDto) throws IOException {
        Book book = dtoToBook(bookDto);
        bookRepo.save(book);
        return bookToDto(book);
    }

    @Override
    public List<BookDto> showAll() {
//        List<Category> categories = categoryRepo.findAll();
//        return categories.stream().map(category -> CategoryDto.builder().id(category.getId()).name(category.getName())
//                .discription(category.getDescription()).build()).collect(Collectors.toList());
        List<Book> books= bookRepo.findAll();
        return books.stream().map(book -> bookToDto(book)).collect(Collectors.toList());
    }

    @Override
    public BookDto findById(Integer integer) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {


    }

    private Book dtoToBook(BookDto bookDto) throws IOException {
//        ResponseDto responseDto = fileComponents.filePath(bookDto.getMultipartFile());
        ResponseDto responseDto=fileComponents.storeFile(bookDto.getMultipartFile());
        if (responseDto.isResponseStatus()) {
            return Book.builder()
                    .bookId(bookDto.getBookId())
                    .name(bookDto.getName())
                    .isbn(bookDto.getIsbn())
                    .noOfPages(bookDto.getNoOfPages())
                    .rating(bookDto.getRating())
                    .publishDate(bookDto.getPublishDate())
                    .photoUrl(responseDto.getResponse())
                    .category(categoryRepo.findById(bookDto.getCategoryId()).get())
                    .authors(authorRepo.findAllById(bookDto.getAuthorId()))
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
                .pathUrl(book.getPhotoUrl())
                .categoryId(book.getCategory().getId())
                .AuthorId(book.getAuthors().stream().map(
                        x -> x.getId()).collect(Collectors.toList()))
                .build();

    }


}
