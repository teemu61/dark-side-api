package com.example.services;

import com.example.domain.Configuration;
import com.example.repositories.ConfigurationRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {
    private ConfigurationRepository configurationRepository;

    private Logger log = LogManager.getLogger(ConfigurationServiceImpl.class);

    @Autowired
    public void setConfigurationRepository(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @Override
    public Configuration getConfigurationById(Integer id) {
        return configurationRepository.findById(id).orElse(null);
    }


    @Override
    public Configuration saveConfiguration(Configuration configuration) {
        return configurationRepository.saveAndFlush(configuration);
    }

}
