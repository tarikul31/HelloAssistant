package com.softmaticbd.helloassistant.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.softmaticbd.helloassistant.R;
import com.softmaticbd.helloassistant.adapter.PostAdapter;
import com.softmaticbd.helloassistant.config.ApiConfig;
import com.softmaticbd.helloassistant.model.Post;
import com.softmaticbd.helloassistant.service.ApiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestApiActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvDataShow,tvCreateNewPost,tvLastPost,tvSalesOrder;
    private RecyclerView recyclerView;
    private ProgressDialog dialog;
    private EditText etUserId;
    private ImageView ivUserId;
    private ApiService apiService;
    private Integer userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_api);
        recyclerView = findViewById(R.id.rvPostId);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        etUserId = findViewById(R.id.etUserId);
        ivUserId = findViewById(R.id.ivUserId);
        ivUserId.setOnClickListener(this);
        tvCreateNewPost = findViewById(R.id.tvCreateNewPost);
        tvCreateNewPost.setOnClickListener(this);
        tvLastPost = findViewById(R.id.tvLastPost);
        tvLastPost.setOnClickListener(this);
        tvSalesOrder = findViewById(R.id.tvSalesOrder);
        tvSalesOrder.setOnClickListener(this);

        //tvDataShow = findViewById(R.id.tvDataShow);
        dialog = new ProgressDialog(RestApiActivity.this);
        dialog.setTitle("Loading ...... ");
        dialog.setCancelable(false);

    }

    @Override
    public void onClick(View view) {
        if (view == ivUserId) {

            if (etUserId.getText().toString().equals("") || etUserId.getText().toString().equals(0)) {
                etUserId.setError("Required");
                etUserId.requestFocus();
            }
            userId = Integer.parseInt(etUserId.getText().toString());
            dialog.show();
            getPostByUserId(userId);
//            if (turnOffKeyBoard()){
//                dialog.show();
//                getPostByUserId(userId);
//            }
        }
        if (view == tvCreateNewPost){
            startActivity(new Intent(getApplicationContext(),CreatePostActivity.class));
        }
        if (view == tvLastPost){
            dialog.show();
            getDataFromApi();
        }
        if (view == tvSalesOrder){
            startActivity(new Intent(getApplicationContext(),SalesOrderActivity.class));
        }
    }

//    private boolean turnOffKeyBoard() {
//        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//        assert imm != null;
//        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//        return true;
//    }

    private void getDataFromApi() {
        apiService = ApiConfig.getApiClient().create(ApiService.class);
        Call<List<Post>> call = apiService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code : " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                List<Post> postList = response.body();
                dialog.dismiss();
                PostAdapter adapter = new PostAdapter(RestApiActivity.this, postList);
                recyclerView.setAdapter(adapter);

//                for (Post post : postList){
//                    String content = "";
//                    content += "Id : " + post.getId() + "\n" ;
//                    content += "User Id : " +post.getUserId() + "\n";
//                    content += "Title : " +post.getTitle() + "\n";
//                    content += "Body : " +post.getBody();
//                    tvDataShow.append(content);
//                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }

    private void getPostByUserId(int uid) {
        // todo for Map Query
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", String.valueOf(uid));
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");

        apiService = ApiConfig.getApiClient().create(ApiService.class);
        // todo for Query inline
        Call<List<Post>> call = apiService.getPostsById(uid,"id","desc");

        // todo for Map Query
       // Call<List<Post>> call = apiService.getPostsById(parameters);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Code : " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                List<Post> postList = response.body();
                dialog.dismiss();
                PostAdapter adapter = new PostAdapter(RestApiActivity.this, postList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
    }
}
