package org.hary.agustian.repository;

import org.hary.agustian.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepotisory extends JpaRepository<Category, Integer> {
    Category findByName(String name);
}
