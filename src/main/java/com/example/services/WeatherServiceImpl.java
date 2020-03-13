package com.example.services;

import com.example.bootstrap.ProductLoader;
import com.example.domain.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.plogitech.darksky.forecast.model.Daily;
import tk.plogitech.darksky.forecast.model.DailyDataPoint;
import tk.plogitech.darksky.forecast.model.Forecast;

import java.util.Iterator;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService{

    private Logger log = LogManager.getLogger(WeatherServiceImpl.class);

    @Autowired
    ProductLoader productLoader;

    @Autowired
    ConfigurationService configurationService;

    @Value("${com.example.apikey}")
    String apikey;

    @Value("${com.example.lattitude}")
    String lattitude;

    @Value("${com.example.longitude}")
    String longitude;

    public List<DailyDataPoint> getDailyDataPoints() {
        log.info("getDailyDataPoints called ...");

        Configuration configuration = this.getConfiguration();
        String[] args = {configuration.getApikey(), configuration.getLattitude(), configuration.getLongitude()};

//        String[] args = { apikey, lattitude, longitude };
        Forecast forecast = productLoader.runClient(args);
        Daily daily = forecast.getDaily();
        List<DailyDataPoint> dailyDataPointList = daily.getData();
        Iterator<DailyDataPoint> it = dailyDataPointList.iterator();

        while (it.hasNext()) {
            DailyDataPoint point = it.next();
//            log.info("point ");
//            log.info("- summary: " +point.getSummary());
//            log.info("- hi: "+point.getApparentTemperatureHigh());
//            log.info("- x: "+point.getTime());
            long time = point.getTime().toEpochMilli();
//            log.info("- time: "+time);
        }
        return dailyDataPointList ;
    }

    private Configuration getConfiguration() {
        return configurationService.getConfigurationById(1);
    };
}
