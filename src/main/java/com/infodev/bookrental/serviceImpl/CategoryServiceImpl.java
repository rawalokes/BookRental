package com.infodev.bookrental.serviceImpl;

import com.infodev.bookrental.dto.CategoryDto;
import com.infodev.bookrental.dto.ResponseDto;
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
    public ResponseDto create(CategoryDto categoryDto) {
        try {
            Category category = toCategory(categoryDto);
            categoryRepo.save(category);
            return ResponseDto.builder()
                    .responseStatus(true)
                    .build();
        } catch (Exception e) {
            if (e.getMessage().contains("name")) {
                return ResponseDto.builder()
                        .responseStatus(false)
                        .response("category already exits")
                        .build();
            }
        }
        return ResponseDto.builder()
                .responseStatus(false)
                .build();
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
    public ResponseDto findById(Integer id) {
        try {
            Optional<Category> category = categoryRepo.findById(id);
            if (category.isPresent()) {
                Category retrieveCategory = category.get();
                return ResponseDto.builder()
                        .responseStatus(true)
                        .categoryDto(toCategoryDto(retrieveCategory))
                        .build();
            }
        } catch (Exception e) {
            return ResponseDto.builder()
                    .responseStatus(false)
                    .response("category not found")
                    .build();
        }
        return ResponseDto.builder()
                .responseStatus(false)

                .build();
    }

    @Override
    public ResponseDto deleteById(Integer id) {
        try {
            categoryRepo.deleteById(id);
        } catch (Exception e) {
            return ResponseDto.builder()
                    .responseStatus(false)
                    .response("Not found")
                    .build();
        }
        return ResponseDto.builder()
                .responseStatus(false)
                .build();

    }


    /**
     * map category into categoryDTO
     *
     * @param category to be mapped on dto
     * @return categoryDto
     */
    protected CategoryDto toCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();

    }

    /**
     * map DTO into cartegory
     *
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
