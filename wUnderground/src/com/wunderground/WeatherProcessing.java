package com.wunderground;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

public class WeatherProcessing{// implements Filterable{
	


	private static String LOG_TAG = "Forecast Error";
	private ArrayList<String> resultList;
	
	public WeatherProcessing(Context context, int resource) {
		super();
		// TODO Auto-generated constructor stub
	}

/*
	
	 @Override
	    public Filter getFilter() {
	        Filter filter = new Filter() {
	            @Override
	            protected FilterResults performFiltering(CharSequence constraint) {
	                FilterResults filterResults = new FilterResults();
	                if (constraint != null) {
	                    // Retrieve the autocomplete results.
	                    resultList = getForecast(constraint.toString());                

	                    // Assign the data to the FilterResults
	                    filterResults.values = resultList;
	                    filterResults.count = (resultList.size());
	                }
	                return filterResults;
	            }

				@Override
				protected void publishResults(CharSequence constraint,
						FilterResults results) {
					// TODO Auto-generated method stub
					
				}};
	         
	        return filter;
	    }
	*/
	
	
	
	public static ArrayList<String> getForecast(String string) {
	 ArrayList<String> resultList = null;
     HttpURLConnection conn = null;
     StringBuilder jsonResults = new StringBuilder();
     try {
     	
     	StringBuilder sb = new StringBuilder("http://api.wunderground.com/api/");
     	sb.append("9f7e0bf707f4dafa/conditions/q/CA/San_Fransisco.json");

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
         return resultList;
     } catch (IOException e) {
         Log.e(LOG_TAG, "Error connecting to Places API", e);
         return resultList;
     } finally {
         if (conn != null) {
             conn.disconnect();
         }
     }
     resultList = new ArrayList<String>(2);
     try {
         // Create a JSON object hierarchy from the results
    	 
         JSONObject jsonObj = new JSONObject(jsonResults.toString());
         JSONObject Observe = jsonObj.getJSONObject("current_observation");
         resultList.add(Observe.getString("temp_f"));
         Log.d("test", resultList.toString());
     } catch (JSONException e) {
         Log.e(LOG_TAG, "Cannot process JSON results", e);
     }
     
	return resultList;
}
}
