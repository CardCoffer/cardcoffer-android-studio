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
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.cardcoffer.app.R;
import com.cardcoffer.app.R.id;

import com.cardcoffer.app.customviews.ItemCardThumbnail;
import com.cardcoffer.app.customviews.ThumbnailButton;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.thedazzler.droidicon.badges.DroidiconBadge;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onResume() {
        super.onResume();


        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername() );
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> userDataList, ParseException e) {
                if (e == null) {

                    if(userDataList != null){
                        ParseObject userData = userDataList.get(0);
//                        Toast.makeText(getApplicationContext(), userData.toString(), Toast.LENGTH_SHORT).show();

                        JSONArray arrayOfOwnCards = userData.getJSONArray("myOwnCards");

                        try {
                            layCards(arrayOfOwnCards);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

//                        Toast.makeText(getApplicationContext(), myArray.toString(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(),"Error Retrieving Data From Server", Toast.LENGTH_SHORT).show();
                }
            }
        });


        boolean found = false;

        for(int i = 0; i<llCardContainer.getChildCount(); i++){

            if(llCardContainer.getChildAt(i).getTag(id.TAG_VIEW_NAME).equals("ThumbnailButton")){
                found = true;
                break;
            }

        }

        if(found != true){
            llCardContainer.addView(new ThumbnailButton(this));
        }

    }

    private void layCards(JSONArray arrayOfOwnCards) throws JSONException {

        for(int i = 0; i<arrayOfOwnCards.length(); i++){

            String id = arrayOfOwnCards.getString(i);

            ParseQuery<ParseObject> query = ParseQuery.getQuery("Cards");

            query.getInBackground(id, new GetCallback<ParseObject>() {
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        populateActivity(object);
                    } else {



                    }
                }
            });

        }



    }

    private void populateActivity(ParseObject cardObject) {

        ItemCardThumbnail cardThumb = new ItemCardThumbnail(this);

        cardThumb.tvName.setText(cardObject.getString("name"));
        cardThumb.tvAffiliation.setText(cardObject.getString("affiliation"));
        cardThumb.tvJobTitle.setText(cardObject.getString("jobTitle"));

        llCardContainer.addView(cardThumb);



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

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
