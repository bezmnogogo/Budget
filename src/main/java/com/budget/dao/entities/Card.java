package com.budget.dao.entities;

import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by home on 12.12.16.
 */
@Entity
@Table(name = "cards")
public class Card extends GenericEntity{

    public Card() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "cash")
    private float cash;

    public User getUser() {
        return user;
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

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "card", cascade = CascadeType.ALL)
    private Set<Record> records;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "card", cascade = CascadeType.ALL)
    private Set<PlannedRecord> plannedRecords;


    public void setUser(User user) {
        this.user = user;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }
}
