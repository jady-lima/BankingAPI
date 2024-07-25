package me.dio.santander_dev_week_2023.service;

import me.dio.santander_dev_week_2023.domain.model.Card;

public interface CardService {
    Card findCardByUser(Long userId);

    Card updateCard(Long userId, Card card);
}
