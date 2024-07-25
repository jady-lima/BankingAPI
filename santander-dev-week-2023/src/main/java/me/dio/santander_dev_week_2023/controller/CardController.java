package me.dio.santander_dev_week_2023.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.santander_dev_week_2023.domain.model.Card;
import me.dio.santander_dev_week_2023.service.CardService;

@RestController
@RequestMapping("/users")
@Tag(name = "Card Management", description = "Operations pertaining to user cards in the Banking System")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/{userId}/card")
    @Operation(summary = "Get card details by user ID")
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

    @PutMapping("/{userId}/card")
    @Operation(summary = "Update card details by user ID")
    public ResponseEntity<Card> updateCard(@PathVariable Long userId, @RequestBody Card card){
        try {
            return ResponseEntity.ok(cardService.updateCard(userId, card));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
