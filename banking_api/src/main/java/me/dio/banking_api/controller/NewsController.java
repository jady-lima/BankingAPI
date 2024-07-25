package me.dio.banking_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.banking_api.domain.model.News;
import me.dio.banking_api.service.NewsService;

@RestController
@RequestMapping("/users")
@Tag(name = "News Management", description = "Operations pertaining to News in the Banking System")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/{userId}/news")
    @Operation(summary = "Get news by user ID")
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

    @PostMapping("/{userId}/news")
    @Operation(summary = "Add a news to a user")
    public ResponseEntity<List<News>> addNewsToUser(@PathVariable Long userId, @RequestBody News news){
        if (userId == null || news == null) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                return ResponseEntity.ok(newsService.addNewToUser(userId, news));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
    }
}
