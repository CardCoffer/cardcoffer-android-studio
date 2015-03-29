package com.cardcoffer.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.cardcoffer.app.R;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * @author sinash
 *
 */

public class ScannerActivity extends Activity implements ZBarScannerView.ResultHandler {

    private ZBarScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZBarScannerView(this);
        setContentView(scannerView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera(); //start camera! you don't say :D

    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }


    @Override
    public void handleResult(Result result) {

        //result handler!

        Log.v("qrcode", "result: " + result.getContents());
        Log.v("qrcode", "type: " + result.getBarcodeFormat().getName());

        Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_SHORT).show();


    }
}
