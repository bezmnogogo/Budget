package com.budget.dao.repository;

import com.budget.dao.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by home on 12.12.16.
 */
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
