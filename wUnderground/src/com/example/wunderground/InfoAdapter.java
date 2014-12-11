/**
* 
* Info adaptor to display weather information on the map
* 
*/

package com.example.wunderground;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;

public class InfoAdapter implements InfoWindowAdapter{
	  private View popup=null;
	  private LayoutInflater inflater=null;

	  InfoAdapter(LayoutInflater inflater) {
	    this.inflater=inflater;
	  }

	  @Override
	  public View getInfoWindow(Marker marker) {
	    return(null);
	  }

	  @SuppressLint("InflateParams")
	  @Override
	  public View getInfoContents(Marker marker) {
	    if (popup == null) {
	      popup=inflater.inflate(R.layout.map_data, null);
	    }

	    TextView tv=(TextView)popup.findViewById(R.id.info);

	    tv.setText(marker.getTitle());
	    tv=(TextView)popup.findViewById(R.id.snippet);
	    tv.setText(marker.getSnippet());

	    return(popup);
	  }
}
