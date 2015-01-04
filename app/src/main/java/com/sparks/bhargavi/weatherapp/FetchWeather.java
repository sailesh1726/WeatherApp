package com.sparks.bhargavi.weatherapp;


import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchWeather {

    FetchWeather(){}
    private static final String OPEN_WEATHER_MAP_API =
            "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
    private URL url;
    private HttpURLConnection connection;
    private InputStream inputStream;

    public String getJSON(String city){
        try {

            url = new URL(String.format(OPEN_WEATHER_MAP_API, city));

            connection =(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            //read response
            StringBuffer stringBuffer = new StringBuffer();
            inputStream= connection.getInputStream();
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream));
            String line= null;
            while ((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line+"\r\n");
            }
            inputStream.close();
            connection.disconnect();
            return stringBuffer.toString();

        }catch (Exception e){}
        finally {
            try { inputStream.close(); } catch(Throwable t) {}
            try { connection.disconnect(); } catch(Throwable t) {}
        }
        return null;
    }

}
