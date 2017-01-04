package com.budget.services;

import com.budget.dao.entities.PlannedRecord;
import com.budget.dao.repository.IPlannedRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by home on 12.12.16.
 */
@Service
@Transactional
public class PlannedRecordService implements IPlannedRecordService{

    private final IPlannedRecordRepository plannedRecordRepository;

    @Autowired
    public PlannedRecordService(IPlannedRecordRepository plannedRecordRepository) {
        this.plannedRecordRepository = plannedRecordRepository;
    }

    @Override
    @Transactional
    public void savePlannedRecord(PlannedRecord plannedRecord) {
        plannedRecordRepository.saveAndFlush(plannedRecord);
    }

    @Override
    @Transactional
    public List<PlannedRecord> getPlannedRecordsByUserId(long id) {
        return plannedRecordRepository.getRecordsByUserId(id);
    }

    @Override
    @Transactional
    public PlannedRecord getPlannedRecordById(long id) {
        return plannedRecordRepository.findOne(id);
    }

    @Override
    public void deletePlannedRecordById(long id) {
        plannedRecordRepository.delete(id);
    }
}
