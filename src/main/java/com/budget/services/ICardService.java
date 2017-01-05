package com.budget.services;

import com.budget.dao.entities.Card;
import com.budget.dao.entities.User;

import java.util.List;

/**
 * Created by home on 12.12.16.
 */
public interface ICardService {

    //получаем список карт пользователя
    List<Card> getCardsByUserId(long user);

    Card getCardByCardNumber(String number);

    void saveCard(Card card);
}
