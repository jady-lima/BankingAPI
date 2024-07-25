package me.dio.santander_dev_week_2023.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dio.santander_dev_week_2023.domain.model.News;
import me.dio.santander_dev_week_2023.domain.model.User;
import me.dio.santander_dev_week_2023.domain.repository.NewsRepository;
import me.dio.santander_dev_week_2023.domain.repository.UserRepository;
import me.dio.santander_dev_week_2023.service.NewsService;

@Service
public class NewsServiceImp implements NewsService{

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserRepository userRepository; 

    @Override
    public List<News> getNewsByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        return new ArrayList<>(user.getNews());
    }
    
}
