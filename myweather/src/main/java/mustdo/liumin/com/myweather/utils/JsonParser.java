package mustdo.liumin.com.myweather.utils;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import mustdo.liumin.com.myweather.bean.MyWeather;

/**
 * Created by Administrator on 2016-11-17.
 */

public class JsonParser {
    //解析JSON数据
    public  boolean parseJSONObjectOrJSONArray(String jsonData, Handler handler) {
        try {
            String count = "";
            JSONObject jsonObject = new JSONObject(jsonData);

            String status = jsonObject.optString("status");
            if (TextUtils.equals(status,"success")){

                MyWeather mw = new MyWeather();


                JSONArray jsonArray = jsonObject.getJSONArray("results");



                if (jsonArray.length() > 0) {
                    JSONObject object = jsonArray.getJSONObject(0);
                    String city = object.optString("currentCity");
                    mw.setPm25(object.optString("pm25"));
                    JSONArray array = object.getJSONArray("weather_data");

//                    for (int i = 0; i < array.length(); i++) {
//                        JSONObject jsonObject1 = array.getJSONObject(i);
//                        String dateDay = jsonObject1.optString("date");
//                        String weather = jsonObject1.optString("weather");
//                        String wind = jsonObject1.optString("wind");
//                        String temperature = jsonObject1.optString("temperature");
//                        count =count +"\n"+ dateDay + " " + weather + " " + wind + " " + temperature;
//                        Log.i("AAA",count);
//                    }
                    JSONObject jsonObject1 = array.getJSONObject(0);
                    mw.setDate(jsonObject1.optString("date"));
                    mw.setWeather(jsonObject1.optString("weather"));
                    mw.setWind(jsonObject1.optString("wind"));
                    mw.setTemperature(jsonObject1.optString("temperature"));
                    Log.i("LMTAG",mw.getDate()+mw.getPm25()+mw.getTemperature()+mw.getWeather());
                    Calendar c = java.util.Calendar.getInstance();
                    CalendarUtil  cu = new CalendarUtil(c);;
                    mw.setChineseday(cu.getDay());
                    Message message = new Message();
                    message.what = 0;
                    message.obj = mw;
                    handler.sendMessage(message);
                    return true;
                }
            }
            else
            {
                return false;
            }


        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }



}
