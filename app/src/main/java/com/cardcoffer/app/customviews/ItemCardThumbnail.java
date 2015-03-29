package com.cardcoffer.app.customviews;

import com.cardcoffer.app.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

//TODO rethink the size of logo, padding and margin of the whole frame (the logo intersects the frame)

public class ItemCardThumbnail extends LinearLayout implements View.OnLongClickListener {

    public TextView tvName, tvJobTitle, tvAffiliation;

    public ItemCardThumbnail(Context context) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.item_card_thumbnail, this);

        findViews();
        setOnLongClickListener(this);

    }


    public ItemCardThumbnail(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.item_card_thumbnail, this);

        findViews();
        setOnLongClickListener(this);
    }



    private void findViews() {
        tvName = (TextView) findViewById(R.id.card_thumbnail_name);
        tvAffiliation = (TextView) findViewById(R.id.card_thumbnail_affiliation);
        tvJobTitle = (TextView) findViewById(R.id.card_thumbnail_jobTitle);

    }

    @Override
    public boolean onLongClick(View view) {
        SharedPreferences dataSP = getContext().getSharedPreferences("dataSP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = dataSP.edit();
        String objectID = getTag(R.id.TAG_CARD_THUMB_OBJECT_ID).toString();
        editor.putString("mainCard", objectID);
        editor.commit();
        Toast.makeText(getContext(), "Main card has been set: " + tvJobTitle.getText() + " at " + tvAffiliation.getText(), Toast.LENGTH_SHORT).show();
        return false;
    }
}
