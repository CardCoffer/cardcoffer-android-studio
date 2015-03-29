package com.cardcoffer.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.cardcoffer.app.R;
import com.thedazzler.droidicon.badges.DroidiconBadge;

public class ExchangeActivity extends Activity {


    ViewFlipper viewFlipper;
    TextView sendBig,sendTap,receiveBig,receiveTap, openScanner;
    DroidiconBadge btnNFCSend, btnNFCReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        getActionBar().setTitle("Exchange Room");

        viewFlipper = (ViewFlipper) findViewById(R.id.exchange_viewFlipper);

        sendBig = (TextView) findViewById(R.id.tvExchange_sending);
        sendTap = (TextView) findViewById(R.id.tvExchange_tapToReceive);

        receiveBig = (TextView) findViewById(R.id.tvExchange_receiving);
        receiveTap = (TextView) findViewById(R.id.tvExchange_tapToSend);
        openScanner = (TextView) findViewById(R.id.tvExchange_openQRScanner);

        btnNFCSend = (DroidiconBadge) findViewById(R.id.btnExchange_nfc_send);

        btnNFCSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                launchNFCActivity("send");
            }
        });

        btnNFCReceive = (DroidiconBadge) findViewById(R.id.btnExchange_nfc_receive);

        btnNFCReceive.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                launchNFCActivity("receive");
            }
        });

        sendBig.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                viewFlipper.showNext();

            }
        });

        sendTap.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                viewFlipper.showNext();

            }
        });
        receiveBig.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                viewFlipper.showNext();

            }
        });
        receiveTap.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                viewFlipper.showNext();

            }
        });

        openScanner.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                launchScannerActivity();

            }
        });

    }

    private void launchScannerActivity() {

        Intent intent = new Intent(this, ScannerActivity.class);
        startActivity(intent);
    }

    private void launchNFCActivity(String mode) {

        Intent intent = new Intent(this, NFCActivity.class);

        intent.putExtra("mode", mode);
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_exchange, menu);
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
