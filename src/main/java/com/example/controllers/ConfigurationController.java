package com.example.controllers;

import com.example.domain.Configuration;
import com.example.domain.Education;
import com.example.domain.Person;
import com.example.services.ConfigurationService;
import com.example.services.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@Transactional
@Controller
public class ConfigurationController {

    private ConfigurationService configurationService;
    private Logger log = LogManager.getLogger(ConfigurationService.class);

    @Autowired
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @RequestMapping(value = "/api/configuration", method = RequestMethod.GET)
    public String list(Model model) {

        Configuration configuration = configurationService.getConfigurationById(1);
        log.info("configuration from db has id: " +configuration.getId());

        model.addAttribute("configuration", configuration);
        log.info("show configuration ...");
        return "configurationshow";
    }

    @RequestMapping("person/edit")
    public String edit(Model model) {
        Configuration configuration = configurationService.getConfigurationById(1);
        model.addAttribute("configuration", configuration);
        log.info("return configurationform for editing existing configuration ...");
        return "configurationform";
    }

}
