package com.cardcoffer.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cardcoffer.app.R;

public class NFCActivity extends Activity {

    private NfcAdapter nfcAdapter;
    private NdefMessage ndefMessage;
    private SharedPreferences locationSharedPref;
    private String locationString;
    private String actualPayload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //FIXME dorost kon in ashghal ro!
        try {
            String mode = getIntent().getStringExtra("mode").toString();

//        Log.d("ui", "mode: " + mode);

            if (mode.equalsIgnoreCase("send")) {
                setContentView(R.layout.activity_nfc_send);
                getActionBar().setTitle("Send via NFC");
//            Log.d("ui", "setting layout send");
            } else if (mode.equalsIgnoreCase("receive")) {

                setContentView(R.layout.activity_nfc_receive);
                getActionBar().setTitle("Receive via NFC");

//            Log.d("ui", "setting layout receive");
            }
        }
        catch (NullPointerException e){
            setContentView(R.layout.activity_nfc_send);
            getActionBar().setTitle("Send via NFC");
        }

        //TODO we should support phones that don't have NFC hardware! but for MVP, it's fine
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(!nfcAdapter.isEnabled()){

            Toast.makeText(getApplicationContext(), "Please turn on NFC", Toast.LENGTH_SHORT).show();
            //head to nfc settings page
            startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS)); //TODO only works with pure android.


        }

        locationSharedPref = getSharedPreferences("locationString", MODE_PRIVATE);
        locationString = locationSharedPref.getString("locationString-name", "Not Set");

        String mainCardObjectID = getSharedPreferences("dataSP", MODE_PRIVATE).getString("mainCard", "");
        actualPayload = mainCardObjectID;


        // Check for NFC Adapter
        if (nfcAdapter == null) {
            Toast.makeText(getApplicationContext(), "NFC is Not Available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        NdefRecord mimeRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA,
                "application/vnd.com.cardcoffer".getBytes(), new byte[0], actualPayload.getBytes());


        NdefRecord[] recordArray = { mimeRecord };

        ndefMessage = new NdefMessage(recordArray);

        //Register Callback
        nfcAdapter.setNdefPushMessage(ndefMessage, this, this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nfc, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // check to see wether the activity is started due to an android beam

        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processNFCIntent(getIntent());
        }


    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
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


    /**
     * The actual magic about Android Beam.
     *
     * @author sinash
     * @param intent
     *
     */

    private void processNFCIntent(Intent intent){

        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

        NdefMessage msg = (NdefMessage) rawMsgs[0];

        Toast.makeText(getApplicationContext(), new String(msg.getRecords()[0].getPayload()), Toast.LENGTH_SHORT)
                .show();

        final String message = new String(msg.getRecords()[0].getPayload());

    }

}
