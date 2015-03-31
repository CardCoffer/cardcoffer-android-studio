package com.cardcoffer.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.cardcoffer.app.R;

public class AddNewCardActivity extends Activity {

    private Button btnSaveSync;
    private ViewFlipper viewFlipper;
    private EditText etName, etJobTitle, etAffiliation, etAddress, etPhone, etEmail, etFacebook, etTwitter, etLinkedin, etGoogleplus, etMiniResume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_card);
        getActionBar().setTitle("Add New Card");

        btnSaveSync = (Button) findViewById(R.id.btnAddNewCard_saveChanges);
        viewFlipper = (ViewFlipper) findViewById(R.id.addnewcard_viewFlipper);

        etName = (EditText) findViewById(R.id.addnewcard_etName);
        etJobTitle = (EditText) findViewById(R.id.addnewcard_etJobTitle);
        etAffiliation = (EditText) findViewById(R.id.addnewcard_etAffiliation);
        etAddress = (EditText) findViewById(R.id.addnewcard_etAddress);
        etPhone = (EditText) findViewById(R.id.addnewcard_etPhone);
        etEmail = (EditText) findViewById(R.id.addnewcard_etEmail);
        etFacebook = (EditText) findViewById(R.id.addnewcard_etFacebook);
        etTwitter = (EditText) findViewById(R.id.addnewcard_etTwitter);
        etLinkedin = (EditText) findViewById(R.id.addnewcard_etLinkedin);
        etGoogleplus = (EditText) findViewById(R.id.addnewcard_etGoogleplus);
        etMiniResume = (EditText) findViewById(R.id.addnewcard_etMiniResume);



        btnSaveSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(detailsAreProvided()) {

                    saveCardToCloud();
                    viewFlipper.setDisplayedChild(1);

                }

            }
        });
    }

    private boolean detailsAreProvided() {

        if(etName.getText().toString().equals("") || etJobTitle.getText().toString().equals("") || etAffiliation.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Please fill in the first three fields.", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;

    }

    private void saveCardToCloud() {





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
