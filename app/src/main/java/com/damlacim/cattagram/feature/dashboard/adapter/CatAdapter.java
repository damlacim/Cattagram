package com.damlacim.cattagram.feature.dashboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.damlacim.cattagram.R;

/**
 * Created by Damla Cim on 24.04.2023
 */

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.ViewHolder> {

    private Integer[] mImages;
    private LayoutInflater mInflater;

    public CatAdapter(Context context, Integer[] images) {
        this.mInflater = LayoutInflater.from(context);
        this.mImages = images;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_cat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Integer imageId = mImages[position];
        holder.imageView.setImageResource(imageId);
    }

    @Override
    public int getItemCount() {
        return mImages.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivImage);
        }
    }
}