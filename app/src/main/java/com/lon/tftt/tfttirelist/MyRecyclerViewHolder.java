package com.lon.tftt.tfttirelist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView txt_title,txt_comment;
    ImageView imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8;
    public MyRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_comment=(TextView)itemView.findViewById(R.id.txt_content);
        txt_title=(TextView)itemView.findViewById(R.id.txt_title);

        imageView=(ImageView)itemView.findViewById(R.id.imageView);
        imageView2=(ImageView)itemView.findViewById(R.id.imageView2);
        imageView3=(ImageView)itemView.findViewById(R.id.imageView3);
        imageView4=(ImageView)itemView.findViewById(R.id.imageView4);
        imageView5=(ImageView)itemView.findViewById(R.id.imageView5);
        imageView6=(ImageView)itemView.findViewById(R.id.imageView6);
        imageView7=(ImageView)itemView.findViewById(R.id.imageView7);
        imageView8=(ImageView)itemView.findViewById(R.id.imageView8);

    }
}
