package me.dio.santander_dev_week_2023.service;

import java.util.List;

import me.dio.santander_dev_week_2023.domain.model.Feature;

public interface FeatureService {
    List<Feature> findFeaturesByUser(Long userId);

    Feature findFeatureById(Long featureId);

    Feature addFeatureToUser(Long userId, Feature feature);

    void deleteFeature(Long featureId);
}
