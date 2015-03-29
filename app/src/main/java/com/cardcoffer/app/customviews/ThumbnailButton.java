package com.cardcoffer.app.customviews;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.widget.Button;

import com.cardcoffer.app.R;

/**
 * Created by sinash on 3/29/15.
 */
public class ThumbnailButton extends Button {
    public ThumbnailButton(Context context) {
        super(context);

        setBackgroundResource(R.drawable.curved_bg_thumb);
        setText("Add New Card!");
        setTag(R.id.TAG_VIEW_NAME, "ThumbnailButton");

    }

}
