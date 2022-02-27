package com.infodev.bookrental.repo;

import com.infodev.bookrental.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rawalokes
 * Date:2/21/22
 * Time:11:50 PM
 */
public interface AuthorRepo extends JpaRepository<Author,Integer> {
}
