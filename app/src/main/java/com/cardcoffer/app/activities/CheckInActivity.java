package com.cardcoffer.app.activities;

import com.cardcoffer.app.R;
import com.cardcoffer.app.R.id;
import com.cardcoffer.app.R.layout;
import com.cardcoffer.app.R.menu;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

/**
 * 
 * @author sinash
 * 
 */

public class CheckInActivity extends Activity {

	private SharedPreferences locationSharedPref;
	private Button btnSaveChanges;
	private EditText etLocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActionBar().setTitle("Check In!");
		setContentView(R.layout.activity_checkin);

		// XXX MODE_PRIVATE maybe source of bugs
		locationSharedPref = getSharedPreferences("location", MODE_PRIVATE);

		btnSaveChanges = (Button) findViewById(R.id.btnCheckIn_saveChanges);

		etLocation = (EditText) findViewById(R.id.etCheckIn_location);
		
		String currentLocationString =locationSharedPref.getString("location-name", ""); 
		if(!(currentLocationString.equals(""))){
			etLocation.setText(currentLocationString);
		}

		btnSaveChanges.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				String locationString = etLocation.getText().toString();
				if (locationString.equals("")) {
					Toast.makeText(getApplicationContext(), "Please Enter a Location Name", Toast.LENGTH_SHORT)
							.show();
					return;

				}
				else{
					Editor editor = locationSharedPref.edit();
					editor.putString("location-name", locationString);
					editor.commit();
					Toast.makeText(getApplicationContext(), locationSharedPref.getString("location-name", "null"), Toast.LENGTH_SHORT)
					.show();
					
					//XXX runtime exception?
					finish();
				}

			}
		});
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
