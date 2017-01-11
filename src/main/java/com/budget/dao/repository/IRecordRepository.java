package com.budget.dao.repository;

import com.budget.dao.entities.Category;
import com.budget.dao.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by home on 12.12.16.
 */
public interface IRecordRepository extends JpaRepository<Record, Long> {
    @Query("SELECT DISTINCT r FROM Record r where r.user.id = :id")
    List<Record> getRecordsByUserId(@Param("id") long id);


    @Modifying
    @Query("update Record r set r.category.id = :categoryId where r.id = :id")
    void updateRecordCategory(@Param("id") long id, @Param("categoryId") long category);
}
