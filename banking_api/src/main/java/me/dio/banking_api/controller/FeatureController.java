package me.dio.banking_api.controller;

import java.net.URI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.banking_api.domain.model.Feature;
import me.dio.banking_api.domain.model.User;
import me.dio.banking_api.service.FeatureService;

@RestController
@RequestMapping("/features")
@Tag(name = "Feature Management", description = "Operations pertaining to features in the Banking System")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @GetMapping
    @Operation(summary = "Get all features")
    public ResponseEntity<List<Feature>> findAllFeatures(){
        return ResponseEntity.ok(featureService.findAllFeatures());
    }

    @GetMapping("/{featureId}")
    @Operation(summary = "Get feature details by feature ID")
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
    @Operation(summary = "Get features by user ID")
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
    @Operation(summary = "Add a feature to a user")
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
