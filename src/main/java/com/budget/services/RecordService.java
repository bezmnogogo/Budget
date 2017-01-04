package com.budget.services;

import com.budget.dao.entities.Record;
import com.budget.dao.repository.IRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by home on 12.12.16.
 */
@Service
@Transactional
public class RecordService implements IRecordService{

    private final IRecordRepository recordRepository;

    @Autowired
    public RecordService(IRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    @Transactional
    public void addRecord(Record record) {
        recordRepository.saveAndFlush(record);
    }

    @Override
    @Transactional
    public Record getRecordById(long id) {
        return recordRepository.findOne(id);
    }

    @Override
    @Transactional
    public List<Record> getRecordsByUserId(long id) {
        return recordRepository.getRecordsByUserId(id);
    }

    @Override
    public void deleteRecordById(long id) {
        recordRepository.delete(id);
    }
}
