package com.softmaticbd.helloassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.softmaticbd.helloassistant.activity.AutoComplete;
import com.softmaticbd.helloassistant.activity.CreatePostActivity;
import com.softmaticbd.helloassistant.activity.HomeActivity;
import com.softmaticbd.helloassistant.activity.MenuActivity;
import com.softmaticbd.helloassistant.activity.RestApiActivity;
import com.softmaticbd.helloassistant.activity.UserActivity;
import com.softmaticbd.helloassistant.login.SignInActivity;

public class MainActivity extends AppCompatActivity {
    private ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivLogo = findViewById(R.id.ivLogo);
        ivLogo.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ivLogo.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(MainActivity.this, MenuActivity.class));
                    }
                },500);
            }
        },1000);

    }
}
