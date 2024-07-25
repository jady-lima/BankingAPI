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

    @Override
    public List<News> addNewToUser(Long userId, News news) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);

        if (userRepository.existsByIdAndNews_Id(userId, news.getId())) {
            throw new IllegalArgumentException("This Feature already exist for this user");
        } else {
            user.getNews().add(news);
            userRepository.save(user);
            newsRepository.save(news);

            return user.getNews();
        }

    }
    
    
}
