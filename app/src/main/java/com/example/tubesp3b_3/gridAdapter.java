package com.example.tubesp3b_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class gridAdapter extends BaseAdapter {

    protected Context context;
    protected Manga[] manga;
    protected LayoutInflater inflater;

    public gridAdapter(Context context, Manga[] manga) {
        this.context = context;
        this.manga = manga;
    }

    @Override
    public int getCount() {
        return manga.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater==null){
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView=inflater.inflate(R.layout.grif_item,null);
        }

        ImageView imageView=convertView.findViewById(R.id.image_grid);
        TextView textView=convertView.findViewById(R.id.tv_gridItem);

        imageView.setImageResource(R.drawable.ic_launcher_background);
        textView.setText(manga[position].getNama());
        return convertView;
    }
}
