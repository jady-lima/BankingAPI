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
    public List<Feature> findFeaturesByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        return new ArrayList<>(user.getFeatures());
    }

    @Override
    public Feature addFeatureToUser(Long userId, Feature feature) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addFeatureToUser'");
    }

    @Override
    public void deleteFeature(Long featureId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFeature'");
    }

    @Override
    public Feature findFeatureById(Long featureId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findFeatureById'");
    }
    
}
