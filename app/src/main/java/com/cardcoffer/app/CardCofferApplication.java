/**
 * Revision 87 Marks the end of an Era :D
 * This was the day a new round of development began.
 * Moving towards a cool UI, and independence from Parse.com !
 * @sinash
 * 
 */
package com.cardcoffer.app;

import java.util.HashMap;
import java.util.Map;

import android.app.Application;
import android.os.Build;

import com.cardcoffer.app.activities.HomeActivity;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.PushService;

/**
 * Application Context Class for Card Coffer.
 * 
 * 
 * @author sinash
 *
 */

/**
 * 
 * BUG-PAD:
 * 
 * TODO After login, if back is pressed, the user sees an empty login screen, and back button (as I've made it
 * to behave so) will not do anything, the user has to wipe the screen and start the app again (this time,
 * getting to logged-in TempHome). Shall be fixed.
 * 
 * 
 */

public class CardCofferApplication extends Application {
	

	@Override
	public void onCreate() {
		super.onCreate();

		// PARSE , @sinash
		Parse.enableLocalDatastore(this);
		Parse.initialize(getApplicationContext(), "OZjSaw6anDwRoWTADZoq6peXUrdg3AR73uyXKvi3",
				"WRbUs7l7cpVvyUafVLzIIvxMHxKxdmwwDi0SExjd");
		
		Map<String, String> dimensions = new HashMap<String, String>();
		dimensions.put("manufacturer", Build.MANUFACTURER);
		dimensions.put("device", Build.DEVICE);
		ParseAnalytics.trackEvent("model-info", dimensions);
		

		// PARSE PUSH NOTIFICATION
		// TODO having an activity that is always in background an can receive notifications!
		PushService.setDefaultPushCallback(this, HomeActivity.class);

	}

}
