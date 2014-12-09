package com.wunderground;

import android.text.Editable;
import android.text.TextWatcher;

public class Validation extends WunderGround implements TextWatcher {

	private String whichKey;

	public Validation(final String key) {
		this.whichKey = key;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		if (whichKey == "myKey1") {
			WunderGround.myKey1 = null;
		} else if (whichKey == "myKey2") {
			WunderGround.myKey2 = null;
		} else if (whichKey == "myKey3") {
			WunderGround.myKey3 = null;
		} else if (whichKey == "myKey4") {
			WunderGround.myKey4 = null;
		} else if (whichKey == "myKey5") {
			WunderGround.myKey5 = null;
		}

	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub

	}

}
