package com.cardcoffer.app.customviews;

import com.cardcoffer.app.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

//TODO rethink the size of logo, padding and margin of the whole frame (the logo intersects the frame)

public class ItemCardThumbnail extends LinearLayout {

	public ItemCardThumbnail(Context context) {
		super(context);

		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.item_card_thumbnail, this);

	}

	public ItemCardThumbnail(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.item_card_thumbnail, this);

	}

}
