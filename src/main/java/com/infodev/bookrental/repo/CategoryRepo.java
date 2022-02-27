package com.infodev.bookrental.repo;

import com.infodev.bookrental.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
