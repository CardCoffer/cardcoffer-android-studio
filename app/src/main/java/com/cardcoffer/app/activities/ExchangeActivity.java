package com.cardcoffer.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.cardcoffer.app.R;
import com.thedazzler.droidicon.badges.DroidiconBadge;

import net.glxn.qrgen.android.QRCode;

public class ExchangeActivity extends Activity {


    ViewFlipper viewFlipper;
    TextView sendBig,sendTap,receiveBig,receiveTap, openScanner;
    ImageView ivQRCode;
    DroidiconBadge btnNFCSend, btnNFCReceive, btnBluetoothSend, btnBluetoothReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        getActionBar().setTitle("Exchange Room");

        viewFlipper = (ViewFlipper) findViewById(R.id.exchange_viewFlipper);


        ivQRCode = (ImageView) findViewById(R.id.ivExchange_qrcode);
        //QRCODE GENERATION! :P
        //TODO this has to be fixed.. the main card should be stored on web rather than mobile device...
        try {
            String mainCardObjectID = getSharedPreferences("dataSP", MODE_PRIVATE).getString("mainCard", "");
            Bitmap myBitmap = QRCode.from(mainCardObjectID).bitmap();
            ivQRCode.setImageBitmap(myBitmap);
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), "Please Long-click on a thumbnail in Home to set as your desired card.", Toast.LENGTH_LONG).show();
            finish();
        }


        sendBig = (TextView) findViewById(R.id.tvExchange_sending);
        sendTap = (TextView) findViewById(R.id.tvExchange_tapToReceive);

        receiveBig = (TextView) findViewById(R.id.tvExchange_receiving);
        receiveTap = (TextView) findViewById(R.id.tvExchange_tapToSend);
        openScanner = (TextView) findViewById(R.id.tvExchange_openQRScanner);

        btnNFCSend = (DroidiconBadge) findViewById(R.id.btnExchange_nfc_send);

        btnBluetoothSend = (DroidiconBadge) findViewById(R.id.btnExchange_bluetooth_send);

        btnBluetoothSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                launchBluetoothActivity("send");
            }
        });

        btnBluetoothReceive = (DroidiconBadge) findViewById(R.id.btnExchange_bluetooth_receive);

        btnBluetoothReceive.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                launchBluetoothActivity("receive");
            }
        });


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

    private void launchBluetoothActivity(String mode) {

        Intent intent = new Intent(this, BluetoothActivity.class);

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
