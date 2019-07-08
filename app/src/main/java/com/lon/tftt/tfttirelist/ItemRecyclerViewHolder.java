package com.lon.tftt.tfttirelist;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txt_title;
        ImageView imageView,imageView2,imageView3;
public ItemRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        txt_title=(TextView)itemView.findViewById(R.id.item_title);

        imageView=(ImageView)itemView.findViewById(R.id.itemImageView);
        imageView2=(ImageView)itemView.findViewById(R.id.itemImageView2);
        imageView3=(ImageView)itemView.findViewById(R.id.itemImageView3);


        }
        }

