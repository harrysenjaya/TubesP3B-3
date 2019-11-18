package com.example.tubesp3b_3;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class gridAdapter extends BaseAdapter {

    protected Context context;
    protected ArrayList<Manga> manga;
    protected LayoutInflater inflater;

    public gridAdapter(Context context, ArrayList<Manga> manga) {
        this.context = context;
        this.manga = manga;
        Log.d("Bikin","ADAPTER");
    }

    @Override
    public int getCount() {
        return manga.size();
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
        Log.d("BIKIN","VIEW");
        if (inflater==null){
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView=inflater.inflate(R.layout.fragment_grid_item,null);
        }

        ImageView imageView=convertView.findViewById(R.id.image_grid);
        TextView title=convertView.findViewById(R.id.titlenya);
        TextView status = convertView.findViewById(R.id.statusnya);
        TextView lastChapterDate = convertView.findViewById(R.id.lastChapterDate);
//        imageView.setImageResource("https://cdn.mangaeden.com/mangasimg/"+this.manga.get(position).getImage()));
        title.setText(this.manga.get(position).getTitle());
        status.setText(this.manga.get(position).getStatus());
        Date date =this.manga.get(position).getLast_chapter_date();
        if(date!=null) {
                String temp = date+"";
                String[] tempsplit = temp.split(" ");
                lastChapterDate.setText(tempsplit[2] + " " + tempsplit[1] + " " + tempsplit[5]);
        }
        else{
            lastChapterDate.setText("-");
        }

        return convertView;
    }
}
