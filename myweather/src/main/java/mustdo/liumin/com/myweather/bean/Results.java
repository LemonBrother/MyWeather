package mustdo.liumin.com.myweather.bean;

import java.util.List;

/**
 * Created by Administrator on 2016-11-17.
 */

public class Results {

    public String currentCity,pm25;
    public List<IndexItem> index;
    public List<WeatherData> weather_data;

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public List<IndexItem> getIndex() {
        return index;
    }

    public void setIndex(List<IndexItem> index) {
        this.index = index;
    }

    public List<WeatherData> getWeather_data() {
        return weather_data;
    }

    public void setWeather_data(List<WeatherData> weather_data) {
        this.weather_data = weather_data;
    }
}
