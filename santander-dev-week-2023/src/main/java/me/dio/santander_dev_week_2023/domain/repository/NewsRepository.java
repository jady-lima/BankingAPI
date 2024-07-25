package me.dio.santander_dev_week_2023.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.santander_dev_week_2023.domain.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>{
    
}
