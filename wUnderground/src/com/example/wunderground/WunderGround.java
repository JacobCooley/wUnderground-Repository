/**
 * Sample Android Application built for Ideasbynature interview
 * 
 * This is the Main class and handles most of the UI functions
 *
 * @author Jacob Cooley
 */

package com.example.wunderground;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.wunderground.R;

public class WunderGround extends Activity {

	private AutoCompleteTextView[] autoCompView;
	private CheckBox[] check;
	private static WeatherInfo[] cityInfo;
	private ArrayList<String> validWords, concreteWords;
	private Intent intent;
	private static boolean validObject = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wunder_ground);
		intent = new Intent(this, MapDisplay.class);

		// Setting the adapters for the textboxes using Wundergrounds
		// autocomplete api
		//
		// ********************************************************************************
		validWords = new ArrayList<String>();
		concreteWords = new ArrayList<String>();

		autoCompView = new AutoCompleteTextView[5];
		autoCompView[0] = (AutoCompleteTextView) findViewById(R.id.city1);
		autoCompView[1] = (AutoCompleteTextView) findViewById(R.id.city2);
		autoCompView[2] = (AutoCompleteTextView) findViewById(R.id.city3);
		autoCompView[3] = (AutoCompleteTextView) findViewById(R.id.city4);
		autoCompView[4] = (AutoCompleteTextView) findViewById(R.id.city5);

		for (int i = 0; i < 5; i++) {
			autoCompView[i].setAdapter(new GetPlaces(this, R.layout.text_file));
			autoCompView[i].setValidator(new Validator());
			autoCompView[i].setVisibility(View.INVISIBLE);
			autoCompView[i].setThreshold(1);
			autoCompView[i].setOnKeyListener(new KeyListener(autoCompView[i]));
		}

		// ********************************************************************************

		check = new CheckBox[5];
		check[0] = (CheckBox) findViewById(R.id.checkBox1);
		check[1] = (CheckBox) findViewById(R.id.checkBox2);
		check[2] = (CheckBox) findViewById(R.id.checkBox3);
		check[3] = (CheckBox) findViewById(R.id.checkBox4);
		check[4] = (CheckBox) findViewById(R.id.checkBox5);

		Button clickButton = (Button) findViewById(R.id.button1);
		clickButton.setOnClickListener(new OnClickListener() {

			/**
			 * Opens the map and transfers objects once button is clicked
			 */
			@Override
			public void onClick(View v) {

				cityInfo = new WeatherInfo[5];
				// Validate Textbox by changing focus
				for (int i = 0; i < 5; i++) {
					autoCompView[i].clearFocus();
				}

				try {
					// TODO Auto-generated method stub
					if (!(check[0].isChecked())) {
						Toast.makeText(getApplicationContext(),
								"Select at least one City", Toast.LENGTH_SHORT)
								.show();
					} else if (check[1].isEnabled() && !(check[1].isChecked())) {
						autoCompView[0].requestFocus();

						if (!(autoCompView[0].getText().toString().equals(""))) {

							cityInfo[0] = new WeatherInfo();
							prepareForecast(1);
							if (validObject == true) {
								intent.putExtra("number", "1");
								intent.putExtra("cityData", cityInfo[0]);
								startMap();
							}
						} else {
							Toast.makeText(getApplicationContext(),
									"Invalid Input.  Please Try Again.",
									Toast.LENGTH_SHORT).show();
						}

					} else if (check[2].isEnabled() && !(check[2].isChecked())) {

						autoCompView[1].requestFocus();
						if (!(autoCompView[0].getText().toString().equals(""))
								&& !(autoCompView[1].getText().toString()
										.equals(""))) {

							for (int i = 0; i < 2; i++) {
								cityInfo[i] = new WeatherInfo();
							}

							prepareForecast(2);
							if (validObject == true) {
								intent.putExtra("number", "2");
								intent.putExtra("cityData", cityInfo[0]);
								intent.putExtra("cityData1", cityInfo[1]);
								startMap();
							}
						} else {
							Toast.makeText(getApplicationContext(),
									"Invalid Input.  Please Try Again.",
									Toast.LENGTH_SHORT).show();
						}
					}

					else if (check[3].isEnabled() && !(check[3].isChecked())) {

						autoCompView[2].requestFocus();
						if (!(autoCompView[0].getText().toString().equals(""))
								&& !(autoCompView[1].getText().toString()
										.equals(""))
								&& !(autoCompView[2].getText().toString()
										.equals(""))) {
							for (int i = 0; i < 3; i++) {
								cityInfo[i] = new WeatherInfo();
							}

							prepareForecast(3);
							if (validObject == true) {
								intent.putExtra("number", "3");
								intent.putExtra("cityData", cityInfo[0]);
								intent.putExtra("cityData1", cityInfo[1]);
								intent.putExtra("cityData2", cityInfo[2]);
								startMap();
							}
						} else {
							Toast.makeText(getApplicationContext(),
									"Invalid Input.  Please Try Again.",
									Toast.LENGTH_SHORT).show();
						}
					}

					else if (check[4].isEnabled() && !(check[4].isChecked())) {
						autoCompView[3].requestFocus();

						if (!(autoCompView[0].getText().toString().equals(""))
								&& !(autoCompView[1].getText().toString()
										.equals(""))
								&& !(autoCompView[2].getText().toString()
										.equals(""))
								&& !(autoCompView[3].getText().toString()
										.equals(""))) {

							for (int i = 0; i < 4; i++) {
								cityInfo[i] = new WeatherInfo();
							}

							prepareForecast(4);
							if (validObject == true) {
								intent.putExtra("number", "4");
								intent.putExtra("cityData", cityInfo[0]);
								intent.putExtra("cityData1", cityInfo[1]);
								intent.putExtra("cityData2", cityInfo[2]);
								intent.putExtra("cityData3", cityInfo[3]);
								startMap();
							}
						} else {

							Toast.makeText(getApplicationContext(),
									"Invalid Input.  Please Try Again.",
									Toast.LENGTH_SHORT).show();
						}

					} else if (check[4].isEnabled() && (check[4].isChecked())) {
						autoCompView[4].requestFocus();
						if (!(autoCompView[0].getText().toString().equals(""))
								&& !(autoCompView[1].getText().toString()
										.equals(""))
								&& !(autoCompView[2].getText().toString()
										.equals(""))
								&& !(autoCompView[3].getText().toString()
										.equals(""))
								&& !(autoCompView[4].getText().toString()
										.equals(""))) {
							for (int i = 0; i < 5; i++) {
								cityInfo[i] = new WeatherInfo();
							}

							prepareForecast(5);
							if (validObject == true) {
								intent.putExtra("number", "5");
								intent.putExtra("cityData", cityInfo[0]);
								intent.putExtra("cityData1", cityInfo[1]);
								intent.putExtra("cityData2", cityInfo[2]);
								intent.putExtra("cityData3", cityInfo[3]);
								intent.putExtra("cityData4", cityInfo[4]);
								startMap();
							}
						} else {

							Toast.makeText(getApplicationContext(),
									"Invalid Input.  Please Try Again.",
									Toast.LENGTH_SHORT).show();
						}
					} else {

						Toast.makeText(getApplicationContext(),
								"Invalid Input.  Please Try Again.",
								Toast.LENGTH_SHORT).show();
					}
				} catch (NullPointerException e) {
					Log.e("Error..", "Nothing in the weatherinfo object");
					e.printStackTrace();
				}

			}

			/**
			 * Starts the map
			 */

			private void startMap() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				startActivity(intent);
			}

			/**
			 * prepare data for forecast
			 */

			private void prepareForecast(int c) {
				for (int i = 0; i < c; i++) {
					String str = autoCompView[i].getText().toString();
					String str1 = str.replace(" ", "%20");
					String weatherLocation[] = str1.split(",");

					getObject o = new getObject(weatherLocation, cityInfo[i], i);
					new Thread(o).start();
					try {
						o.join();
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}

			/**
			 * Runs in a background thread to set the weather data into the
			 * WeatherInfo object
			 * 
			 */

			class getObject extends Thread {
				private String[] string = null;
				private WeatherInfo city = null;
				private int num = 0;

				public getObject(String[] s, WeatherInfo c, int i) {
					this.city = c;
					this.string = s;
					this.num = i;
				}

				@Override
				public void run() {
					// TODO Auto-generated method stub
					if (string.length == 1) {

						WunderGround.validObject = false;

						runOnUiThread(new Runnable() {
							public void run() {
								String str = "You must use the format City, State!";
								Toast.makeText(getApplicationContext(), str,
										Toast.LENGTH_SHORT).show();
							}
						});

					}
					if (string.length >= 2) {
						city = WeatherProcessing.getForecast(string[1],
								string[0], city);
						cityInfo[num] = city;
					}

				}

			}

		});

		// Integrating the Checkboxes and text fields
		// ********************************************************************************

		check[0].setOnCheckedChangeListener(new CheckListener());
		for (int i = 1; i < 5; i++) {
			check[i].setVisibility(View.INVISIBLE);
			check[i].setOnCheckedChangeListener(new CheckListener());
		}

		// ********************************************************************************

	}

	/**
	 * Set valid words for GetPlaces validation
	 */
	public void setValidWords(ArrayList<String> list) {
		validWords = list;
	}

	/**
	 * Validator to implement the GetPlaces validation
	 */
	class Validator implements AutoCompleteTextView.Validator {

		@Override
		public boolean isValid(CharSequence text) {
			Log.v("Test", "Checking if valid: " + text);
			for (String s : validWords)
				if (s.toString()
						.toLowerCase(Locale.getDefault())
						.trim()
						.equals(text.toString()
								.toLowerCase(Locale.getDefault()).trim())) {
					concreteWords.add(text.toString());
					return true;
				}
			for (String s : concreteWords)
				if (s.toString()
						.toLowerCase(Locale.getDefault())
						.trim()
						.equals(text.toString()
								.toLowerCase(Locale.getDefault()).trim())) {
					return true;

				}
			return false;
		}

		@Override
		public CharSequence fixText(CharSequence invalidText) {
			Log.v("Test", "Returning fixed text");

			Toast.makeText(getApplicationContext(),
					"Please enter a valid city", Toast.LENGTH_SHORT).show();
			return "";
		}
	}

	/**
	 * Listener to validate every time focus is changed from the textviews
	 */

	class FocusListener implements View.OnFocusChangeListener {

		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			Log.v("Test", "Focus changed");
			if (v.getId() == R.id.city1 && !hasFocus
					&& ((AutoCompleteTextView) v) != null) {
				Log.v("Test", "Performing validation");
				((AutoCompleteTextView) v).performValidation();
			}
		}
	}

	/**
	 * KeyListener to close the keyboard when done is clicked
	 */
	class KeyListener implements OnKeyListener {
		private AutoCompleteTextView text;

		public KeyListener(AutoCompleteTextView t) {
			this.text = t;
		}

		@Override
		public boolean onKey(View arg0, int arg1, KeyEvent event) {
			// If the event is a key-down event on the "enter" button
			if ((event.getAction() == KeyEvent.ACTION_DOWN)
					&& (arg1 == KeyEvent.KEYCODE_ENTER)) {
				InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(text.getWindowToken(), 0);
				return true;
			}
			return false;
		}
	}

	/**
	 * Listener for checkbox being changed
	 */
	class CheckListener implements OnCheckedChangeListener {

		private CheckBox c;

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			c = (CheckBox) buttonView;
			for (int i = 0; i < 4; i++) {
				if (c == check[i]) {
					if (isChecked) {
						autoCompView[i].setVisibility(View.VISIBLE);
						autoCompView[i].requestFocus();
						check[i + 1].setVisibility(View.VISIBLE);
						if (i != 0) {
							check[i - 1].setEnabled(false);
						}
					} else {
						autoCompView[i].setVisibility(View.INVISIBLE);
						check[i + 1].setVisibility(View.INVISIBLE);
						if (i != 0) {
							autoCompView[i - 1].requestFocus();
							check[i - 1].setEnabled(true);
						}
					}
				}
			}
			if (c == check[4]) {
				if (isChecked) {
					autoCompView[4].setVisibility(View.VISIBLE);
					autoCompView[4].requestFocus();
					check[3].setEnabled(false);
				} else {
					autoCompView[4].setVisibility(View.INVISIBLE);
					autoCompView[3].requestFocus();
					check[3].setEnabled(true);
				}
			}
		}
	};

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
