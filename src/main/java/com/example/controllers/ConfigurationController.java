package com.example.controllers;

import com.example.domain.Configuration;
import com.example.services.ConfigurationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ConfigurationController {

    private ConfigurationService configurationService;
    private Logger log = LogManager.getLogger(ConfigurationService.class);

    @Autowired
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @RequestMapping(value = "/api/configuration/show", method = RequestMethod.GET)
    public String list(Model model) {

        Configuration configuration = configurationService.getConfigurationById(1);
        log.info("configuration from db has id: " +configuration.getId());

        model.addAttribute("configuration", configuration);
        log.info("show configuration ...");
        return "configurationshow";
    }

    @RequestMapping("api/configuration/edit")
    public String edit(Model model) {
        Configuration configuration = configurationService.getConfigurationById(1);
        model.addAttribute("configuration", configuration);
        log.info("return configurationform for editing existing configuration ...");
        return "configurationform";
    }

    @RequestMapping(value = "api/configuration/update", method = RequestMethod.POST)
    public String saveConfiguration(Configuration configuration) {
        configurationService.saveConfiguration(configuration);
        log.info("redirect to show configuration information ...");
        return "redirect:/api/configuration/show";
    }
}
