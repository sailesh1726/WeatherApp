package com.sparks.bhargavi.weatherapp;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONWeatherParser {

    public static Location getParsedWeather(String jObject){
        Location location=new Location();
        try {
            JSONObject jsonObject= new JSONObject(jObject);

            JSONObject coodJsonObject= jsonObject.getJSONObject("coord");

            location.setLat(coodJsonObject.getDouble("lat"));
            location.setLon(coodJsonObject.getDouble("lon"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return location;
    }
}
