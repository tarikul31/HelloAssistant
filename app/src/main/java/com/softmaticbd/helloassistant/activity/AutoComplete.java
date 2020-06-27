package com.softmaticbd.helloassistant.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.softmaticbd.helloassistant.R;
import com.softmaticbd.helloassistant.adapter.AutoCompleteAdapter;
import com.softmaticbd.helloassistant.model.CountryItem;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AutoComplete extends AppCompatActivity implements View.OnClickListener{

    // todo for auto completer
    private List<CountryItem> countryItemList;
    private AutoCompleteTextView actvCountry;
    private EditText etNumber1, etNumber2, etNumber3;
    private Button btnGetMax, btnGetRandom;
    private TextView tvMaxResult, tvRandomResult;
    private static final String REQUIRED = "REQUIRED";
    private ToggleButton toggleButton;
    private LinearLayout layoutAutoComplete, layoutGenerateRandom;
    private SecureRandom random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete);

        actvCountry = findViewById(R.id.actvCountry);
//        String[] countries = getResources().getStringArray(R.array.countries);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, countries);
//        textView.setAdapter(adapter);
        if (getCountryInfo()){
            AutoCompleteAdapter adapter = new AutoCompleteAdapter(this,countryItemList);
            actvCountry.setAdapter(adapter);
        }

        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        etNumber3 = findViewById(R.id.etNumber3);
        tvMaxResult = findViewById(R.id.tvMaxResult);
        btnGetMax = findViewById(R.id.btnGetMax);
        btnGetMax.setOnClickListener(this);
        // todo get Random
        btnGetRandom = findViewById(R.id.btnGetRandom);
        btnGetRandom.setOnClickListener(this);
        tvRandomResult = findViewById(R.id.tvRandomResult);
        random = new SecureRandom();
        // todo toggle layout
        toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(this);
        layoutAutoComplete = findViewById(R.id.layoutAutoComplete);
        layoutGenerateRandom = findViewById(R.id.layoutGenerateRandom);

    }

    private boolean getCountryInfo(){
        countryItemList = new ArrayList<>();
        countryItemList.add(new CountryItem("Afghanistan",R.raw.afghanistan));
        countryItemList.add(new CountryItem("Bangladesh",R.raw.bangladesh));
        countryItemList.add(new CountryItem("Brazil",R.raw.brazil));
        countryItemList.add(new CountryItem("Canada",R.raw.canada));
        countryItemList.add(new CountryItem("Dubai",R.raw.dubai));
        countryItemList.add(new CountryItem("England",R.raw.england));
        countryItemList.add(new CountryItem("France",R.raw.france));
        countryItemList.add(new CountryItem("Germany",R.raw.germany));
        countryItemList.add(new CountryItem("Holland",R.raw.holland));
        countryItemList.add(new CountryItem("India",R.raw.india));
        countryItemList.add(new CountryItem("Italy",R.raw.italy));
        countryItemList.add(new CountryItem("Japan",R.raw.japan));
        countryItemList.add(new CountryItem("Lebanon",R.raw.lebanan));
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == btnGetMax) {
            // todo for max Calculation
            getInputFromUser();
        }
        if (view == toggleButton) {
            if (toggleButton.isChecked()) {
                layoutGenerateRandom.setVisibility(View.VISIBLE);
                layoutAutoComplete.setVisibility(View.GONE);
            } else {
                layoutGenerateRandom.setVisibility(View.GONE);
                layoutAutoComplete.setVisibility(View.VISIBLE);
            }
        }
        if (view == btnGetRandom) {
            getRandomNumber();
        }
    }

    private void getInputFromUser(){
        if (etNumber1.getText().toString().equals("")) {
            etNumber1.setError(REQUIRED);
            etNumber1.requestFocus();
            return;
        }
        if (etNumber2.getText().toString().equals("")) {
            etNumber2.setError(REQUIRED);
            etNumber2.requestFocus();
            return;
        }
        if (etNumber3.getText().toString().equals("")) {
            etNumber3.setError(REQUIRED);
            etNumber3.requestFocus();
            return;
        }
        CalculateMAxResult();
    }

    @SuppressLint("SetTextI18n")
    private void CalculateMAxResult() {
        double result = 0;
        double number1 = Double.parseDouble(etNumber1.getText().toString());
        double number2 = Double.parseDouble(etNumber2.getText().toString());
        double number3 = Double.parseDouble(etNumber3.getText().toString());
        if (number1 > number2) {
            if (number1 > number3) {
                result = number1;
            } else {
                result = number3;
            }

        } else if (number2 > number3) {
            result = number2;
        } else {
            result = number3;
        }
        tvMaxResult.setText(result + "");
        clearText();
    }

    @SuppressLint("SetTextI18n")
    private void getRandomNumber() {
        int min = 100000;
        int max = 999999;
        int result = min + random.nextInt(max);
        tvRandomResult.setText(result + "");
    }


    private void clearText() {
        etNumber1.setText("");
        etNumber2.setText("");
        etNumber3.setText("");
    }
}
