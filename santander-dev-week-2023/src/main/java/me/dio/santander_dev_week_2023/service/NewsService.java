package me.dio.santander_dev_week_2023.service;

import java.util.List;

import me.dio.santander_dev_week_2023.domain.model.News;

public interface NewsService {
    List<News> getNewsByUserId(Long userId);

    List<News> addNewToUser(Long userId, News news);
}
