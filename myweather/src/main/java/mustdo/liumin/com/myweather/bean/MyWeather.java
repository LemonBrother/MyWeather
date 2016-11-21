package mustdo.liumin.com.myweather.bean;

/**
 * Created by Administrator on 2016-11-17.
 */






public class MyWeather {
    public String date;
    public String chineseday;
    public String pm25;
    public String weather;
    public String wind;
    public String temperature;
    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }


    public String getChineseday() {
        return chineseday;
    }

    public void setChineseday(String chineseday) {
        this.chineseday = chineseday;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String toString (){
        return "今天是"+ date+"农历"+chineseday+"天气:"+weather+wind+temperature+ "空气质量"+pm25;
    }
}
