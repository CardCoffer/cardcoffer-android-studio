package com.cardcoffer.app.customviews;

import com.cardcoffer.app.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

//TODO rethink the size of logo, padding and margin of the whole frame (the logo intersects the frame)

public class ItemCardThumbnail extends LinearLayout {

    public TextView tvName, tvJobTitle, tvAffiliation;

	public ItemCardThumbnail(Context context) {
		super(context);

		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.item_card_thumbnail, this);

        findViews();

	}


    public ItemCardThumbnail(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater inflater = LayoutInflater.from(context);
		inflater.inflate(R.layout.item_card_thumbnail, this);

        findViews();

	}


    private void findViews() {
        tvName = (TextView) findViewById(R.id.card_thumbnail_name);
        tvAffiliation = (TextView) findViewById(R.id.card_thumbnail_affiliation);
        tvJobTitle = (TextView) findViewById(R.id.card_thumbnail_jobTitle);
    }

}
