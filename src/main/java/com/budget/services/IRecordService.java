package com.budget.services;

import com.budget.dao.entities.Record;

import java.util.List;

/**
 * Created by home on 12.12.16.
 */
public interface IRecordService {
    void addRecord(Record record);

    List<Record> getRecordsByUserId(long id);

    Record getRecordById(long id);
}
