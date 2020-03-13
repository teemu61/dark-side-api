package com.example.services;

import com.example.bootstrap.ProductLoader;
import com.example.client.DarkSkyJacksonClient;
import com.example.domain.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.*;

import java.util.Iterator;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService{

    private Logger log = LogManager.getLogger(WeatherServiceImpl.class);

    @Autowired
    ProductLoader productLoader;

    @Autowired
    DarkSkyJacksonClient darkSkyJacksonClient;

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
        Forecast forecast = this.getForecast(configuration);

        Daily daily = forecast.getDaily();
        List<DailyDataPoint> dailyDataPointList = daily.getData();
        Iterator<DailyDataPoint> it = dailyDataPointList.iterator();

        return dailyDataPointList ;
    }

    private Configuration getConfiguration() {
        return configurationService.getConfigurationById(1);
    };

    public Forecast getForecast(Configuration configuration ) {

        String apikey = configuration.getApikey();
        String latitude = configuration.getLattitude();
        String longitude = configuration.getLongitude();

        ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey(apikey))
                .location(new GeoCoordinates(new Longitude(Double.valueOf(latitude)), new Latitude(Double.valueOf(longitude)))).build();

        DarkSkyJacksonClient client = new DarkSkyJacksonClient();
        Forecast forecast = null;
        try {
            forecast = client.forecast(request);
        } catch (ForecastException e) {
            e.printStackTrace();
        }
        System.out.println("The current weather: " + forecast.getCurrently().getSummary());
        Daily daily = forecast.getDaily();
        List<DailyDataPoint> dailyDataPoints = daily.getData();
        Iterator<DailyDataPoint> it = dailyDataPoints.iterator();

        while (it.hasNext()) {
            DailyDataPoint point = it.next();
            long time = point.getTime().toEpochMilli();
        }
        return forecast;
    }
}
