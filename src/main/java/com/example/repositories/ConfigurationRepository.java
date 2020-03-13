package com.example.repositories;

import com.example.domain.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {

    public Configuration findById(String id);

}
