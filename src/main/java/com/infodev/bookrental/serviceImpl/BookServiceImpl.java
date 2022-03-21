package com.infodev.bookrental.serviceImpl;

import com.infodev.bookrental.components.FileComponents;
import com.infodev.bookrental.dto.AuthorDto;
import com.infodev.bookrental.dto.BookDto;
import com.infodev.bookrental.dto.ResponseDto;
import com.infodev.bookrental.model.Author;
import com.infodev.bookrental.model.Book;
import com.infodev.bookrental.repo.AuthorRepo;
import com.infodev.bookrental.repo.BookRepo;
import com.infodev.bookrental.repo.CategoryRepo;
import com.infodev.bookrental.service.BookService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
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

    /**
     *store book in database
     * @param bookDto
     * @return
     */
    @Override
    public ResponseDto create(BookDto bookDto) {
            try {
                //convert bookDto to book with the help of Converter function (convertBookDtoToBook)
                Book book = convertBookDtoToBook(bookDto);
                if (book==null){
                    return errorStatus("please select an jpeg , png  or jpg file");
                }

                //store book into database
                bookRepo.save(book);

                return ResponseDto.builder()
                        .responseStatus(true)
                        .response("Book added successfully")
                        .build();

        } catch (Exception e) {
                //check if exception error message contains String  'isbn'
            if (e.getMessage().contains("isbn")){
                return ResponseDto.builder()
                        .responseStatus(false)
                        .response("Isbn already in use.")
                        .build();
            }
            return ResponseDto.builder()
                    .responseStatus(false)
                    .response(e.getMessage())
                    .build();
        }

    }

    /**
     * convert list of book to list of bookDto and
     * @return list of bookDto
     */
    @Override
    public List<BookDto> showAll() {
        // fetch list of book stored in database
        List<Book> books = bookRepo.findAll();
        books.toString();
        return books.stream().map(book -> convertBookToBookDto(book)).collect(Collectors.toList());
    }

    /**
     * find book based on book id
     * @param integer book id
     * @return error or success message
     */
    @Override
    public ResponseDto findById(Integer integer) {
        //fetch particular book based on Id
        Optional<Book> book = bookRepo.findById(integer);

        //check if  fetched book is null
        if (book.isPresent()) {

            Book retBook = book.get();
            return ResponseDto.builder()
                    .responseStatus(true)
                    .bookDto(convertBookToBookDto(retBook))
                    .build();
        } else {
            return errorStatus("book Not Found");
        }

    }

    /**
     * delete book using id
     * @param integer book id
     * @return error or success message
     */
    @Override
    public ResponseDto deleteById(Integer integer) {

        try {
            //delete particular book based on id
            bookRepo.deleteById(integer);
            return ResponseDto.builder()
                    .response("Book deleted successfully")
                    .responseStatus(true)
                    .build();
        } catch (Exception e) {
            return errorStatus("Book not found");

        }
    }

    /**
     * convert BookDto To Book
     * @param bookDto
     * @return book
     */
    private Book convertBookDtoToBook(BookDto bookDto) throws IOException {

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

    /**
     * convert Book To BookDto
     * @param book
     * @return bookDto
     */
    private BookDto convertBookToBookDto(Book book) {

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
                .categoryDto(categoryRepo.findById(book.getCategory().getId()).get())
                //convert AuthorList To AuthorDtoList
                .authorDtoList(convertAuthorListToAuthorDtoList(book.getAuthors()))
                .pathUrl(fileComponents.returnFileAsBase64(book.getPhotoUrl()))
                .build();

    }

    private BookDto setAuthors(BookDto bookDto) {
        bookDto.setAuthors(authorRepo.findAllById(bookDto.getAuthorId()));
        return bookDto;
    }

    /**
     * convert AuthorList To AuthorDtoList
     * @param authors
     * @return authorDtoList
     */
    public List<AuthorDto> convertAuthorListToAuthorDtoList(List<Author> authors) {
        List<AuthorDto> authorDtoList = new ArrayList<AuthorDto>();
        for (Author author : authors) {
            authorDtoList.add(
                    AuthorDto.builder()
                            .id(author.getId())
                            .name(author.getName())
                            .email(author.getEmail())
                            .mNumber(author.getPhone())
                            .build());
        }
        return authorDtoList;
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
