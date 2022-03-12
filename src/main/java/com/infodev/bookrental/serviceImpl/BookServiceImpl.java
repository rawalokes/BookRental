package com.infodev.bookrental.serviceImpl;

import com.infodev.bookrental.components.FileComponents;
import com.infodev.bookrental.dto.BookDto;
import com.infodev.bookrental.dto.ResponseDto;
import com.infodev.bookrental.model.Book;
import com.infodev.bookrental.repo.AuthorRepo;
import com.infodev.bookrental.repo.BookRepo;
import com.infodev.bookrental.repo.CategoryRepo;
import com.infodev.bookrental.service.BookService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
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
    public ResponseDto create(BookDto bookDto) throws IOException {
        try {
            Book book = dtoToBook(bookDto);
            bookRepo.save(book);
            return ResponseDto.builder()
                    .responseStatus(true)
                    .response("Book added successfully")
                    .build();

        } catch (Exception e) {
            return ResponseDto.builder()
                    .responseStatus(false)
                    .response("Book cannot be created")
                    .build();
        }

    }

    @Override
    public List<BookDto> showAll() {
        List<Book> books = bookRepo.findAll();
        return books.stream().map(book -> bookToDto(book)).collect(Collectors.toList());
    }

    @Override
    public ResponseDto findById(Integer integer) {

            Optional<Book> book = bookRepo.findById(integer);

            if (book.isPresent()) {
                Book retBook = book.get();
                return ResponseDto.builder()
                        .responseStatus(true)
                        .bookDto(bookToDto(retBook))
                        .build();
            }
            else {
                return errorStatus("book Not Found");
            }

    }

    @Override
    public ResponseDto deleteById(Integer integer) {

        try {
            bookRepo.deleteById(integer);
            return ResponseDto.builder()
                    .response("Book deleted successfully")
                    .responseStatus(true)
                    .build();
        } catch (Exception e) {
            return errorStatus("Book not found");

        }
    }

    private Book dtoToBook(BookDto bookDto) throws IOException {

        ResponseDto responseDto = fileComponents.storeFile(bookDto.getMultipartFile());
        if (responseDto.isResponseStatus()) {
            return Book.builder()
                    .bookId(bookDto.getBookId())
                    .name(bookDto.getName())
                    .isbn(bookDto.getIsbn())
                    .noOfPages(bookDto.getNoOfPages())
                    .rating(bookDto.getRating())
                    .stockCount(bookDto.getStockCount())
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
                .stockCount(book.getStockCount())
                .noOfPages(book.getNoOfPages())
                .rating(book.getRating())
                .publishDate(book.getPublishDate())
                .pathUrl(book.getPhotoUrl())
                .categoryId(book.getCategory().getId())
                .AuthorId(book.getAuthors().stream().map(
                        x -> x.getId()).collect(Collectors.toList()))
                .build();

    }


    /**
     * @param message or error message
     * @return responseDto object with response status false and response message
     */
    private ResponseDto errorStatus(String message) {
        return ResponseDto.builder()
                .responseStatus(false)
                .response(message)
                .build();
    }

}
