package com.cardcoffer.app.activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ViewFlipper;

import com.cardcoffer.app.R;


//TODO listen for bluetooth state changes , so that users can't meddle with the app! :D
//use ACTION_STATE_CHANGED broadcast intent
public class BluetoothActivity extends Activity {

    ViewFlipper viewFlipper;


    //request codes
    int REQUEST_ENABLE_BT = 1001;

    BluetoothAdapter bluetoothAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        viewFlipper = (ViewFlipper) findViewById(R.id.bluetooth_viewFlipper);


        bluetoothAdapter  = BluetoothAdapter.getDefaultAdapter();

        if(bluetoothAdapter == null){
            //device doesn't support bluetooth

            viewFlipper.setDisplayedChild(0);
            finish();
            return; // FIXME ??? :D
        }

        viewFlipper.setDisplayedChild(1);

        if (!bluetoothAdapter.isEnabled()){

            //Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            //startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);


            Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);
            startActivity(discoverableIntent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bluetooth, menu);
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
