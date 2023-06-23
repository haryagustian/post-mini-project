package org.hary.agustian.repository;

import org.hary.agustian.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepotisory extends JpaRepository<Category, Integer> {
    Category findByName(String name);
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
