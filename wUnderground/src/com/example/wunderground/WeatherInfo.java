package com.example.wunderground;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class WeatherInfo implements Parcelable {

	private String weather;
	private String temp;
	private String humidity;
	private String precipitation;
	private String wind;
	private String city;
	private String state;
	private String country;

	public WeatherInfo() {
		this.weather = null;
		this.temp = null;
		this.humidity = null;
		this.precipitation =null;
		this.wind = null;
		this.city = null;
		this.state = null;
		this.country = null;

	}

	public WeatherInfo(Parcel in) {
		readfromParcel(in);
	}

	private void readfromParcel(Parcel in) {
		String[] data = new String[8];
		in.readStringArray(data);
		this.weather = data[0];
		this.temp = data[1];
		this.humidity = data[2];
		this.precipitation =data[3];
		this.wind = data[4];
		this.city = data[5];
		this.state = data[6];
		this.country = data[7];
		/*
		Log.d("", this.getWeather());

		Log.d("", this.getCity());
		Log.d("", this.getState());
		Log.d("", this.getWind());
		Log.d("", this.getHumidity());
		Log.d("", this.getPrecipitation());*/

	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeStringArray(new String[] { this.weather, this.temp,
				this.humidity, this.precipitation, this.wind, this.city,
				this.state, this.country });
	}

	public static final Parcelable.Creator<WeatherInfo> CREATOR = new Parcelable.Creator<WeatherInfo>() {
		public WeatherInfo createFromParcel(Parcel in) {
			return new WeatherInfo(in);
		}

		public WeatherInfo[] newArray(int size) {
			return new WeatherInfo[size];
		}
	};

}
