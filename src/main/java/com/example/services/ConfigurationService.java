package com.example.services;


import com.example.domain.Configuration;

public interface ConfigurationService {

    Configuration getConfigurationById(Integer id);

    Configuration saveConfiguration(Configuration configuration);
}

