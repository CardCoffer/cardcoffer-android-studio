package com.cardcoffer.app.activities;

import com.cardcoffer.app.R;
import com.cardcoffer.app.customviews.ItemCardThumbnail;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SearchView;

public class StacksActivity extends Activity {
	
	LinearLayout llStackContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_stacks);
		getActionBar().setTitle("Stacks");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.stacks_actionbar, menu);

		// actionbar searchview animation

		SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
		int searchBarId = searchView.getContext().getResources().getIdentifier("android:id/search_bar", null, null);
		LinearLayout searchBar = (LinearLayout) searchView.findViewById(searchBarId);
		searchBar.setLayoutTransition(new LayoutTransition()); // TODO set good
																// animation!
		
		llStackContainer = (LinearLayout) findViewById(R.id.stacks_llStackContainer);

		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			llStackContainer.addView(new ItemCardThumbnail(this));
			
			return true;
		}
		if (id == R.id.action_search) {


			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
