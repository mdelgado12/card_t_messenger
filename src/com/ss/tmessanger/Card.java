package com.ss.tmessanger;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Card extends LinearLayout {

	private Context mContext;
	
	private ImageView 	mImageView;
	private TextView	mHeadlineView;
	private TextView 	mBodyTextView;
	
	public static Card newInstance(Activity activity) {
		return (Card)activity.getLayoutInflater().inflate(R.layout.card_template, null);
	}
	
	public Card(Context context, AttributeSet attrSet) {
		super(context, attrSet);
		mContext = context;
		mImageView = new ImageView(mContext);
		mImageView.setImageResource(R.drawable.ic_launcher);
		mHeadlineView = new TextView(mContext);
		mBodyTextView = new TextView(mContext);

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		mImageView.setLayoutParams(params);
		mHeadlineView.setLayoutParams(params);
		mBodyTextView.setLayoutParams(params);
		mHeadlineView.setTextAppearance(mContext, R.style.nameTextStyle);
		mBodyTextView.setTextAppearance(mContext, R.style.textCardStyle);
		
		setImageFromResource(R.drawable.ic_launcher);
		setHeadline("This Me");
		setBodyText("This is my world now");
		
		
		this.addView(mImageView);
		this.addView(mHeadlineView);
		this.addView(mBodyTextView);
	}
	
	public Card(Context context) {
		super(context, null, R.style.nowCardStyle);
		
		mContext = context;
		mImageView = new ImageView(mContext);
		mImageView.setImageResource(R.drawable.ic_launcher);
		mHeadlineView = new TextView(mContext);
		mBodyTextView = new TextView(mContext);

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		mImageView.setLayoutParams(params);mImageView.setScaleType(ScaleType.FIT_CENTER);
		mHeadlineView.setLayoutParams(params);
		mBodyTextView.setLayoutParams(params);
		mHeadlineView.setTextAppearance(mContext, R.style.nameTextStyle);
		mBodyTextView.setTextAppearance(mContext, R.style.textCardStyle);
		
		setImageFromResource(R.drawable.ic_launcher);
		setHeadline("This Me");
		setBodyText("This is my world now");
		
		
		this.addView(mImageView);
		this.addView(mHeadlineView);
		this.addView(mBodyTextView);
	}
	
	public void setImageFromResource(int imageId) {
		mImageView.setImageResource(imageId);
	}
	public void setImageFromBitmap(Bitmap bitmap) {
		mImageView.setImageBitmap(bitmap);
	}
	public void setImageFromDrawable(Drawable imageDrawable) {
		mImageView.setImageDrawable(imageDrawable);
	}
	
	public void setHeadline(String headline) {
		mHeadlineView.setText(headline);
	}

	public void setBodyText(String bodyText) {
		mBodyTextView.setText(bodyText);
	}
}
