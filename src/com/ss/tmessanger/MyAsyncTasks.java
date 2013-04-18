package com.ss.tmessanger;

import android.os.AsyncTask;

public class MyAsyncTasks extends AsyncTask<String, Integer, Boolean>
{
	@Override
	protected Boolean doInBackground(String... params) {

		return true;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		
		
	}
}
