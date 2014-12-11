/**
* This class displays the map and handles all marker creation
*  and displays the WeatherInfo Objects
*
* @author Jacob Cooley
*/

package com.example.wunderground;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapDisplay extends Activity implements OnMapReadyCallback,
		OnInfoWindowClickListener {

	private WeatherInfo[] cityData;
	private String number = null;
	private InfoAdapter adapter;
	private GoogleMap gmap = null;
	private Bitmap img = null;
	private Canvas c = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);

		MapFragment mapFragment = (MapFragment) getFragmentManager()
				.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
		gmap = mapFragment.getMap();

		gmap.setInfoWindowAdapter(new InfoAdapter(getLayoutInflater()));
		gmap.setOnInfoWindowClickListener(this);

		Bundle data = getIntent().getExtras();
		number = data.getString("number");
		Log.d("Num ", number);

		if (number.equals("1")) {
			cityData = new WeatherInfo[1];
			cityData[0] = (WeatherInfo) data.getParcelable("cityData");

			/*
			 * Log.d("", cityData[0].getWeather()); Log.d("",
			 * cityData[0].getCity()); Log.d("", cityData[0].getState());
			 * Log.d("", cityData[0].getWind()); Log.d("",
			 * cityData[0].getHumidity()); Log.d("",
			 * cityData[0].getPrecipitation()); Log.d("", cityData[0].getLat());
			 * Log.d("", cityData[0].getLon()); Log.d("",
			 * cityData[0].getIcon());
			 */

		} else if (number.equals("2")) {
			cityData = new WeatherInfo[2];
			cityData[0] = (WeatherInfo) data.getParcelable("cityData");
			cityData[1] = (WeatherInfo) data.getParcelable("cityData1");

		} else if (number.equals("3")) {
			cityData = new WeatherInfo[3];
			cityData[0] = (WeatherInfo) data.getParcelable("cityData");
			cityData[1] = (WeatherInfo) data.getParcelable("cityData1");
			cityData[2] = (WeatherInfo) data.getParcelable("cityData2");

		} else if (number.equals("4")) {
			cityData = new WeatherInfo[4];
			cityData[0] = (WeatherInfo) data.getParcelable("cityData");
			cityData[1] = (WeatherInfo) data.getParcelable("cityData1");
			cityData[2] = (WeatherInfo) data.getParcelable("cityData2");
			cityData[3] = (WeatherInfo) data.getParcelable("cityData3");

		} else if (number.equals("5")) {

			cityData = new WeatherInfo[5];
			cityData[0] = (WeatherInfo) data.getParcelable("cityData");
			cityData[1] = (WeatherInfo) data.getParcelable("cityData1");
			cityData[2] = (WeatherInfo) data.getParcelable("cityData2");
			cityData[3] = (WeatherInfo) data.getParcelable("cityData3");
			cityData[4] = (WeatherInfo) data.getParcelable("cityData4");

		}

	}
	
	/**
	*    Whenever the map is ready, add markers  
	*/

	@Override
	public void onMapReady(GoogleMap map) {

		if (number.equals("1")) {
			addMarker(map, 1);
		} else if (number.equals("2")) {

			addMarker(map, 2);
		} else if (number.equals("3")) {

			addMarker(map, 3);

		} else if (number.equals("4")) {
			addMarker(map, 4);

		} else if (number.equals("5")) {
			addMarker(map, 5);
		}

	}

	
	/**
	*   Method is called once map is ready  
	*/
	public void addMarker(GoogleMap map, int i) {

		for (int k = 0; k < i; k++) {
			if (cityData[k] != null || !cityData[k].getLat().isEmpty()|| !cityData[k].getLon().isEmpty()) {
				getBitmapFromURL(cityData[k].getIcon());

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				double lat = Double.parseDouble(cityData[k].getLat());
				double lon = Double.parseDouble(cityData[k].getLon());
				LatLng mLoc = new LatLng(lat, lon);
				map.addMarker(new MarkerOptions()
						.title(cityData[k].getCity() + ", "
								+ cityData[k].getState() + "\n")
						.snippet(
								"\nWeather: " + cityData[k].getWeather()
										+ "\nTemperature: "
										+ cityData[k].getTemp()
										+ "\nHumidity: "
										+ cityData[k].getHumidity()
										+ "\nPrecipitation: "
										+ cityData[k].getPrecipitation()
										+ "\nWind: " + cityData[k].getWind())
						.position(mLoc)
						.icon(BitmapDescriptorFactory.fromBitmap(img)));
			} else {

				Toast.makeText(getApplicationContext(), "City Data is null!.",
						Toast.LENGTH_SHORT).show();

			}
		}

	}

	public Bitmap getBitmapFromURL(final String imageUrl) {

		new Thread(new Runnable() {
			public void run() {
				try {
					URL url = new URL(imageUrl);
					HttpURLConnection connection = (HttpURLConnection) url
							.openConnection();
					connection.setDoInput(true);
					connection.connect();
					InputStream input = connection.getInputStream();
					Bitmap bm = BitmapFactory.decodeStream(input);
					img = bm.copy(Bitmap.Config.ARGB_8888, true);
					int width = bm.getWidth();
					int height = bm.getHeight();
					for (int x = 0; x < width; x++) {
						for (int y = 0; y < height; y++) {
							if (bm.getPixel(x, y) == Color.WHITE) {
								img.setPixel(x, y, Color.TRANSPARENT);
							} else {
								img.setPixel(x, y, bm.getPixel(x, y));
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}).start();
		return img;
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		// TODO Auto-generated method stub
		// Toast.makeText(this, marker.getTitle(), Toast.LENGTH_LONG).show();
	}

}