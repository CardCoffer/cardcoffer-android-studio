package com.cardcoffer.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.cardcoffer.app.R;

public class ExchangeActivity extends Activity {


    ViewFlipper viewFlipper;
    TextView sendBig,sendTap,receiveBig,receiveTap;

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

        sendBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewFlipper.showNext();

            }
        });

        sendTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewFlipper.showNext();

            }
        });
        receiveBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewFlipper.showNext();

            }
        });
        receiveTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewFlipper.showNext();

            }
        });

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
