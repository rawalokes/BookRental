package com.infodev.bookrental.serviceImpl;

import com.infodev.bookrental.components.SendEmailComponents;
import com.infodev.bookrental.dto.AuthorDto;
import com.infodev.bookrental.dto.ResponseDto;
import com.infodev.bookrental.model.Author;
import com.infodev.bookrental.repo.AuthorRepo;
import com.infodev.bookrental.service.AuthorService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author rawalokes
 * Date:2/21/22
 * Time:11:44 PM
 */
@Controller
public class AuthorServiceImpl implements AuthorService {
    private final SendEmailComponents sendEmailComponents;
    private final AuthorRepo authorRepo;


    public AuthorServiceImpl(SendEmailComponents sendEmailComponents, AuthorRepo authorRepo) {
        this.sendEmailComponents = sendEmailComponents;
        this.authorRepo = authorRepo;

    }



    @Override
    public ResponseDto create(AuthorDto authorDto) {
        try {
            //convert dto to author
            Author author = dtoToAuthor(authorDto);
//            save author
            authorRepo.save(author);
            //send email to register author
            sendEmailComponents.sendEmail(authorDto.getEmail(),"Author" ,authorDto.getName(),false);
            return ResponseDto.builder()
                    .responseStatus(true)
                    .response("Author created successfully")
                    .build();
        } catch (Exception e) {

            if (e   .getMessage().contains("author_mobile")) {
                return ResponseDto.builder()
                        .responseStatus(false)
                        .response("Mobile number already exists").build();
            } else {
                return ResponseDto.builder()
                        .responseStatus(false)
                        .response("Email address exists").build();
            }
        }

    }


    @Override
    public List<AuthorDto> showAll() {
        List<Author> authors = authorRepo.findAll();
        //return list of author dto after converting author  into dto
        return authors.stream().map(reteriveAuthors -> AuthorDto.builder().id(reteriveAuthors.getId()).name(reteriveAuthors.getName()).email(reteriveAuthors.getEmail()).mNumber(reteriveAuthors.getPhone()).build()).collect(Collectors.toList());
    }

    @Override
    public ResponseDto findById(Integer id) {
        Optional<Author> author = authorRepo.findById(id);
        if (author.isPresent()) {
            Author retrieveAuthor = author.get();
            return ResponseDto.builder()
                    .responseStatus(true)
                    //convert author into dto
                    .authorDto(dtoToAuthor(retrieveAuthor))
                    .build();
        } else {
            return errorStatus("Author not found");
        }
    }


    @Override
    public ResponseDto deleteById(Integer id) {
        try {
            authorRepo.deleteById(id);
            return ResponseDto.builder()
                    .responseStatus(true)
                    .response("Author deleted successfully")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return errorStatus("Author not found");
        }
    }

    /**
     * map author into authorDTO
     *
     * @param author
     * @return authorDto
     */
    private AuthorDto dtoToAuthor(Author author) {
        return AuthorDto.builder().id(author.getId()).name(author.getName()).email(author.getEmail()).mNumber(author.getPhone()).build();
    }

    /**
     * map authorDto into author
     * @param authorDto
     * @return author
     */
    private Author dtoToAuthor(AuthorDto authorDto) {
        return Author.builder().id(authorDto.getId())
                .name(authorDto.getName()).email(authorDto.getEmail())
                .phone(authorDto.getMNumber()).build();
    }

    /**
     * send error message with status false
     *
     * @param message error message
     * @return responseDto
     */
    private ResponseDto errorStatus(String message) {
        return ResponseDto.builder()
                .responseStatus(false)
                .response(message)
                .build();
    }
}