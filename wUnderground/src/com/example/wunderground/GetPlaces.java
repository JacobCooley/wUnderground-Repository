/**
* This connects to wundergrounds autocomplete api to 
* receive a list of places to use
*/

package com.example.wunderground;

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

public class GetPlaces extends ArrayAdapter<String> implements Filterable {

	private static final String LOG_TAG = "AUTOCOMPLETE ERROR";
	private Context mContext;
	private ArrayList<String> resultList;

	public GetPlaces(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		this.mContext = context;
	}

	@Override
	public int getCount() {
		if (resultList == null) {
			return 0;
		} else {
			return resultList.size();
		}
	}

	@Override
	public String getItem(int index) {
		if (resultList == null) {
			return "Empty";
		} else {
			return resultList.get(index);
		}

	}

	/**
	* Runs in a background thread to retrieve the information and publish the results
	* everytime the data is changed
	*/
	@Override
	public Filter getFilter() {
		Filter filter = new Filter() {
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				FilterResults filterResults = new FilterResults();
				ArrayList<String> queryResults;
				if (constraint != null) {// || constraint.length() != 0) {
					// Retrieve the autocomplete results.
					queryResults = autocomplete(constraint.toString());

					// Assign the data to the FilterResults
					filterResults.values = queryResults;
					filterResults.count = queryResults.size();
				} else {
					queryResults = new ArrayList<String>();
				}
				return filterResults;
			}

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				resultList = (ArrayList<String>) results.values;
				if (results != null && results.count > 0) {
					notifyDataSetChanged();
					((WunderGround) mContext).setValidWords(resultList);
				} else {
					notifyDataSetInvalidated();
				}
			}
		};
		return filter;
	}
	
	/**
	* Connects to API and retrieves the JSON data
	*/

	public static ArrayList<String> autocomplete(String string) {

		ArrayList<String> resultList = null;
		HttpURLConnection conn = null;
		StringBuilder jsonResults = new StringBuilder();
		try {

			StringBuilder sb = new StringBuilder(
					"http://autocomplete.wunderground.com/aq?query=");
			sb.append(URLEncoder.encode(string, "utf8"));
			sb.append("&c=US");
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
			Log.e(LOG_TAG, "Error processing AutoComplete URL", e);
			return resultList;
		} catch (IOException e) {
			Log.e(LOG_TAG, "Error connecting to AutoComplete", e);
			return resultList;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		try {
			// Create a JSON object hierarchy from the results
			JSONObject jsonObj = new JSONObject(jsonResults.toString());
			JSONArray predsJsonArray = jsonObj.getJSONArray("RESULTS");

			// Extract the Place descriptions from the results
			resultList = new ArrayList<String>(predsJsonArray.length());
			for (int i = 0; i < predsJsonArray.length(); i++) {
				resultList.add(predsJsonArray.getJSONObject(i)
						.getString("name"));
			}

		} catch (JSONException e) {
			Log.e(LOG_TAG, "Cannot process JSON results", e);
		}

		return resultList;
	}
}