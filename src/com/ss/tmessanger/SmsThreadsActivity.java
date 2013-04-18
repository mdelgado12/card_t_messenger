package com.ss.tmessanger;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.PhoneLookup;
import android.provider.ContactsContract.Profile;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ss.tmessanger.settings.SettingsPreference;

public class SmsThreadsActivity extends Activity {

	CardLayout mCards;
	String[] mMessage;
	private String[] mProjection;
	private Cursor mProfileCursor;
	private String mDisplayName;
	private Bitmap mBitmap;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_threads);
        
        Card card = Card.newInstance(this);
        mCards = (CardLayout)findViewById(R.id.cardLayout);
        mCards.addView(card);
        mMessage = new String[] { "address", "_id", "body", "type", "date", "read", "thread_id" };
        getAllMessages();
        

        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sms_threads, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if(item.getItemId() == R.id.menu_settings) {
    		Intent intent = new Intent(this, SettingsPreference.class);
    		startActivity(intent);
    	}
    	return true;
    }
    
    public void getAllMessages() {

        Uri messageUri = Uri.parse("content://sms");
        Cursor cursor = getContentResolver().query(messageUri, mMessage, null, null, "date DESC");
        while(cursor.moveToNext()) {
        	
        	Card card = Card.newInstance(this);
        	getContactsByPhone(cursor.getString(0));
        	card.setHeadline(cursor.getString(0));
        	card.setBodyText(cursor.getString(2));
        	
        	if(mBitmap!=null) {
        		card.setHeadline(mDisplayName);
        		card.setImageFromBitmap(mBitmap);
        	}
        	mCards.addView(card);
        	
        }
    }

	public int getContactIDFromNumber(String contactNumber) {
        
		contactNumber = Uri.encode(contactNumber);
		int phoneContactID = -1;
		String displayName = "";
		Bitmap bitmap;
		long imageId = -1;
		Cursor contactLookupCursor = this.getContentResolver().query(
				Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
						Uri.encode(contactNumber)),
				new String[] { PhoneLookup.DISPLAY_NAME, PhoneLookup._ID , PhoneLookup.PHOTO_ID},
				null, null, null);
		
		while (contactLookupCursor.moveToNext()) {
			phoneContactID = contactLookupCursor.getInt(contactLookupCursor
					.getColumnIndexOrThrow(PhoneLookup._ID));
			displayName = contactLookupCursor.getString(contactLookupCursor
					.getColumnIndexOrThrow(PhoneLookup.DISPLAY_NAME));
			imageId =  contactLookupCursor.getInt(contactLookupCursor
					.getColumnIndexOrThrow(PhoneLookup.PHOTO_ID));
			
			bitmap = QuickContactHelper.loadContactPhoto(getContentResolver(), phoneContactID, imageId);

			mDisplayName = displayName;
			mBitmap = bitmap;
			if(phoneContactID >= 0)
				break;
		}
		
		contactLookupCursor.close();

		return phoneContactID;
	}

	public void getContactsByPhone(String phoneNumber) {		

		int cId = getContactIDFromNumber(phoneNumber);
		if (cId < 0) {
			return;
		}

		mProjection = new String[] { Profile._ID, Profile.DISPLAY_NAME_PRIMARY,
				Profile.LOOKUP_KEY, Profile.PHOTO_THUMBNAIL_URI, };

		// Retrieves the profile from the Contacts Provider
		mProfileCursor = getContentResolver().query(Profile.CONTENT_URI,
				mProjection, null,
				null, null);
		
		while (mProfileCursor.moveToNext()) {

			String myString = mProfileCursor.getString(0);
			Log.d("Name", myString.toString());
		}

		mProfileCursor.close();
    }
}
