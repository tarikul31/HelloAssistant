package com.softmaticbd.helloassistant.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softmaticbd.helloassistant.R;
import com.softmaticbd.helloassistant.activity.CreatePostActivity;
import com.softmaticbd.helloassistant.model.Post;
import com.softmaticbd.helloassistant.viewholder.PostViewHolder;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private Context context;
    private List<Post> postList;

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post_list, parent, false);
        return new PostViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        final Post post = postList.get(position);
        holder.rvId.setText("Id : " + (post.getId()));
        holder.rvUserId.setText("User Id: " + (post.getUserId()));
        holder.rvTitle.setText("Title : " + (post.getTitle()));
        holder.rvBody.setText("Body" + (post.getBody()));
        holder.ivRecyclerEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CreatePostActivity.class);
                intent.putExtra("id",String.valueOf(post.getId()));
                intent.putExtra("userId",String.valueOf(post.getUserId()));
                intent.putExtra("title",post.getTitle());
                intent.putExtra("body",post.getBody());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
