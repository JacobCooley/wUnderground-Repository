package com.example.wunderground;

import com.example.wunderground.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class MapDisplay extends Activity {

	private WeatherInfo[] cityData;
	private String number = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		Bundle data = getIntent().getExtras();
		number = data.getString("number");
		Log.d("Num ", number);

		if (number.equals("1")) {
			cityData = new WeatherInfo[1];
			cityData[0] = (WeatherInfo) data.getParcelable("cityData");
			Log.d("", cityData[0].getWeather());
			Log.d("", cityData[0].getCity());
			Log.d("", cityData[0].getState());
			Log.d("", cityData[0].getWind());
			Log.d("", cityData[0].getHumidity());
			Log.d("", cityData[0].getPrecipitation());
		} else if (number.equals("2")) {
			cityData = new WeatherInfo[2];
			cityData[0] = (WeatherInfo) data.getParcelable("cityData");
			cityData[1] = (WeatherInfo) data.getParcelable("cityData1");
			for(int i = 0; i<2; i++){
			Log.d("", cityData[i].getWeather());
			Log.d("", cityData[i].getCity());
			Log.d("", cityData[i].getState());
			Log.d("", cityData[i].getWind());
			Log.d("", cityData[i].getHumidity());
			Log.d("", cityData[i].getPrecipitation());
			}

		} else if (number.equals("3")) {
			cityData = new WeatherInfo[3];
			cityData[0] = (WeatherInfo) data.getParcelable("cityData");
			cityData[1] = (WeatherInfo) data.getParcelable("cityData1");
			cityData[2] = (WeatherInfo) data.getParcelable("cityData2");
			for(int i = 0; i<3; i++){
			Log.d("", cityData[i].getWeather());
			Log.d("", cityData[i].getCity());
			Log.d("", cityData[i].getState());
			Log.d("", cityData[i].getWind());
			Log.d("", cityData[i].getHumidity());
			Log.d("", cityData[i].getPrecipitation());
			}

		} else if (number.equals("4")) {
			cityData = new WeatherInfo[4];
			cityData[0] = (WeatherInfo) data.getParcelable("cityData");
			cityData[1] = (WeatherInfo) data.getParcelable("cityData1");
			cityData[2] = (WeatherInfo) data.getParcelable("cityData2");
			cityData[3] = (WeatherInfo) data.getParcelable("cityData3");
			for(int i = 0; i<4; i++){
			Log.d("", cityData[i].getWeather());
			Log.d("", cityData[i].getCity());
			Log.d("", cityData[i].getState());
			Log.d("", cityData[i].getWind());
			Log.d("", cityData[i].getHumidity());
			Log.d("", cityData[i].getPrecipitation());
			}

		} else if (number.equals("5")) {
			
			cityData = new WeatherInfo[5];
			cityData[0] = (WeatherInfo) data.getParcelable("cityData");
			cityData[1] = (WeatherInfo) data.getParcelable("cityData1");
			cityData[2] = (WeatherInfo) data.getParcelable("cityData2");
			cityData[3] = (WeatherInfo) data.getParcelable("cityData3");
			cityData[4] = (WeatherInfo) data.getParcelable("cityData4");
			for(int i = 0; i<5; i++){
			Log.d("", cityData[i].getWeather());
			Log.d("", cityData[i].getCity());
			Log.d("", cityData[i].getState());
			Log.d("", cityData[i].getWind());
			Log.d("", cityData[i].getHumidity());
			Log.d("", cityData[i].getPrecipitation());
			}

		}

	}

}