package com.example.dell.ridesolo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView price;
    public ImageView turfimage;
    public TextView vehicle;
    public TextView numberplate;

    private ItemClickListener itemClickListener;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        price = (TextView)itemView.findViewById(R.id.textViewRating);
        vehicle = (TextView)itemView.findViewById(R.id.textViewTitle);
        numberplate = (TextView)itemView.findViewById(R.id.textViewShortDesc);
        turfimage = (ImageView)itemView.findViewById(R.id.turfimg);
        turfimage.setScaleType(ImageView.ScaleType.FIT_XY);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);

    }
}