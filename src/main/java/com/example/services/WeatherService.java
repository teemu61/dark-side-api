package com.example.services;

import com.example.domain.Summary;
import tk.plogitech.darksky.forecast.model.DailyDataPoint;

import java.util.List;


public interface WeatherService {

    public List<DailyDataPoint> getDailyDataPoints();

    public Summary getSummary();

}
