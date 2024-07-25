package me.dio.santander_dev_week_2023.service.impl;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.dio.santander_dev_week_2023.domain.model.Card;
import me.dio.santander_dev_week_2023.domain.model.User;
import me.dio.santander_dev_week_2023.domain.repository.CardRepository;
import me.dio.santander_dev_week_2023.domain.repository.UserRepository;
import me.dio.santander_dev_week_2023.service.CardService;

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

    
    
}
