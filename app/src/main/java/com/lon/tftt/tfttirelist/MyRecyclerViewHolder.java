package com.lon.tftt.tfttirelist;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView txt_title,txt_comment;
    public MyRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_comment=(TextView)itemView.findViewById(R.id.txt_content);
        txt_title=(TextView)itemView.findViewById(R.id.txt_title);
    }
}
