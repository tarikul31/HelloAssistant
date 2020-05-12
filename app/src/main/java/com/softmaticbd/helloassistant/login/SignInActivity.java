package com.softmaticbd.helloassistant.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.softmaticbd.helloassistant.R;
import com.softmaticbd.helloassistant.activity.MenuActivity;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvCreateNew;
    private EditText etUser, etPassword;
    private Button btnLogin;
    private String user,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        tvCreateNew = findViewById(R.id.tvCreateNewId);
        tvCreateNew.setOnClickListener(this);
        etUser = findViewById(R.id.etUserNameId);
        etPassword = findViewById(R.id.etPasswordId);
        btnLogin = findViewById(R.id.btnLoginId);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin){
            loginProcess();
        }
        if (view ==  tvCreateNew){
            startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
        }
    }
    private void loginProcess(){
        user = etUser.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        if (user.equals("")){
            etUser.setError("User Name Required");
            etUser.requestFocus();
            return;
        }
        if (password.equals("")){
            etPassword.setError("Password Required");
            etPassword.requestFocus();
            return;
        }
        if (user.equals("admin") && password.equals("123")){
            AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
            builder.setTitle("Success");
            builder.setMessage("Login Successful");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    etUser.setText("");
                    etPassword.setText("");
                    startActivity(new Intent(SignInActivity.this, MenuActivity.class));
                    dialog.dismiss();
                }
            });
            builder.show();
        }
        else if (!user.equals("admin") || !password.equals("123")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
            builder.setTitle("Error");
            builder.setMessage("Login Un Successful");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    etUser.setText("");
                    etPassword.setText("");
                    dialog.dismiss();
                }
            });
            builder.show();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();

    }
}
