package com.softmaticbd.helloassistant.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CamScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    private String isClass;
    private String scResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        getIntentResult();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            checkPermission();
        }
    }

    private void getIntentResult() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            isClass = null;
        } else {
            isClass = extras.getString("IsClass");
        }
    }

    private Boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        //  todo for which class needs to return scanner result
        scResult = result.getText();
        onBackPressed();
//        if (isClass.equals("scan")) {
//            ScannerActivity.tvCamScanResult.setText(scResult);
//            startActivity(new Intent(CamScannerActivity.this, ScannerActivity.class));
//        }

    }

    @Override
    public void onBackPressed() {
//        if (isClass.equals("scan")) {
//            ScannerActivity.tvCamScanResult.setText(scResult);
//        }
        if (isClass.equals("stock")) {
           // startActivity(new Intent(CamScannerActivity.this, StockSearchActivity.class).putExtra("Result", scResult));
        }
        if (isClass.equals("audit")) {
           // startActivity(new Intent(CamScannerActivity.this, AuditActivity.class).putExtra("Result", scResult));
        }
        if (isClass.equals("sales")) {
            //startActivity(new Intent(CamScannerActivity.this, SalesOrderActivity.class).putExtra("Result", scResult));
        }
        super.onBackPressed();
    }
}
