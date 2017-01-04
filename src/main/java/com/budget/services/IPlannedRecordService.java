package com.budget.services;

import com.budget.dao.entities.PlannedRecord;

import java.util.List;

/**
 * Created by home on 12.12.16.
 */
public interface IPlannedRecordService {

    void savePlannedRecord(PlannedRecord plannedRecord);

    List<PlannedRecord> getPlannedRecordsByUserId(long id);

}
