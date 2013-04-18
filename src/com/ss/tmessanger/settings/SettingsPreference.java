package com.ss.tmessanger.settings;

import java.util.List;

import com.ss.tmessanger.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsPreference extends PreferenceActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onBuildHeaders(List<Header> target) {
		loadHeadersFromResource(R.xml.preference_headers, target);
	}
	
}
