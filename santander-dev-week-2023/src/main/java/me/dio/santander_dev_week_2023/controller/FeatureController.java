package me.dio.santander_dev_week_2023.controller;

import java.net.URI;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import me.dio.santander_dev_week_2023.domain.model.Feature;
import me.dio.santander_dev_week_2023.domain.model.User;
import me.dio.santander_dev_week_2023.service.FeatureService;

@RestController
@RequestMapping("/features")
public class FeatureController {
    private final FeatureService featureService;

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @GetMapping
    public ResponseEntity<List<Feature>> findAllFeatures(){
        return ResponseEntity.ok(featureService.findAllFeatures());
    }

    @GetMapping("/{featureId}")
    public ResponseEntity<Feature> findFeatureById(@PathVariable Long featureId){
        if (featureId == null) {
            return ResponseEntity.badRequest().build();
        }
    
        try {
            return ResponseEntity.ok(featureService.findFeatureById(featureId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Feature>> findFeatureByUserId(@PathVariable Long userId){
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }
    
        try {
            List<Feature> features = featureService.findFeaturesByUser(userId);
    
            if (features.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
    
            return ResponseEntity.ok(features);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<User> addFeatureToUser(@PathVariable Long userId, @RequestBody Feature feature) {
        if (userId == null || feature == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            User featureCreated = featureService.addFeatureToUser(userId, feature);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{featureId}")
                            .buildAndExpand(featureCreated.getId())
                            .toUri();

            return ResponseEntity.created(location).body(featureCreated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
