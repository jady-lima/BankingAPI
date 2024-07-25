package me.dio.santander_dev_week_2023.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dio.santander_dev_week_2023.domain.model.News;
import me.dio.santander_dev_week_2023.service.NewsService;

@RestController
@RequestMapping("/users")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/{userId}/news")
    public ResponseEntity<List<News>> findNewsByUserId(@PathVariable Long userId){
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                return ResponseEntity.ok(newsService.getNewsByUserId(userId));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }
}
