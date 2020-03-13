package com.example.client;

import org.springframework.stereotype.Service;
import tk.plogitech.darksky.forecast.DarkSkyClient;
import com.fasterxml.jackson.core.JsonParser;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.MapperFeature.AUTO_DETECT_GETTERS;
import static com.fasterxml.jackson.databind.MapperFeature.REQUIRE_SETTERS_FOR_GETTERS;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.io.InputStream;
import static java.util.logging.Level.FINE;
import java.util.logging.Logger;
import tk.plogitech.darksky.forecast.APIKey;
import tk.plogitech.darksky.forecast.DarkSkyClient;
import tk.plogitech.darksky.forecast.ForecastException;
import tk.plogitech.darksky.forecast.ForecastRequest;
import tk.plogitech.darksky.forecast.ForecastRequestBuilder;
import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.model.Forecast;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;
import static tk.plogitech.darksky.forecast.util.Assert.notNull;

@Service
public class DarkSkyJacksonClient extends DarkSkyClient {

    private static final Logger logger = Logger.getLogger(DarkSkyJacksonClient.class.getSimpleName());
    private static final ObjectMapper mapper = objectMapper();

    public Forecast forecast(ForecastRequest request) throws ForecastException {
        notNull("The ForecastRequest cannot be null.", request);
        logger.log(FINE, "Executing Forecat request: {0}", request);

        try (InputStream is = executeForecastRequest(request)) {
            return mapper.readValue(is, Forecast.class);

        } catch (IOException e) {
            throw new ForecastException("Forecast cannot be fetched.", e);
        }
    }

    static ObjectMapper objectMapper() {
        ObjectMapper result = new ObjectMapper();
        result.registerModule(new JavaTimeModule());
        result.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        result.configure(REQUIRE_SETTERS_FOR_GETTERS, false);
        result.configure(AUTO_DETECT_GETTERS, true);
        result.configure(INDENT_OUTPUT, true);
        result.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        return result;
    }

}