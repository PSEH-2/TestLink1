package com.sapient.weatherforcast.service;

import com.sapient.weatherforcast.domain.Forcast;
import com.sapient.weatherforcast.domain.TIP;
import com.sapient.weatherforcast.domain.WeatherForcast;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class WeatherService {

    @RequestMapping("/getWeatherForcast")
    public WeatherForcast getWeatherForcast(@RequestParam(value="city", required=false, defaultValue="London") String city) {

        List<Forcast>  forcastList = new ArrayList<>();

        OWM owm = new OWM("b6907d289e10d714a6e88b30761fae22");
        CurrentWeather cwd = null;
        try {
            cwd = owm.currentWeatherByCityName(city);


        Forcast todayForcast = new Forcast(cwd.getMainData().getTempMax(),cwd.getMainData().getTempMin());
        if(cwd.hasRainData())
            todayForcast.setTip(TIP.CARRY_UMBRELLA);
        if(cwd.getMainData().getTempMax()>40)
            todayForcast.setTip(TIP.USE_SUNSCREEN);
        Forcast tommForcast = new Forcast(cwd.component8().getTempMax(),cwd.component8().getTempMin());
        forcastList.add(todayForcast);
        forcastList.add(tommForcast);
        } catch (APIException e) {
            e.printStackTrace();
        }
        return new WeatherForcast(city,forcastList);
    }
}
