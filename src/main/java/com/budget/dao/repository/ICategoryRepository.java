package com.budget.dao.repository;

import com.budget.dao.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by home on 12.12.16.
 */
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT DISTINCT c FROM Category c WHERE c.type = :type")
    Category findByType(@Param("type") String type);
}
