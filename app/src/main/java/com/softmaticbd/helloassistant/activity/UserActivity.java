package com.softmaticbd.helloassistant.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.softmaticbd.helloassistant.R;
import com.softmaticbd.helloassistant.model.UserContactInfo;
import com.softmaticbd.helloassistant.shared.SharedPref;
import com.softmaticbd.helloassistant.utils.UserUtility;

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etUserId, etUserName, etFirstName, etLastName, etMobileNumber, etEmail, etPresentAddress, etPermanentAddress;
    private String userName, firstName, lastName, mobileNumber, email, presentAddress, permanentAddress;
    private String userId;
    private Button btnSaveUser;
    private UserContactInfo userContactInfo;
    private UserUtility userUtility;
    private static final String REQUIRED = "REQUIRED";
    private SharedPref sharedPref;
    private ProgressDialog dialog;
    // todo response show
    private TextView tvShowData;
    private Button btnClose;
    private LinearLayout layoutResp,layoutMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        etUserId = findViewById(R.id.etContactUserId);
        etUserName = findViewById(R.id.etUserName);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etMobileNumber = findViewById(R.id.etMobileNumber);
        etEmail = findViewById(R.id.etEmail);
        etPresentAddress = findViewById(R.id.etPresentAddress);
        etPermanentAddress = findViewById(R.id.etPermanentAddress);
        btnSaveUser = findViewById(R.id.btnSaveUser);
        tvShowData = findViewById(R.id.tvShowData);
        btnClose= findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
        layoutResp = findViewById(R.id.layoutResp);
        layoutMain = findViewById(R.id.layoutMain);
        layoutMain.setVisibility(View.VISIBLE);

        btnSaveUser.setOnClickListener(this);
        sharedPref = new SharedPref(this);
        userUtility = new UserUtility(this);
        // todo progress dialog
        dialog = new ProgressDialog(UserActivity.this);
        dialog.setTitle("Loading....");
        dialog.setCancelable(false);


    }

    @Override
    public void onClick(View view) {
        if (view == btnSaveUser) {
            dialog.show();
            SaveUserInfo();
        }
        if (view == btnClose){
            layoutResp.setVisibility(View.GONE);
            layoutMain.setVisibility(View.VISIBLE);
        }
    }

    private void SaveUserInfo() {
        userId = etUserId.getText().toString();
        userName = etUserName.getText().toString();
        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();
        mobileNumber = etMobileNumber.getText().toString();
        email = etEmail.getText().toString();
        presentAddress = etPresentAddress.getText().toString();
        permanentAddress = etPermanentAddress.getText().toString();
        if (userId.equals("")){
            etUserId.setError(REQUIRED);
            etUserId.requestFocus();
            dialog.dismiss();
            return;
        }
        if (userName.equals("")){
            etUserName.setError(REQUIRED);
            etUserName.requestFocus();
            dialog.dismiss();
            return;
        }
        if (firstName.equals("")){
            etFirstName.setError(REQUIRED);
            etFirstName.requestFocus();
            dialog.dismiss();
            return;
        }
        if (lastName.equals("")){
            etLastName.setError(REQUIRED);
            etLastName.requestFocus();
            dialog.dismiss();
            return;
        }
        if (mobileNumber.equals("")){
            etMobileNumber.setError(REQUIRED);
            etMobileNumber.requestFocus();
            dialog.dismiss();
            return;
        }
        if (!userUtility.isMobileValid(mobileNumber)){
            etMobileNumber.setError("Invalid Mobile Number ..!!");
            etMobileNumber.requestFocus();
            dialog.dismiss();
            return;
        }
        if (email.equals("")){
            etEmail.setError(REQUIRED);
            etEmail.requestFocus();
            dialog.dismiss();
            return;
        }
        if (!userUtility.isEmailValid(email)){
            etEmail.setError("Invalid Email.. !!");
            etEmail.requestFocus();
            dialog.dismiss();
            return;
        }
        if (presentAddress.equals("")){
            etPresentAddress.setError(REQUIRED);
            etPresentAddress.requestFocus();
            return;
        }
        if (permanentAddress.equals("")){
            etPermanentAddress.setError(REQUIRED);
            etPermanentAddress.requestFocus();
            return;
        }
        int id = Integer.parseInt(userId);
        userContactInfo = new UserContactInfo(id,userName,firstName,lastName,mobileNumber,email,presentAddress,permanentAddress);
        if (sharedPref.setUserContactInfo(userContactInfo)){
            Toast.makeText(getApplicationContext(),"Data Save",Toast.LENGTH_SHORT).show();
            if (ClearField()){
                loadDataIntoView();
                layoutMain.setVisibility(View.GONE);
                layoutResp.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        }

    }

    private void loadDataIntoView(){
        UserContactInfo user = sharedPref.getUserContactInfo();
        if (user != null){
            String content = "";
            content += "User Id : " + user.getUser_id() + "\n";
            content += "User Name : " + user.getUser_name() + "\n";
            content += "Full Name : " + user.getFirst_name() +" "+user.getLast_name() +"\n";
            content += "Phone Number : " + user.getContact_number() + "\n";
            content += "User Email : " + user.getEmail() + "\n";
            content += "Present Address : " + user.getPresent_address() + "\n";
            content += "Permanent Address : " + user.getPermanent_address();
            tvShowData.append(content);
        }
    }
    private boolean ClearField(){
        etUserId.setText("");
        etUserName.setText("");
        etFirstName.setText("");
        etLastName.setText("");
        etMobileNumber.setText("");
        etEmail.setText("");
        etPresentAddress.setText("");
        etPermanentAddress.setText("");
        return true;
    }

}