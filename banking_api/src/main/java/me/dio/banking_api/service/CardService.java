package me.dio.banking_api.service;

import me.dio.banking_api.domain.model.Card;

public interface CardService {
    Card findCardByUser(Long userId);

    Card updateCard(Long userId, Card card);
}
