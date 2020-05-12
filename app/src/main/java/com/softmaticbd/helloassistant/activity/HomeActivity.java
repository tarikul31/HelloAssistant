package com.softmaticbd.helloassistant.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.softmaticbd.helloassistant.R;
import com.softmaticbd.helloassistant.adapter.ImageAdapter;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager vpImageSlide;
    private ImageAdapter imageAdapter;
    private LinearLayout lvStockSearch,lvSalesOrder,layAuditInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        vpImageSlide = findViewById(R.id.viewPagerId);
        imageAdapter = new ImageAdapter(this);
        vpImageSlide.setAdapter(imageAdapter);
        lvStockSearch = findViewById(R.id.layStockSearch);
        lvStockSearch.setOnClickListener(this);
        lvSalesOrder = findViewById(R.id.laySalesOrderId);
        lvSalesOrder.setOnClickListener(this);
        layAuditInfo = findViewById(R.id.layAuditInfo);
        layAuditInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
