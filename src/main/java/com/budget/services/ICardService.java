package com.budget.services;

import com.budget.dao.entities.Card;
import java.util.List;

/**
 * Created by home on 12.12.16.
 */
public interface ICardService {

    //получаем список карт пользователя
    List<Card> getCardsByUserId(long userId);



}
