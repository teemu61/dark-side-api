package com.example.repositories;

import com.example.configuration.RepositoryConfiguration;
import com.example.domain.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class ConfiguraitionRepositoryTest {

    private RepositoryConfiguration repositoryConfiguration;
    private ConfigurationRepository configurationRepository;

    private Logger log = LogManager.getLogger(ConfiguraitionRepositoryTest.class);

    @Autowired
    public void setPersonRepository(RepositoryConfiguration repositoryConfiguration, ConfigurationRepository configurationRepository) {
        this.repositoryConfiguration = repositoryConfiguration;
        this.configurationRepository = configurationRepository;
    }

    @Test
    public void testSaveConfiguration(){

        Configuration configuration = new Configuration();
        configuration.setApikey("292f0b952e1422d5e0d02eceb2773f3d");
        configuration.setLattitude("20.16952");
        configuration.setLongitude("24.93545");

        //save configuration, verify has ID value after save
        assertNull(configuration.getId()); //null before save
        configurationRepository.save(configuration);
        assertNotNull(configuration.getId()); //not null after save
        //fetch from DB
        Configuration fetchedConf = configurationRepository.findById(configuration.getId()).orElse(null);

        //should not be null
        assertNotNull(fetchedConf);
    }
}
