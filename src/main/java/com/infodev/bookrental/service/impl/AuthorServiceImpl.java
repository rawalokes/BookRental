package com.infodev.bookrental.service.impl;

import com.infodev.bookrental.dto.AuthorDto;
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
    private AuthorRepo authorRepo;

    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public AuthorDto create(AuthorDto authorDto) {
//        Author author = Author.builder()
//                .id(authorDto.getId())
//                .name(authorDto.getName())
//                .email(authorDto.getEmail())
//                .phone(authorDto.getNumber())
//                .build();
        Author author= authorToDto(authorDto);
        author = authorRepo.save(author);
//        return AuthorDto.builder()
//                .id(author.getId())
//                .name(author.getName())
//                .email(author.getEmail())
//                .number(author.getPhone())
//                .build();
        return authorToDto(author);
    }

    @Override
    public List<AuthorDto> showAll() {
        List<Author> authors = authorRepo.findAll();
        return authors.stream().map(reteriveAuthors -> AuthorDto.builder()
                .id(reteriveAuthors.getId())
                .name(reteriveAuthors.getName())
                .email(reteriveAuthors.getEmail())
                .number(reteriveAuthors.getPhone())
                .build()).collect(Collectors.toList());
    }

    @Override
    public AuthorDto findById(Integer id) {
        Optional<Author> author = authorRepo.findById(id);
        if (author.isPresent()) {
            Author reteriveAuthor = author.get();
            return authorToDto(reteriveAuthor);
        }

        return null;
    }

    @Override
    public void deleteById(Integer id) {
        authorRepo.deleteById(id);

    }

    public AuthorDto authorToDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .number(author.getPhone())
                .build();
    }

    public Author authorToDto(AuthorDto authorDto) {
        return Author.builder()
                .id(authorDto.getId())
                .name(authorDto.getName())
                .email(authorDto.getEmail())
                .phone(authorDto.getNumber())
                .build();
    }
}
