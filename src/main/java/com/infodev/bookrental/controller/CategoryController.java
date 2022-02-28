package com.infodev.bookrental.controller;

import com.infodev.bookrental.dto.CategoryDto;
import com.infodev.bookrental.service.impl.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author rawalokes
 * Date:2/25/22
 * Time:9:06 PM
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/setup")
    public String getAllCategory(Model model) {
        model.addAttribute("categoryList", categoryService.showAll());
        return "category/categorysetup";
    }

    @GetMapping("/create")
    public String getAddCategory(Model model) {
        model.addAttribute("categories", new CategoryDto());
        return "/category/categorycreate";
    }

    @PostMapping("/create")
    public String postAddCategory(@ModelAttribute("categories") CategoryDto categoryDto) {
        categoryService.create(categoryDto);
        return "redirect:/category/setup";
    }

    @GetMapping("/delete/{id}")
    public String removeCategory(@PathVariable("id") Integer id) {
        categoryService.deleteById(id);
        return "category/categorysetup";
    }
    @GetMapping("/update/{id}")
    public String updateCategory(@PathVariable Integer id,Model model){
        CategoryDto categoryDto=categoryService.findById(id);
        model.addAttribute("categories",categoryDto);
        return "/category/categorycreate";
    }


}
