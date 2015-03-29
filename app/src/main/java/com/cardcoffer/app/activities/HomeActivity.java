package com.cardcoffer.app.activities;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.cardcoffer.app.R;
import com.cardcoffer.app.R.id;
import com.cardcoffer.app.customviews.ItemCardThumbnail;
import com.parse.ParseUser;
import com.thedazzler.droidicon.IconicFontDrawable;
import com.thedazzler.droidicon.badges.DroidiconBadge;
import com.thedazzler.droidicon.typeface.CustomTypefaceHolder;
import com.thedazzler.droidicon.typeface.TypefaceManager;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sinash
 *
 */
public class HomeActivity extends Activity {

    LinearLayout llCardContainer;
    ImageButton btnCardCoffer;
    DroidiconBadge btnExchange, btnCheckIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        llCardContainer = (LinearLayout) findViewById(R.id.home_llCardContainer);

//        initCustomIcons();



        btnCardCoffer = (ImageButton) findViewById(R.id.btnHome_cardCoffer);
        btnCheckIn = (DroidiconBadge) findViewById(R.id.btnHome_checkIn);


        //btnExchange = (ImageButton) findViewById(R.id.btnHome_exchange);
        btnExchange = (DroidiconBadge) findViewById(id.btnHome_exchange);

        btnExchange.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                launchExchangeActivity();
            }
        });


        btnCheckIn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                launchCheckInActivity();

            }
        });

        btnCardCoffer.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                launchStacksActivity();

            }
        });

        if(ParseUser.getCurrentUser() == null){

            launchLoginActivity();

        }

    }

    private void launchExchangeActivity() {

        Intent intent  = new Intent(this, ExchangeActivity.class);
        startActivity(intent);

    }

    private void initCustomIcons() {


////Init your icons name-val mapping
//        Map<String, Integer> customMap = new HashMap<String, Integer>();
//        customMap.put("custom", 0xe600);
//
///**Create a CustomTypefaceHolder
//* The params in the constructor are:
//*  - Prefix name: the prefix you would like to use, make sure this one matches whatever you have in your name-val mapping.
//*  - The font file resource: e.g. Your font file is under res/raw/custom.ttf then put 'R.raw.custom' here.
//*  - Name-val map: the name-val mapping you created before.
//**/
//        CustomTypefaceHolder customTypefaceHolder = new CustomTypefaceHolder("custom", R.raw.custom, customMap);
//
////Add it to TypefaceManager.
//        TypefaceManager.getInstance().addNewTypefaceHolder(customTypefaceHolder);
//
////That's it! Use it in your app
//


    }

    protected void launchStacksActivity() {
        Intent intent = new Intent(this, StacksActivity.class);
        startActivity(intent);
    }

    private void launchLoginActivity() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    protected void launchCheckInActivity() {

        Intent intent = new Intent(this, CheckInActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_actionbar, menu);

        // actionbar searchview animation

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        int searchBarId = searchView.getContext().getResources().getIdentifier("android:id/search_bar", null, null);
        LinearLayout searchBar = (LinearLayout) searchView.findViewById(searchBarId);
        searchBar.setLayoutTransition(new LayoutTransition()); // TODO set good
        // animation!

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_search) {

            llCardContainer.addView(new ItemCardThumbnail(this));

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
