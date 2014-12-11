
/**
* Object to store all the weather information
*
* @author Jacob Cooley
*/

package com.example.wunderground;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class WeatherInfo implements Parcelable {

	private String weather = null;
	private String temp = null;
	private String humidity = null;
	private String precipitation = null;
	private String wind = null;
	private String city = null;
	private String state = null;
	private String country = null;
	private String lat = null;
	private String lon = null;
	private String icon = null;

	public WeatherInfo() {

	}

	public WeatherInfo(Parcel in) {
		readfromParcel(in);
	}
	/**
	*   Must use Parcelable to stream across classes
	*/

	private void readfromParcel(Parcel in) {
		String[] data = new String[11];
		in.readStringArray(data);
		this.weather = data[0];
		this.temp = data[1];
		this.humidity = data[2];
		this.precipitation =data[3];
		this.wind = data[4];
		this.city = data[5];
		this.state = data[6];
		this.country = data[7];
		this.lat = data[8];
		this.lon = data[9];
		this.icon = data[10];

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
	
	

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeStringArray(new String[] { this.weather, this.temp,
				this.humidity, this.precipitation, this.wind, this.city,
				this.state, this.country, this.lat, this.lon, this.icon });
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
