package org.hary.agustian.repository;

import org.hary.agustian.model.Book;
import org.hary.agustian.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);
}
