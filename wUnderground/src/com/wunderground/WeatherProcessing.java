package com.wunderground;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.util.Log;

public class WeatherProcessing{
	


	private static String LOG_TAG = "Forecast Error";
	
	public WeatherProcessing(Context context, int resource) {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public static void getForecast(String state, String city, WeatherInfo cityInfo) {
     HttpURLConnection conn = null;
     StringBuilder jsonResults = new StringBuilder();
     try {
     	
     	StringBuilder sb = new StringBuilder("http://api.wunderground.com/api/");
     	sb.append("9f7e0bf707f4dafa/conditions/q/");
     	sb.append(state);
     	sb.append("/");
     	sb.append(city);
     	sb.append(".json");

         URL url = new URL(sb.toString());
         conn = (HttpURLConnection) url.openConnection();
         InputStreamReader in = new InputStreamReader(conn.getInputStream());

         // Load the results into a StringBuilder
         int read;
         char[] buff = new char[1024];
         while ((read = in.read(buff)) != -1) {
             jsonResults.append(buff, 0, read);
         }
     } catch (MalformedURLException e) {
         Log.e(LOG_TAG, "Error processing Places API URL", e);
     } catch (IOException e) {
         Log.e(LOG_TAG, "Error connecting to Places API", e);
     } finally {
         if (conn != null) {
             conn.disconnect();
         }
     }
     try {
         // Create a JSON object hierarchy from the results
    	 
         JSONObject jsonObj = new JSONObject(jsonResults.toString());
         JSONObject Observe = jsonObj.getJSONObject("current_observation");
         cityInfo.setTemp(Observe.getString("temp_f"));
         cityInfo.setHumidity(Observe.getString("relative_humidity"));
         cityInfo.setPrecipitation(Observe.getString("precip_today_string"));
         cityInfo.setWeather(Observe.getString("weather"));
         cityInfo.setWind(Observe.getString("wind_string"));
         Observe = jsonObj.getJSONObject("current_observation").getJSONObject("display_location");
         cityInfo.setCity(Observe.getString("city"));
         cityInfo.setState(Observe.getString("state"));
         cityInfo.setCountry(Observe.getString("country"));
         
     } catch (JSONException e) {
         Log.e(LOG_TAG, "Cannot process JSON results", e);
     }
}
	
	public static void getForecast(String state, WeatherInfo cityInfo) {
	     HttpURLConnection conn = null;
	     StringBuilder jsonResults = new StringBuilder();
	     try {
	     	
	     	StringBuilder sb = new StringBuilder("http://api.wunderground.com/api/");
	     	sb.append("9f7e0bf707f4dafa/conditions/q/");
	     	sb.append(state);
	     	sb.append(".json");

	         URL url = new URL(sb.toString());
	         conn = (HttpURLConnection) url.openConnection();
	         InputStreamReader in = new InputStreamReader(conn.getInputStream());

	         // Load the results into a StringBuilder
	         int read;
	         char[] buff = new char[1024];
	         while ((read = in.read(buff)) != -1) {
	             jsonResults.append(buff, 0, read);
	         }
	     } catch (MalformedURLException e) {
	         Log.e(LOG_TAG, "Error processing Places API URL", e);
	     } catch (IOException e) {
	         Log.e(LOG_TAG, "Error connecting to Places API", e);
	     } finally {
	         if (conn != null) {
	             conn.disconnect();
	         }
	     }
	     try {
	         // Create a JSON object hierarchy from the results
	    	 
	         JSONObject jsonObj = new JSONObject(jsonResults.toString());
	         JSONObject Observe = jsonObj.getJSONObject("current_observation");
	         cityInfo.setTemp(Observe.getString("temp_f"));
	         cityInfo.setHumidity(Observe.getString("relative_humidity"));
	         cityInfo.setPrecipitation(Observe.getString("precip_today_string"));
	         cityInfo.setWeather(Observe.getString("weather"));
	         cityInfo.setWind(Observe.getString("wind_string"));
	         Observe = jsonObj.getJSONObject("current_observation").getJSONObject("display_location");
	         cityInfo.setState(Observe.getString("state"));
	         cityInfo.setCountry(Observe.getString("country"));
	         
	     } catch (JSONException e) {
	         Log.e(LOG_TAG, "Cannot process JSON results", e);
	     }
	}
}
