package com.infodev.bookrental.repo;

import com.infodev.bookrental.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,Integer> {
}
