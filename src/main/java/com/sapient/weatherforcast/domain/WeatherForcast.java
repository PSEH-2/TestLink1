package com.sapient.weatherforcast.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForcast {
    private String city;

    public WeatherForcast(String city, List<Forcast> forcast) {
        this.city = city;
        this.forcast = forcast;
    }

    private List<Forcast> forcast;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Forcast> getForcast() {
        return forcast;
    }

    public void setForcast(List<Forcast> forcast) {
        this.forcast = forcast;
    }
}
