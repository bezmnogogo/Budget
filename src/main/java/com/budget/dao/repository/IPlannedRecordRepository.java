package com.budget.dao.repository;

import com.budget.dao.entities.PlannedRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by home on 12.12.16.
 */
public interface IPlannedRecordRepository extends JpaRepository<PlannedRecord, Long> {

    @Query("SELECT DISTINCT r FROM PlannedRecord r where r.user.id = :id")
    List<PlannedRecord> getRecordsByUserId(@Param("id") long id);

}
