package me.dio.banking_api.service;

import java.util.List;

import me.dio.banking_api.domain.model.News;

public interface NewsService {
    List<News> getNewsByUserId(Long userId);

    List<News> addNewToUser(Long userId, News news);
}
