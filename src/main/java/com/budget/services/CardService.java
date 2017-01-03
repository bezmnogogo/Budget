package com.budget.services;

import com.budget.dao.entities.Card;
import com.budget.dao.repository.ICardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by home on 12.12.16.
 */
@Service
@Transactional
public class CardService implements ICardService{

    private final ICardRepository cardRepository;

    @Autowired
    public CardService(ICardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    @Transactional
    public List<Card> getCardsByUserId(long userId) {
        return null;
    }

    @Override
    @Transactional
    public Card getCardByCardNumber(String number) {
        return cardRepository.findByCardNumber(number);
    }
}
