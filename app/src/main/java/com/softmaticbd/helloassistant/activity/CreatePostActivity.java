package com.softmaticbd.helloassistant.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.softmaticbd.helloassistant.R;
import com.softmaticbd.helloassistant.config.ApiConfig;
import com.softmaticbd.helloassistant.model.Post;
import com.softmaticbd.helloassistant.service.ApiService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePostActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUserId, etTitleId, etBodyId, etIDId;
    private Button btnSavePost, btnCloseResponse,btnEditPost;
    private String user_Id, title, body;
    private int userId;
    private Post post;
    private ProgressDialog pDialog;
    private LinearLayout layoutCreate, layoutResponse;
    private TextView tvResponseResult;
    private String eId,eUserId,eTitle,eBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        etIDId = findViewById(R.id.etIDId);
        etUserId = findViewById(R.id.etUserId);
        etTitleId = findViewById(R.id.etTitleId);
        etBodyId = findViewById(R.id.etBodyId);
        btnSavePost = findViewById(R.id.btnSavePost);
        btnSavePost.setOnClickListener(this);
        // todo edit post
        btnEditPost = findViewById(R.id.btnEditPost);
        btnEditPost.setOnClickListener(this);
        btnEditPost.setVisibility(View.GONE);

        // todo response message
        btnCloseResponse = findViewById(R.id.btnCloseResponse);
        btnCloseResponse.setOnClickListener(this);
        layoutCreate = findViewById(R.id.layoutCreate);
        layoutResponse = findViewById(R.id.layoutResponse);
        tvResponseResult = findViewById(R.id.tvResponseResult);
        layoutResponse.setVisibility(View.GONE);
        layoutCreate.setVisibility(View.VISIBLE);

        pDialog = new ProgressDialog(this);
        pDialog.setTitle("Loading ......");
        pDialog.setCancelable(false);
        getExtraResult();
        if (getExtraResult()){
            btnSavePost.setVisibility(View.GONE);
            btnEditPost.setVisibility(View.VISIBLE);
            setDataIntoView();
        }
    }

    private boolean getExtraResult(){
        eId = getIntent().getStringExtra("id");
        eUserId = getIntent().getStringExtra("userId");
        eTitle = getIntent().getStringExtra("title");
        eBody = getIntent().getStringExtra("body");
        return true;
    }

    private void setDataIntoView(){
        etIDId.setText(eId);
        etUserId.setText(eUserId);
        etTitleId.setText(eTitle);
        etBodyId.setText(eBody);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSavePost) {
            user_Id = etUserId.getText().toString();
            title = etTitleId.getText().toString();
            body = etBodyId.getText().toString();
            if (user_Id.equals("")) {
                etUserId.setError("Required");
                etUserId.requestFocus();
                return;
            }
            userId = Integer.parseInt(user_Id);
            if (title.equals("")) {
                etTitleId.setError("Required");
                etTitleId.requestFocus();
                return;
            }
            if (body.equals("")) {
                etBodyId.setError("Required");
                etBodyId.requestFocus();
                return;
            }
            pDialog.show();
            savePostActivity();
        }
        if (view == btnCloseResponse){
            layoutResponse.setVisibility(View.GONE);
            layoutCreate.setVisibility(View.VISIBLE);
        }
        if (view == btnEditPost){

        }
    }

    private void savePostActivity() {
        post = new Post(userId, title, body);
        // todo for map field
        Map<String, String> fields = new HashMap<>();
        fields.put("userId", user_Id);
        fields.put("title", title);
        fields.put("body", body);

        ApiService service = ApiConfig.getApiClient().create(ApiService.class);
        // Call<Post> call = service.createPosts(post); // todo for body annotation

        //Call<Post> call = service.createPosts(userId,title,body); // todo for field annotation

        Call<Post> call = service.createPosts(fields); // todo for field Map annotation
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code : " + response.code(), Toast.LENGTH_LONG).show();
                }
                // Toast.makeText(getApplicationContext(),"Data Save Successful " +response.code(),Toast.LENGTH_LONG).show();
                layoutResponse.setVisibility(View.VISIBLE);
                layoutCreate.setVisibility(View.GONE);
                Post postRes = response.body();
                String content = "";
                content += "Code : " + response.code() + "\n";
                content += "Id : " + postRes.getId() + "\n";
                content += "User Id : " + postRes.getUserId() + "\n";
                content += "Title : " + postRes.getTitle() + "\n";
                content += "Body : " + postRes.getBody();
                tvResponseResult.append(content);
                ClearField();
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                pDialog.dismiss();
            }
        });
    }

    private void ClearField() {
        etUserId.setText("");
        etTitleId.setText("");
        etBodyId.setText("");
    }
}
