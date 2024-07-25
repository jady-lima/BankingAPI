package me.dio.banking_api.service.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.dio.banking_api.domain.model.Card;
import me.dio.banking_api.domain.model.User;
import me.dio.banking_api.domain.repository.CardRepository;
import me.dio.banking_api.domain.repository.UserRepository;
import me.dio.banking_api.service.CardService;

@Service
public class CardServiceImp implements CardService{

    private final CardRepository cardRepository;
    private final UserRepository userRepository;  

    public CardServiceImp(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Card findCardByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        return user.getCard();
    }

    @Override
    public Card updateCard(Long userId, Card card) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);

        user.setCard(card);
        userRepository.save(user);

        return cardRepository.save(user.getCard());
    }
    
}
