package com.cardcoffer.app.activities;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.cardcoffer.app.R;
import com.cardcoffer.app.R.id;
import com.cardcoffer.app.R.layout;
import com.cardcoffer.app.R.menu;
import com.cardcoffer.app.customviews.ItemCardThumbnail;
import com.parse.ParseUser;

/**
 * 
 * @author sinash
 *
 */
public class HomeActivity extends Activity {

	LinearLayout llCardContainer;
	ImageButton btnCardCoffer, btnCheckIn, btnExchange;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		llCardContainer = (LinearLayout) findViewById(R.id.home_llCardContainer);

		btnCardCoffer = (ImageButton) findViewById(R.id.btnHome_cardCoffer);
		btnCheckIn = (ImageButton) findViewById(R.id.btnHome_checkIn);
		btnExchange = (ImageButton) findViewById(R.id.btnHome_exchange);
		
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
