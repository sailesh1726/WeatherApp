package com.sparks.bhargavi.weatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeatherFragment extends BaseFragment {
    private View rootView;
    TextView latTextView;
    TextView lonTextView;
    TextView cityTextView;
    EditText cityEditText;
    Button findButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.home_fragment, container, false);
        latTextView = (TextView) rootView.findViewById(R.id.latTextView);
        lonTextView = (TextView) rootView.findViewById(R.id.lonTextView);
        cityTextView = (TextView) rootView.findViewById(R.id.cityTextView);
        cityEditText = (EditText) rootView.findViewById(R.id.cityNameEditText);
        findButton = (Button) rootView.findViewById(R.id.findWeatherButton);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cityEditText.getText().toString().isEmpty()) {
                    String cityName = cityEditText.getText().toString();
                    new WeatherAsync().execute(new String[]{cityName});
                    cityEditText.setText("");
                }
            }
        });


        return rootView;
    }

    private class WeatherAsync extends AsyncTask<String, Void, Location> {

        @Override
        protected Location doInBackground(String... params) {
            Location location;
            String jObject = (new FetchWeather()).getJSON(params[0]);
            location = JSONWeatherParser.getParsedWeather(jObject);
            return location;
        }

        @Override
        protected void onPostExecute(Location location) {
            super.onPostExecute(location);
            latTextView.setText(location.getLat().toString());
            lonTextView.setText(location.getLon().toString());
            cityTextView.setText(location.getCity().toString());
        }
    }
}
