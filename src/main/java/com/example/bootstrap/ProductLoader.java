package com.example.bootstrap;

import com.example.domain.Configuration;

import com.example.repositories.ConfigurationRepository;

import com.example.services.WeatherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {


    private ConfigurationRepository configurationRepository;
    private WeatherService weatherService;
    private Logger log = LogManager.getLogger(ProductLoader.class);

    @Autowired
    public void setProductRepository(ConfigurationRepository configurationRepository, WeatherService weatherService) {
        this.configurationRepository = configurationRepository;
        this.weatherService = weatherService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Configuration configuraition = new Configuration();
        configuraition.setId(1);
        configuraition.setApikey("292f0b952e1422d5e0d02eceb2773f3d");
        configuraition.setLongitude("24.93545");
        configuraition.setLattitude("20.16952");
        configurationRepository.save(configuraition);
        Configuration feachedConfiguration = configurationRepository.findById(1).get();
        log.info("configuration from db has id: " +feachedConfiguration.getId());


        String lattitude = "60.16952";
        String longitude = "24.93545";
        String apikey="292f0b952e1422d5e0d02eceb2773f3d";

        String[] args = { apikey, lattitude, longitude };
        //weatherService.getDailyDataPoints();
    }

}
