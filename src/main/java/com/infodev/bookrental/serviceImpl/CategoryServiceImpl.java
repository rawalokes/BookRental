package com.infodev.bookrental.serviceImpl;

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
        Category category = toCategory(categoryDto);

        category = categoryRepo.save(category);
        return toCategoryDto(category);
    }

    @Override
    public List<CategoryDto> showAll() {
        List<Category> categories = categoryRepo.findAll();
        return categories.stream().map(category -> CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build()).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Integer id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (category.isPresent()) {
            Category reteriveCategory = category.get();
            return toCategoryDto(reteriveCategory);
        }
        return null;
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepo.deleteById(id);
    }


    /**
     * map category into categoryDTO
     *
     * @param category to be mapped on dto
     * @return categoryDto
     */
    private CategoryDto toCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();

    }

    /**
     *
     *  map DTO into cartegory
     * @param categoryDto to be maped into cagegory
     * @return category
     */
    private Category toCategory(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();
    }
}
