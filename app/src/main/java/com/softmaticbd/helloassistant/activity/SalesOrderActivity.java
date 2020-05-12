package com.softmaticbd.helloassistant.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.softmaticbd.helloassistant.R;
import com.softmaticbd.helloassistant.adapter.SalesOrderAdapter;
import com.softmaticbd.helloassistant.model.SalesOrderModel;
import com.softmaticbd.helloassistant.service.ClickListener;

import java.util.ArrayList;
import java.util.List;

public class SalesOrderActivity extends AppCompatActivity implements View.OnClickListener, ClickListener {
    private List<SalesOrderModel> orderList = new ArrayList<>();
    private RecyclerView recyclerView;
    private static final String TAG = "Sales order Click";
    private Button btnOrdPayment;
    private EditText etSalesProCode;
    private ImageView ivSalesProCode;
    private String salesProCode;
    private LinearLayout laySalesScanner;

    // todo show scan result
    private String resultData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_order);
        etSalesProCode = findViewById(R.id.etSalesProCode);
        ivSalesProCode = findViewById(R.id.ivSalesProCode);
        ivSalesProCode.setOnClickListener(this);
        laySalesScanner = findViewById(R.id.laySalesScanner);
        laySalesScanner.setOnClickListener(this);

        recyclerView = findViewById(R.id.rvSalesOrder);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        btnOrdPayment = findViewById(R.id.btnOrdPayment);
        btnOrdPayment.setOnClickListener(this);
        getOrderInfo();
        getIntentResult();
    }
    private void getOrderInfo(){
        SalesOrderModel orderModel1 = new SalesOrderModel("0000000000123","Men's Slim Fit Panjabi","38","1500");
        orderList.add(orderModel1);
        SalesOrderModel orderModel2 = new SalesOrderModel("0000000000124","Men's T Shirt","L","500");
        orderList.add(orderModel2);
        SalesOrderModel orderModel3 = new SalesOrderModel("0000000000125","Men's Cotton Panjabi","40","2400");
        orderList.add(orderModel3);
        SalesOrderModel orderModel4 = new SalesOrderModel("0000000000126","Men's Formal Shirt","XL","1800");
        orderList.add(orderModel4);
        SalesOrderModel orderModel5 = new SalesOrderModel("0000000000127","Men's Regular Shirt","M","1500");
        orderList.add(orderModel5);
        SalesOrderModel orderModel6 = new SalesOrderModel("0000000000128","Men's Silk Panjabi","38","1900");
        orderList.add(orderModel6);

        SalesOrderAdapter adapter = new SalesOrderAdapter(SalesOrderActivity.this, orderList, this);
        recyclerView.setAdapter(adapter);
    }


    private void getIntentResult() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            resultData = null;
        } else {
            resultData = extras.getString("Result");
            ShowData(resultData);
        }
    }
    private void ShowData(String res){
        AlertDialog.Builder builder = new AlertDialog.Builder(SalesOrderActivity.this);
        builder.setTitle("Success");
        builder.setMessage("Scan Code : " + res);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }



    @Override
    public void onClick(View view) {

        if (view == ivSalesProCode){
            salesProCode = etSalesProCode.getText().toString();
            if (salesProCode.equals("")){
                etSalesProCode.setError("Required");
                etSalesProCode.requestFocus();
                return;
            }
        }
        if (view == laySalesScanner){
            startActivity(new Intent(getApplicationContext(),CamScannerActivity.class).putExtra("IsClass","sales"));
        }
    }

    @Override
    public void onPositionClicked(int position) {

    }
}
