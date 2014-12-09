package com.wunderground;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
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

	protected static String myKey1 = null;
	protected static String myKey2 = null;
	protected static String myKey3 = null;
	protected static String myKey4 = null;
	protected static String myKey5 = null;
	private AutoCompleteTextView autoCompView1;
	private AutoCompleteTextView autoCompView2;
	private AutoCompleteTextView autoCompView3;
	private AutoCompleteTextView autoCompView4;
	private AutoCompleteTextView autoCompView5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wunder_ground);

		// Setting the adapters for the textboxes using Wundergrounds
		// autocomplete api
		//
		// ********************************************************************************

		autoCompView1 = (AutoCompleteTextView) findViewById(R.id.city1);
		autoCompView1.setAdapter(new GetPlaces(this, R.layout.text_file));
		autoCompView1.setOnItemClickListener(this);
		autoCompView1.addTextChangedListener(new Validation("myKey1"));

		autoCompView2 = (AutoCompleteTextView) findViewById(R.id.city2);
		autoCompView2.setAdapter(new GetPlaces(this, R.layout.text_file));
		autoCompView2.setOnItemClickListener(this);
		autoCompView2.addTextChangedListener(new Validation("myKey2"));

		autoCompView3 = (AutoCompleteTextView) findViewById(R.id.city3);
		autoCompView3.setAdapter(new GetPlaces(this, R.layout.text_file));
		autoCompView3.setOnItemClickListener(this);
		autoCompView3.addTextChangedListener(new Validation("myKey3"));
		autoCompView4 = (AutoCompleteTextView) findViewById(R.id.city4);
		autoCompView4.setAdapter(new GetPlaces(this, R.layout.text_file));
		autoCompView4.setOnItemClickListener(this);
		autoCompView4.addTextChangedListener(new Validation("myKey4"));
		autoCompView5 = (AutoCompleteTextView) findViewById(R.id.city5);
		autoCompView5.setAdapter(new GetPlaces(this, R.layout.text_file));
		autoCompView5.setOnItemClickListener(this);
		autoCompView5.addTextChangedListener(new Validation("myKey5"));

		// ********************************************************************************

		final CheckBox check = (CheckBox) findViewById(R.id.checkBox1);
		final CheckBox check2 = (CheckBox) findViewById(R.id.checkBox2);
		final CheckBox check3 = (CheckBox) findViewById(R.id.checkBox3);
		final CheckBox check4 = (CheckBox) findViewById(R.id.checkBox4);
		final CheckBox check5 = (CheckBox) findViewById(R.id.checkBox5);

		autoCompView1.setVisibility(View.INVISIBLE);
		autoCompView1.setThreshold(1);
		autoCompView2.setVisibility(View.INVISIBLE);
		autoCompView2.setThreshold(1);
		autoCompView3.setVisibility(View.INVISIBLE);
		autoCompView3.setThreshold(1);
		autoCompView4.setVisibility(View.INVISIBLE);
		autoCompView4.setThreshold(1);
		autoCompView5.setVisibility(View.INVISIBLE);
		autoCompView5.setThreshold(1);

		Button clickButton = (Button) findViewById(R.id.button1);
		clickButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				WeatherInfo cityInfo1 = null;
				WeatherInfo cityInfo2 = null;
				WeatherInfo cityInfo3 = null;
				WeatherInfo cityInfo4 = null;
				WeatherInfo cityInfo5 = null;

				// TODO Auto-generated method stub
				if (check2.isEnabled() && !(check2.isChecked())
						&& myKey1 != null) {
					Log.d("MY KEY ", myKey1);
					cityInfo1 = new WeatherInfo();
					String str = autoCompView1.getText().toString();
					String str1 = str.replace(" ", "%20");
					String weatherLocation[] = str1.split(",");
					foreCast(weatherLocation, cityInfo1);

				} else if (check3.isEnabled() && !(check3.isChecked())
						&& myKey1 != null && myKey2 != null) {
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

				else if (check4.isEnabled() && !(check4.isChecked())
						&& myKey1 != null && myKey2 != null && myKey3 != null) {
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

				else if (check5.isEnabled() && !(check5.isChecked())
						&& myKey1 != null && myKey2 != null && myKey3 != null
						&& myKey4 != null) {
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

				} else if (check5.isEnabled() && (check5.isChecked())
						&& myKey1 != null && myKey2 != null && myKey3 != null
						&& myKey4 != null && myKey5 != null) {
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

			}

			private void foreCast(final String string[],
					final WeatherInfo cityInfo) {
				Thread thread = new Thread() {
					@Override
					public void run() {
						if (string.length == 1 && !(string.equals(""))) {

							runOnUiThread(new Runnable() {
								public void run() {
									String str = "Choose a entry in the formate (City, State)";
									Toast.makeText(getApplicationContext(),
											str, Toast.LENGTH_LONG).show();
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

		if (autoCompView1.isFocused()) {
			myKey1 = (String) parent.getItemAtPosition(position);
		} else if (autoCompView2.isFocused()) {
			myKey2 = (String) parent.getItemAtPosition(position);
		} else if (autoCompView3.isFocused()) {
			myKey3 = (String) parent.getItemAtPosition(position);
		} else if (autoCompView4.isFocused()) {
			myKey4 = (String) parent.getItemAtPosition(position);
		} else if (autoCompView5.isFocused()) {
			myKey5 = (String) parent.getItemAtPosition(position);
		}

	}
}
