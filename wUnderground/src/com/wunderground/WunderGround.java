package com.wunderground;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.wunderground.R;

public class WunderGround extends Activity {



	private AutoCompleteTextView autoCompView1;
	private AutoCompleteTextView autoCompView2;
	private AutoCompleteTextView autoCompView3;
	private AutoCompleteTextView autoCompView4;
	private AutoCompleteTextView autoCompView5;
	private ArrayList<String> validWords, concreteWords;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wunder_ground);

		// Setting the adapters for the textboxes using Wundergrounds
		// autocomplete api
		//
		// ********************************************************************************
		validWords = new ArrayList<String>();
		concreteWords = new ArrayList<String>();

		autoCompView1 = (AutoCompleteTextView) findViewById(R.id.city1);
		autoCompView1.setAdapter(new GetPlaces(this, R.layout.text_file));
		autoCompView1.setValidator(new Validator());
		autoCompView2 = (AutoCompleteTextView) findViewById(R.id.city2);
		autoCompView2.setAdapter(new GetPlaces(this, R.layout.text_file));
		autoCompView2.setValidator(new Validator());
		autoCompView3 = (AutoCompleteTextView) findViewById(R.id.city3);
		autoCompView3.setAdapter(new GetPlaces(this, R.layout.text_file));
		autoCompView3.setValidator(new Validator());
		autoCompView4 = (AutoCompleteTextView) findViewById(R.id.city4);
		autoCompView4.setAdapter(new GetPlaces(this, R.layout.text_file));
		autoCompView4.setValidator(new Validator());
		autoCompView5 = (AutoCompleteTextView) findViewById(R.id.city5);
		autoCompView5.setAdapter(new GetPlaces(this, R.layout.text_file));
		autoCompView5.setValidator(new Validator());
		
		autoCompView1.setVisibility(View.INVISIBLE);
		autoCompView1.setThreshold(1);
		autoCompView1.setOnKeyListener(new KeyListener(autoCompView1));
		autoCompView2.setVisibility(View.INVISIBLE);
		autoCompView2.setThreshold(1);
		autoCompView2.setOnKeyListener(new KeyListener(autoCompView2));
		autoCompView3.setVisibility(View.INVISIBLE);
		autoCompView3.setThreshold(1);
		autoCompView3.setOnKeyListener(new KeyListener(autoCompView3));
		autoCompView4.setVisibility(View.INVISIBLE);
		autoCompView4.setThreshold(1);
		autoCompView4.setOnKeyListener(new KeyListener(autoCompView4));
		autoCompView5.setVisibility(View.INVISIBLE);
		autoCompView5.setThreshold(1);
		autoCompView5.setOnKeyListener(new KeyListener(autoCompView5));

		// ********************************************************************************

		final CheckBox check = (CheckBox) findViewById(R.id.checkBox1);
		final CheckBox check2 = (CheckBox) findViewById(R.id.checkBox2);
		final CheckBox check3 = (CheckBox) findViewById(R.id.checkBox3);
		final CheckBox check4 = (CheckBox) findViewById(R.id.checkBox4);
		final CheckBox check5 = (CheckBox) findViewById(R.id.checkBox5);

		Button clickButton = (Button) findViewById(R.id.button1);
		clickButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				WeatherInfo cityInfo1 = null;
				WeatherInfo cityInfo2 = null;
				WeatherInfo cityInfo3 = null;
				WeatherInfo cityInfo4 = null;
				WeatherInfo cityInfo5 = null;
				autoCompView1.clearFocus();
				autoCompView2.clearFocus();
				autoCompView3.clearFocus();
				autoCompView4.clearFocus();
				autoCompView5.clearFocus();

				// TODO Auto-generated method stub
				if (!(check.isChecked())) {
					Toast.makeText(getApplicationContext(),
							"Select at least one City", Toast.LENGTH_SHORT).show();
				} else if (check2.isEnabled() && !(check2.isChecked())) {
					cityInfo1 = new WeatherInfo();
					String str = autoCompView1.getText().toString();
					String str1 = str.replace(" ", "%20");
					String weatherLocation[] = str1.split(",");
					foreCast(weatherLocation, cityInfo1);

				} else if (check3.isEnabled() && !(check3.isChecked())) {
					cityInfo1 = new WeatherInfo();
					cityInfo2 = new WeatherInfo();
					String str = autoCompView1.getText().toString();
					String str1 = str.replace(" ", "%20");
					String weatherLocation[] = str1.split(",");
					foreCast(weatherLocation, cityInfo1);

					str = autoCompView2.getText().toString();
					str1 = str.replace(" ", "%20");
					weatherLocation = str1.split(",");
					foreCast(weatherLocation, cityInfo2);

				}

				else if (check4.isEnabled() && !(check4.isChecked())) {
					cityInfo1 = new WeatherInfo();
					cityInfo2 = new WeatherInfo();
					cityInfo3 = new WeatherInfo();
					String str = autoCompView1.getText().toString();
					String str1 = str.replace(" ", "%20");
					String weatherLocation[] = str1.split(",");
					foreCast(weatherLocation, cityInfo1);

					str = autoCompView2.getText().toString();
					str1 = str.replace(" ", "%20");
					weatherLocation = str1.split(",");
					foreCast(weatherLocation, cityInfo2);

					str = autoCompView3.getText().toString();
					str1 = str.replace(" ", "%20");
					weatherLocation = str1.split(",");
					foreCast(weatherLocation, cityInfo3);

				}

				else if (check5.isEnabled() && !(check5.isChecked())) {
					cityInfo1 = new WeatherInfo();
					cityInfo2 = new WeatherInfo();
					cityInfo3 = new WeatherInfo();
					cityInfo4 = new WeatherInfo();
					String str = autoCompView1.getText().toString();
					String str1 = str.replace(" ", "%20");
					String weatherLocation[] = str1.split(",");
					foreCast(weatherLocation, cityInfo1);

					str = autoCompView2.getText().toString();
					str1 = str.replace(" ", "%20");
					weatherLocation = str1.split(",");
					foreCast(weatherLocation, cityInfo2);

					str = autoCompView3.getText().toString();
					str1 = str.replace(" ", "%20");
					weatherLocation = str1.split(",");
					foreCast(weatherLocation, cityInfo3);

					str = autoCompView4.getText().toString();
					str1 = str.replace(" ", "%20");
					weatherLocation = str1.split(",");
					foreCast(weatherLocation, cityInfo4);

				} else if (check5.isEnabled() && (check5.isChecked())) {
					cityInfo1 = new WeatherInfo();
					cityInfo2 = new WeatherInfo();
					cityInfo3 = new WeatherInfo();
					cityInfo4 = new WeatherInfo();
					cityInfo5 = new WeatherInfo();
					String str = autoCompView1.getText().toString();
					String str1 = str.replace(" ", "%20");
					String weatherLocation[] = str1.split(",");
					foreCast(weatherLocation, cityInfo1);

					str = autoCompView2.getText().toString();
					str1 = str.replace(" ", "%20");
					weatherLocation = str1.split(",");
					foreCast(weatherLocation, cityInfo2);

					str = autoCompView3.getText().toString();
					str1 = str.replace(" ", "%20");
					weatherLocation = str1.split(",");
					foreCast(weatherLocation, cityInfo3);

					str = autoCompView4.getText().toString();
					str1 = str.replace(" ", "%20");
					weatherLocation = str1.split(",");
					foreCast(weatherLocation, cityInfo4);

					str = autoCompView5.getText().toString();
					str1 = str.replace(" ", "%20");
					weatherLocation = str1.split(",");
					foreCast(weatherLocation, cityInfo5);

				} else {

					Toast.makeText(getApplicationContext(),
							"Invalid Input.  Please Try Again.",
							Toast.LENGTH_SHORT).show();

				}

				// Intent intent = new
				// Intent(WunderGround.this,MapDisplay.class);
				// startActivity(intent);

			}

			private void foreCast(final String string[],
					final WeatherInfo cityInfo) {
				Thread thread = new Thread() {
					@Override
					public void run() {
						if (string.length == 1 && !(string.equals(""))) {

							runOnUiThread(new Runnable() {
								public void run() {
									String str = "Choose a entry in the format (City, State)";
									Toast.makeText(getApplicationContext(),
											str, Toast.LENGTH_SHORT).show();
								}
							});

						}
						if (string.length >= 2 && !(string.equals(""))) {
							WeatherProcessing.getForecast(string[1], string[0],
									cityInfo);
						}

					}
				};

				thread.start();

			}

		});

		// Integrating the Checkboxes and text fields
		// ********************************************************************************

		check2.setVisibility(View.INVISIBLE);
		check3.setVisibility(View.INVISIBLE);
		check4.setVisibility(View.INVISIBLE);
		check5.setVisibility(View.INVISIBLE);

		check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					autoCompView1.setVisibility(View.VISIBLE);
					check2.setVisibility(View.VISIBLE);
				} else {
					autoCompView1.setVisibility(View.INVISIBLE);
					check2.setVisibility(View.INVISIBLE);
				}
			}
		});

		check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					autoCompView2.setVisibility(View.VISIBLE);
					check3.setVisibility(View.VISIBLE);
					check.setEnabled(false);
				} else {
					autoCompView2.setVisibility(View.INVISIBLE);
					check3.setVisibility(View.INVISIBLE);
					check.setEnabled(true);
				}
			}
		});

		check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					autoCompView3.setVisibility(View.VISIBLE);
					check2.setEnabled(false);
					check4.setVisibility(View.VISIBLE);
				} else {
					autoCompView3.setVisibility(View.INVISIBLE);
					check2.setEnabled(true);
					check4.setVisibility(View.INVISIBLE);
				}
			}
		});

		check4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					autoCompView4.setVisibility(View.VISIBLE);
					check3.setEnabled(false);
					check5.setVisibility(View.VISIBLE);
				} else {
					autoCompView4.setVisibility(View.INVISIBLE);
					check3.setEnabled(true);
					check5.setVisibility(View.INVISIBLE);
				}
			}
		});

		check5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					autoCompView5.setVisibility(View.VISIBLE);
					check4.setEnabled(false);
				} else {
					autoCompView5.setVisibility(View.INVISIBLE);
					check4.setEnabled(true);
				}
			}
		});

		// ********************************************************************************

	}

	public void setValidWords(ArrayList<String> list) {
		validWords = list;
	}

	class Validator implements AutoCompleteTextView.Validator {

		@Override
		public boolean isValid(CharSequence text) {
			Log.v("Test", "Checking if valid: " + text);
			for (String s : validWords)
				if (s.toString()
						.toLowerCase(Locale.getDefault())
						.equals(text.toString()
								.toLowerCase(Locale.getDefault()))) {
					concreteWords.add(text.toString());
					return true;
				}
			for (String s : concreteWords)
				if (s.toString()
						.toLowerCase(Locale.getDefault())
						.equals(text.toString()
								.toLowerCase(Locale.getDefault()))) {
					return true;

				}
			return false;
		}

		@Override
		public CharSequence fixText(CharSequence invalidText) {
			Log.v("Test", "Returning fixed text");

			/*
			 * I'm just returning an empty string here, so the field will be
			 * blanked, but you could put any kind of action here, like popping
			 * up a dialog?
			 * 
			 * Whatever value you return here must be in the list of valid
			 * words.
			 */
			return "";
		}
	}

	class FocusListener implements View.OnFocusChangeListener {

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			Log.v("Test", "Focus changed");
			if (v.getId() == R.id.city1 && !hasFocus) {
				Log.v("Test", "Performing validation");
				((AutoCompleteTextView) v).performValidation();
			}
		}
	}
	
	class KeyListener implements OnKeyListener
	{
		private AutoCompleteTextView text;
		
		public KeyListener(AutoCompleteTextView t){
			this.text = t;
		}
	     /**
	      * This listens for the user to press the enter button on 
	      * the keyboard and then hides the virtual keyboard
	      */
		@Override
		public boolean onKey(View arg0, int arg1, KeyEvent event) {
	        // If the event is a key-down event on the "enter" button
	        if ( (event.getAction() == KeyEvent.ACTION_DOWN  ) &&
	             (arg1           == KeyEvent.KEYCODE_ENTER)   )
	        {               
	        	InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
	                imm.hideSoftInputFromWindow(text.getWindowToken(), 0);   
	                return true;
	        }
	        return false;
	     }
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

}
