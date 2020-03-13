package com.example.controllers;

import com.example.domain.Datapoint;
import com.example.services.WeatherService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tk.plogitech.darksky.forecast.model.DailyDataPoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class WeatherController {

    private WeatherService weatherService;
    private Logger log = LogManager.getLogger(WeatherService.class);

    @Autowired
    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/datapoints", method = RequestMethod.GET)
    public String getDatapoints(Model model) {
        log.info("WeatherController called ... ");
        model.addAttribute("dum", "dum");
        return "datapoints";
    }

}
