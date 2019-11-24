package com.example.tubesp3b_3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tubesp3b_3.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private ArrayList<String> pages;

    public SliderAdapter(Context context, ArrayList<String> pages) {
        this.pages = pages;
        this.context = context;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_manga_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        Glide.with(viewHolder.itemView).load("https://cdn.mangaeden.com/mangasimg/" + this.pages.get(position)).into(viewHolder.imageViewBackground);
    }

    @Override
    public int getCount() {
        return this.pages.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.photoView);
            this.itemView = itemView;
        }
    }
}