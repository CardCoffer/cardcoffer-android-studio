//TODO in landscape orientation, the logo doesn't show up completely.

package com.cardcoffer.app.activities;

import com.cardcoffer.app.R;
import com.cardcoffer.app.R.id;
import com.cardcoffer.app.R.layout;

import android.accounts.AccountAuthenticatorActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AccountAuthenticatorActivity {
	
	private EditText etUsername;
	private EditText etPassword;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.layout_login);
		
		etUsername = (EditText) findViewById(R.id.etUsername);
		etPassword = (EditText) findViewById(R.id.etPassword);
		
		TextView textView =(TextView)findViewById(R.id.tvLogin_Signup);
		textView.setClickable(true);
		textView.setMovementMethod(LinkMovementMethod.getInstance());
		String text = "<a href='http://www.cardcoffer.com'> Sign Up for CardCoffer </a>";
		textView.setText(Html.fromHtml(text));
		
		Button btnSignIn = (Button) findViewById(R.id.btnSignIn);
		
		btnSignIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Log.i("vv-auth", "Button Clicked");
		
				String username = etUsername.getText().toString();
				
				username = username.toLowerCase();
				String password = etPassword.getText().toString();
		
				Log.d("vv-auth", "username: " + username + "  password: " + password);
				
				
				
				ParseUser.logInInBackground(username, password, new LogInCallback() {
					
					@Override
					public void done(ParseUser user, ParseException e) {
						
						if(user != null){
							Log.i("vv-auth", "User Signed In Successfully");
							Intent tempHomeIntent = new Intent (getApplicationContext(), HomeActivity.class);
							startActivity(tempHomeIntent);
							finish();
						}
						else{
							Log.e("vv-auth", e.toString() + "\n****CODE: " + e.getCode());
							if(e.getCode() == ParseException.CONNECTION_FAILED){
								Toast.makeText(getApplicationContext(), "Connection To Server Failed, Check Your Network Connection" ,Toast.LENGTH_SHORT).show();
							}
							else if(e.getCode() == ParseException.OBJECT_NOT_FOUND){
							Toast.makeText(getApplicationContext(), "This Username/Password Combination Doesn't Exist, Please Try Again.", Toast.LENGTH_SHORT).show();
							}
							else{
								Toast.makeText(getApplicationContext(), "Attempt Failed by Other Causes, Report to Developers." , Toast.LENGTH_SHORT).show();
							}
						}
					}
				} );
				
			}
		});
		
		
		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Tokhom?! Make it look like an adamziadi app! at least, make the app disappear and show the Device's Home Screen! 
		//DO NOTHING! :D XXX
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		return super.onCreateOptionsMenu(menu);
		
	}
	

}
