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

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Record> records;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "category", cascade = CascadeType.ALL)
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
}
