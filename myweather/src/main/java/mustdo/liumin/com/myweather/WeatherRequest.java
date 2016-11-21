package mustdo.liumin.com.myweather;

import java.util.Calendar;
import java.util.List;

import mustdo.liumin.com.myweather.bean.Results;
import mustdo.liumin.com.myweather.utils.CalendarUtil;

/**
 * Created by Administrator on 2016-11-17.
 */

public class WeatherRequest {
    public String error,status,date;

    public List<Results> results;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }





    public String toString (){

        Calendar c = java.util.Calendar.getInstance();
        CalendarUtil cu = new CalendarUtil(c);
        return "今天是"+ date+"农历"+cu.getDay()+"天气:"+results.get(0).getWeather_data().get(0).getWeather()
                +results.get(0).getWeather_data().get(0).getWind()
                +results.get(0).getWeather_data().get(0).getTemperature()
                + "空气质量"+results.get(0).getPm25();
    }
}
