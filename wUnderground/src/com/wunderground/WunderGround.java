package com.wunderground;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.wunderground.R;




public class WunderGround extends Activity implements OnItemClickListener {
	
	private double lat;
	private double lon;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wunder_ground);
        
        
        //Setting the adapters for the autocomplete textboxes using Google Places API
      //********************************************************************************
        
        final AutoCompleteTextView autoCompView = (AutoCompleteTextView) findViewById(R.id.city1);
        autoCompView.setAdapter(new GetPlaces(this, R.layout.text_file));
        final AutoCompleteTextView autoCompView2 = (AutoCompleteTextView) findViewById(R.id.city2);
        autoCompView2.setAdapter(new GetPlaces(this, R.layout.text_file));
        final AutoCompleteTextView autoCompView3 = (AutoCompleteTextView) findViewById(R.id.city3);
        autoCompView3.setAdapter(new GetPlaces(this, R.layout.text_file));
        final AutoCompleteTextView autoCompView4 = (AutoCompleteTextView) findViewById(R.id.city4);
        autoCompView4.setAdapter(new GetPlaces(this, R.layout.text_file));
        final AutoCompleteTextView autoCompView5 = (AutoCompleteTextView) findViewById(R.id.city5);
        autoCompView5.setAdapter(new GetPlaces(this, R.layout.text_file));
      //********************************************************************************
        
        
        autoCompView.setVisibility(View.INVISIBLE);;
        autoCompView2.setVisibility(View.INVISIBLE);
        autoCompView3.setVisibility(View.INVISIBLE);
        autoCompView4.setVisibility(View.INVISIBLE);
        autoCompView5.setVisibility(View.INVISIBLE);
        
       
        
        
        
        Button clickButton = (Button) findViewById(R.id.button1);
        clickButton.setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String str = autoCompView.getText().toString();
				final String weatherLocation = str.replace(",", " ")
						.replace("  ", " ").replace(" ", "%20");
				Thread t = new Thread(){
					public void run(){
				ArrayList<String> Forecast = WeatherProcessing.getForecast(weatherLocation);
				Log.d("Forecast: ", Forecast.toString());
					}
				};
				t.start();
				
				
				
			}

        
        });
        
        
        //Integrating the Checkboxes and text fields
        //********************************************************************************
        
        final CheckBox check = (CheckBox) findViewById(R.id.checkBox1);
        final CheckBox check2 = (CheckBox) findViewById(R.id.checkBox2);       
        final CheckBox check3 = (CheckBox) findViewById(R.id.checkBox3);        
        final CheckBox check4 = (CheckBox) findViewById(R.id.checkBox4);        
        final CheckBox check5 = (CheckBox) findViewById(R.id.checkBox5);   
        
        check2.setVisibility(View.INVISIBLE);
        check3.setVisibility(View.INVISIBLE);
        check4.setVisibility(View.INVISIBLE);
        check5.setVisibility(View.INVISIBLE);
        
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
            	if(isChecked){
            		autoCompView.setVisibility(View.VISIBLE);
            		check2.setVisibility(View.VISIBLE);
            	}
            	else{
            		autoCompView.setVisibility(View.INVISIBLE);
            		check2.setVisibility(View.INVISIBLE);            	}
            }
        }
     );

        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
            	if(isChecked){
            		autoCompView2.setVisibility(View.VISIBLE);
            		check3.setVisibility(View.VISIBLE);
            		check.setEnabled(false);
            	}
            	else{
            		autoCompView2.setVisibility(View.INVISIBLE);
            		check3.setVisibility(View.INVISIBLE);  
            		check.setEnabled(true);
            		}
            }
        }
     );  

        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
            	if(isChecked){
            		autoCompView3.setVisibility(View.VISIBLE);
            		check2.setEnabled(false);
            		check4.setVisibility(View.VISIBLE);
            	}
            	else{
            		autoCompView3.setVisibility(View.INVISIBLE);
            		check2.setEnabled(true);
            		check4.setVisibility(View.INVISIBLE);
            	}
            }
        }
     );  

        check4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
            	if(isChecked){
            		autoCompView4.setVisibility(View.VISIBLE);
            		check3.setEnabled(false);
            		check5.setVisibility(View.VISIBLE);
            	}
            	else{
            		autoCompView4.setVisibility(View.INVISIBLE);
            		check3.setEnabled(true);
            		check5.setVisibility(View.INVISIBLE);
            	}
            }
        }
     );  

        check5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
            	if(isChecked){
            		autoCompView5.setVisibility(View.VISIBLE);
            		check4.setEnabled(false);
            	}
            	else{
            		autoCompView5.setVisibility(View.INVISIBLE);
            		check4.setEnabled(true);
            	}
            }
        }
     );    
        
        
      //********************************************************************************
        
        
        
        
        
    }
    

    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wunder_ground, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		String str = (String) parent.getItemAtPosition(position);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
    
    
    
    
    
}
