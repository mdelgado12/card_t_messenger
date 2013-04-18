package com.ss.tmessanger.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.ss.tmessanger.R;

public class LanguageSelectFragment extends PreferenceFragment{

	View mBaseView;
	ExpandableListView mLanguageList;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mBaseView = inflater.inflate(R.layout.language_selection, null, false);
		
		mLanguageList = (ExpandableListView) mBaseView.findViewById(R.id.languageList);
		return mBaseView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		populateExpandableList();
	}
	
	public void populateExpandableList() {
		
	}
}
