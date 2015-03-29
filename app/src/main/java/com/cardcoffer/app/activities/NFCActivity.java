package com.cardcoffer.app.activities;

import android.app.Activity;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.cardcoffer.app.R;

public class NFCActivity extends Activity {

    private NfcAdapter nfcAdapter;
    private NdefMessage ndefMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String mode = getIntent().getStringExtra("mode").toString();

//        Log.d("ui", "mode: " + mode);

        if (mode.equalsIgnoreCase("send")) {
            setContentView(R.layout.activity_nfc_send);
            getActionBar().setTitle("Send via NFC");
//            Log.d("ui", "setting layout send");
        }

        else if(mode.equalsIgnoreCase("receive")){

            setContentView(R.layout.activity_nfc_receive);
            getActionBar().setTitle("Receive via NFC");

//            Log.d("ui", "setting layout receive");
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nfc, menu);
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
