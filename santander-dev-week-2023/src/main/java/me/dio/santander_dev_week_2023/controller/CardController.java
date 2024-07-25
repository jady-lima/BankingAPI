package me.dio.santander_dev_week_2023.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.dio.santander_dev_week_2023.domain.model.Card;
import me.dio.santander_dev_week_2023.domain.model.User;
import me.dio.santander_dev_week_2023.service.CardService;

@RestController
@RequestMapping("/users")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/{userId}/card")
    public ResponseEntity<Card> findCardByUser(@PathVariable Long userId){
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                return ResponseEntity.ok(cardService.findCardByUser(userId));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
    }

}
