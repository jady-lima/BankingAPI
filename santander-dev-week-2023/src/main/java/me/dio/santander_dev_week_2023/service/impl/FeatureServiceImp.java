package me.dio.santander_dev_week_2023.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.dio.santander_dev_week_2023.domain.model.Feature;
import me.dio.santander_dev_week_2023.domain.model.User;
import me.dio.santander_dev_week_2023.domain.repository.FeatureRepository;
import me.dio.santander_dev_week_2023.domain.repository.UserRepository;
import me.dio.santander_dev_week_2023.service.FeatureService;

@Service
public class FeatureServiceImp implements FeatureService{

    private final FeatureRepository featureRepository;
    private final UserRepository userRepository;

    public FeatureServiceImp(FeatureRepository featureRepository, UserRepository userRepository) {
        this.featureRepository = featureRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Feature> findAllFeatures() {
        return featureRepository.findAll();
    }

    @Override
    public Feature findFeatureById(Long featureId) {
        return featureRepository.findById(featureId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Feature> findFeaturesByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        return new ArrayList<>(user.getFeatures());
    }

    @Override
    public User addFeatureToUser(Long userId, Feature feature) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));

        if(userRepository.existsByIdAndFeatures_Id(userId, feature.getId())) {
            throw new IllegalArgumentException("This Feature already exist for this user");
        } else {
            Feature feat = feature;
            feat.setId(feature.getId());
            feat.setIcon(feature.getIcon());
            feat.setDescription(feature.getDescription());

            user.getFeatures().add(feat);
            userRepository.save(user);
            return user;
        }
    }
    
}
