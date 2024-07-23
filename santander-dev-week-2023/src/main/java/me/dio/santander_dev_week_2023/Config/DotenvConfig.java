package me.dio.santander_dev_week_2023.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class DotenvConfig {

    @Bean
    public Dotenv dotenv() {
        Dotenv dotenv = Dotenv.load();
        
        System.setProperty("spring.datasource.url", "jdbc:postgresql://" + dotenv.get("PGHOST") + ":" + dotenv.get("PGPORT") + "/" + dotenv.get("PGDATABASE"));
        System.setProperty("spring.datasource.username", dotenv.get("PGUSER"));
        System.setProperty("spring.datasource.password", dotenv.get("PGPASSWORD"));
        
        return dotenv;
    }
}
