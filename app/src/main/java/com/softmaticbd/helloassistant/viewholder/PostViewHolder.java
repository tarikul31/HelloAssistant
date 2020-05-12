package com.softmaticbd.helloassistant.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softmaticbd.helloassistant.R;

public class PostViewHolder extends RecyclerView.ViewHolder {
    public TextView rvId,rvUserId,rvTitle,rvBody;
    public ImageView ivRecyclerEdit;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        rvId = itemView.findViewById(R.id.rvId);
        rvUserId = itemView.findViewById(R.id.rvUserId);
        rvTitle = itemView.findViewById(R.id.rvTitleId);
        rvBody = itemView.findViewById(R.id.rvBodyId);
        ivRecyclerEdit = itemView.findViewById(R.id.ivRecyclerEdit);
    }
}
