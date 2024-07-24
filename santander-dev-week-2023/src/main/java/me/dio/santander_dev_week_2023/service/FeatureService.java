package me.dio.santander_dev_week_2023.service;

import java.util.List;

import me.dio.santander_dev_week_2023.domain.model.Feature;
import me.dio.santander_dev_week_2023.domain.model.User;

public interface FeatureService {
    List<Feature> findAllFeatures();

    Feature findFeatureById(Long featureId);

    List<Feature> findFeaturesByUser(Long userId);

    User addFeatureToUser(Long userId, Feature feature);
}
