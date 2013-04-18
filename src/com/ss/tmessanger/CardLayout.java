package com.ss.tmessanger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class CardLayout extends LinearLayout implements OnGlobalLayoutListener {

	private Context mContext;

	public CardLayout(Context context) {
		super(context);
		mContext = context;
		initGlobalLayout();
	}
	
	public CardLayout(Context context, AttributeSet attrSet) {
		super(context, attrSet);
		mContext = context;
		initGlobalLayout();
	}

	private void initGlobalLayout() {
		getViewTreeObserver().addOnGlobalLayoutListener(this);

	}

	@SuppressLint("NewApi")
	public void onGlobalLayout() {
		getViewTreeObserver().removeOnGlobalLayoutListener(this);

		final int heightPx = mContext.getResources().getDisplayMetrics().heightPixels;

		boolean inversed = false;
		final int childCount = getChildCount();

		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);

			int[] location = new int[2];

			child.getLocationOnScreen(location);

			if (location[1] > heightPx) {
				break;
			}

			if (!inversed) {
				child.startAnimation(AnimationUtils.loadAnimation(mContext,
						R.anim.animation_left));
			} else {
				child.startAnimation(AnimationUtils.loadAnimation(mContext,
						R.anim.animation_right));
			}

			inversed = !inversed;
		}

	}
}
