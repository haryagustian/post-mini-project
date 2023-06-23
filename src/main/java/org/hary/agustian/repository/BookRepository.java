package org.hary.agustian.repository;

import org.hary.agustian.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
