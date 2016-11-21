package mustdo.liumin.com.myweather.utils;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016-11-17.
 */

public class MyUtils {


    public static Object parserFromJson(Type type, String jsonData) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(jsonData, type);
        } catch (JsonParseException e) {
            return null;
        }
    }



}
