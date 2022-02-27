package com.infodev.bookrental.service.impl;

import com.infodev.bookrental.dto.CategoryDto;
import com.infodev.bookrental.model.Category;
import com.infodev.bookrental.repo.CategoryRepo;
import com.infodev.bookrental.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author rawalokes
 * Date:2/21/22
 * Time:11:21 PM
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;

    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }


    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .description(categoryDto
                        .getDiscription()).build();
        category = categoryRepo.save(category);

        return CategoryDto.builder().id(category.getId()).name(category.getName()).discription(category.getDescription()).build();
    }

    @Override
    public List<CategoryDto> showAll() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream().map(category -> CategoryDto.builder().id(category.getId()).name(category.getName()).discription(category.getDescription()).build()).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Integer id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isPresent()) {
            Category reteriveCategory = category.get();
            return CategoryDto.builder().id(reteriveCategory.getId()).name(reteriveCategory.getName()).discription(reteriveCategory.getDescription()).build();
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
