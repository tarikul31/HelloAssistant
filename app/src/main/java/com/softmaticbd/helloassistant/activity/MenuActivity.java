package com.softmaticbd.helloassistant.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.softmaticbd.helloassistant.R;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        toolbar = findViewById(R.id.toolBarId);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.navViewId);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
        view = navView.getHeaderView(0);
        //testFragment();
    }

//    private void testFragment(){
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.mainContainerId, new Profile())
//                .commit();
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.navHomeId : {
                //startActivity(new Intent(MenuActivity.this,HomeActivity.class));
                Toast.makeText(getApplicationContext(),"Home Activity",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.navRestApiId : {
                startActivity(new Intent(MenuActivity.this,RestApiActivity.class));
                break;
            }

            case R.id.navAutoCompleteId : {
//               Toast.makeText(MenuActivity.this,"Menu Cam Scanner Clicked",Toast.LENGTH_LONG).show();
                startActivity(new Intent(MenuActivity.this,AutoComplete.class));
                break;
            }

        }
        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
