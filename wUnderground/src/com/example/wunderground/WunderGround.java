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
	private WeatherInfo[] cityInfo;
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

			@Override
			public void onClick(View v) {

				cityInfo = new WeatherInfo[5];
				for (int i = 0; i < 5; i++) {
					cityInfo[i] = new WeatherInfo();
				}
				// Validate Textbox by changing focus
				for (int i = 0; i < 5; i++) {
					autoCompView[i].clearFocus();
				}

				// TODO Auto-generated method stub
				if (!(check[0].isChecked())) {
					Toast.makeText(getApplicationContext(),
							"Select at least one City", Toast.LENGTH_SHORT)
							.show();
				} else if (check[1].isEnabled() && !(check[1].isChecked())) {
					autoCompView[0].requestFocus();
					if (!(autoCompView[0].equals(0)) && autoCompView[0] != null)
						prepareForecast(1);

				} else if (check[2].isEnabled() && !(check[2].isChecked())) {
					autoCompView[1].requestFocus();
					if (!(autoCompView[0].equals(0))
							&& !(autoCompView[1].equals(0)))
						prepareForecast(2);
				}

				else if (check[3].isEnabled() && !(check[3].isChecked())) {
					autoCompView[2].requestFocus();
					if (autoCompView[0] != null && autoCompView[1] != null
							&& autoCompView[2] != null)
						prepareForecast(3);

				}

				else if (check[4].isEnabled() && !(check[4].isChecked())) {
					autoCompView[3].requestFocus();
					if (autoCompView[0] != null && autoCompView[1] != null
							&& autoCompView[3] != null
							&& autoCompView[4] != null)
						prepareForecast(4);

				} else if (check[4].isEnabled() && (check[4].isChecked())) {
					autoCompView[4].requestFocus();
					if (autoCompView[0] != null && autoCompView[1] != null
							&& autoCompView[2] != null
							&& autoCompView[3] != null
							&& autoCompView[4] != null)
						prepareForecast(5);

				} else {

					Toast.makeText(getApplicationContext(),
							"Invalid Input.  Please Try Again.",
							Toast.LENGTH_SHORT).show();

				}

				Intent intent = new Intent(WunderGround.this, MapDisplay.class);
				startActivity(intent);

			}

			private void prepareForecast(int c) {
				for (int i = 0; i < c; i++) {
					String str = autoCompView[i].getText().toString();
					String str1 = str.replace(" ", "%20");
					String weatherLocation[] = str1.split(",");
					foreCast(weatherLocation, cityInfo[i]);
				}
			}

			private void foreCast(final String string[],
					final WeatherInfo cityInfo) {
				Thread thread = new Thread() {
					@Override
					public void run() {
						if (string.length == 1) {

							runOnUiThread(new Runnable() {
								public void run() {
									String str = "Make sure you use the format (City, State)";
									Toast.makeText(getApplicationContext(),
											str, Toast.LENGTH_SHORT).show();
								}
							});

						}
						if (string.length >= 2) {
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

		check[0].setOnCheckedChangeListener(new CheckListener());
		for (int i = 1; i < 5; i++) {
			check[i].setVisibility(View.INVISIBLE);
			check[i].setOnCheckedChangeListener(new CheckListener());
		}

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

			Toast.makeText(getApplicationContext(),
					"Please enter a valid city", Toast.LENGTH_SHORT).show();
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

	class KeyListener implements OnKeyListener {
		private AutoCompleteTextView text;

		public KeyListener(AutoCompleteTextView t) {
			this.text = t;
		}

		/**
		 * This listens for the user to press the enter button on the keyboard
		 * and then hides the virtual keyboard
		 */
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
						autoCompView[i - 1].requestFocus();
						if (i != 0) {
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
