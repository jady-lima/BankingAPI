package me.dio.banking_api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dio.banking_api.domain.model.News;
import me.dio.banking_api.domain.model.User;
import me.dio.banking_api.domain.repository.NewsRepository;
import me.dio.banking_api.domain.repository.UserRepository;
import me.dio.banking_api.service.NewsService;

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
