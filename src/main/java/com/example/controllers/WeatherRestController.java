package com.example.controllers;

import com.example.domain.Datapoint;
import com.example.domain.Education;
import com.example.domain.Person;
import com.example.services.PersonService;
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


@RestController
public class WeatherRestController {

    private WeatherService weatherService;
    private Logger log = LogManager.getLogger(WeatherService.class);

    @Autowired
    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

        private final AtomicLong counter = new AtomicLong();

        @GetMapping("/api/datapoints")
        public List<Datapoint> get() {

            log.info("WeatherRestController called");

            List<DailyDataPoint> dataPointList = weatherService.getDailyDataPoints();
            List<Datapoint> datapointList = new ArrayList<>();

            Iterator<DailyDataPoint> it = dataPointList.iterator();
            while (it.hasNext()) {
                Datapoint dp = new Datapoint();
                DailyDataPoint d = it.next();
                dp.setTime(d.getTime().toEpochMilli());
                dp.setTempHi(d.getApparentTemperatureHigh());
                dp.setTempLo(d.getApparentTemperatureLow());
                datapointList.add(dp);
            }
            return datapointList;
    }


}
