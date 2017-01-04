package com.budget.dao.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by home on 12.12.16.
 */
@Entity
@Table(name = "categories")
public class Category extends GenericEntity {

    public Category() {
    }

    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "category")
    private Set<Record> records;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "category")
    private Set<PlannedRecord> plannedRecords;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }

    public Set<PlannedRecord> getPlannedRecords() {
        return plannedRecords;
    }

    public void setPlannedRecords(Set<PlannedRecord> plannedRecords) {
        this.plannedRecords = plannedRecords;
    }

    public void addRecord(Record record){
        this.records.add(record);
    }

    public void addPlannedRecords(PlannedRecord plannedRecord){
        this.plannedRecords.add(plannedRecord);
    }

    public boolean deleteRecordById(long id){
        boolean complete = false;
        for (Record record : records){
            if(record.getId() == id){
                return records.remove(record);
            }
        }
        return complete;
    }

    public void updateRecord(Record record){
        for(Record record1 : records){
            if(record1.getId() == record.getId()){
                records.remove(record1);
                records.add(record);
                return;
            }
        }
    }
}
