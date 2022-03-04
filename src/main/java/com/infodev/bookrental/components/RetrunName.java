package com.infodev.bookrental.components;

import com.infodev.bookrental.dto.AuthorDto;
import com.infodev.bookrental.dto.CategoryDto;
import com.infodev.bookrental.model.Author;
import com.infodev.bookrental.model.Category;
import com.infodev.bookrental.repo.AuthorRepo;
import com.infodev.bookrental.repo.CategoryRepo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rawalokes
 * Date:3/2/22
 * Time:9:21 PM
 */
public class RetrunName {
    private final AuthorRepo authorRepo;
    private final CategoryRepo categoryRepo;

    public RetrunName(AuthorRepo authorRepo, CategoryRepo categoryRepo) {
        this.authorRepo = authorRepo;
        this.categoryRepo = categoryRepo;
    }

}
