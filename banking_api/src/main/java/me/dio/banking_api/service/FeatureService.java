package me.dio.banking_api.service;

import java.util.List;

import me.dio.banking_api.domain.model.Feature;
import me.dio.banking_api.domain.model.User;

public interface FeatureService {
    List<Feature> findAllFeatures();

    Feature findFeatureById(Long featureId);

    List<Feature> findFeaturesByUser(Long userId);

    User addFeatureToUser(Long userId, Feature feature);
}
