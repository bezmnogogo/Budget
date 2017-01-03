package com.budget.dao.repository;

import com.budget.dao.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by home on 12.12.16.
 */
public interface ICardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT DISTINCT c FROM Card c WHERE c.cardNumber = :cardNumber")
    Card findByCardNumber(@Param("cardNumber") String cardNumber);


}
