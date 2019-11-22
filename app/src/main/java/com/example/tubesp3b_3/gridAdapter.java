package com.example.tubesp3b_3;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import uk.co.senab.photoview.PhotoViewAttacher;

class GridAdapter extends BaseAdapter {

    protected Context context;
    protected ArrayList<Manga> manga;
    protected LayoutInflater inflater;
    protected PhotoViewAttacher attacher;


    public GridAdapter(Context context, ArrayList<Manga> manga) {
        this.context = context;
        this.manga = manga;
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
        if (inflater==null){
            inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView=inflater.inflate(R.layout.fragment_grid_item,null);
        }

        ImageView imageView=convertView.findViewById(R.id.image_grid);
        TextView title=convertView.findViewById(R.id.titleGrid);
        TextView status = convertView.findViewById(R.id.statusGrid);
        TextView lastChapterDate = convertView.findViewById(R.id.chapterDateGrid);
        if(!this.manga.get(position).getImage().equals("")) {
            Glide.with(convertView).load("https://cdn.mangaeden.com/mangasimg/" + this.manga.get(position).getImage()).into(imageView);
            attacher=new PhotoViewAttacher(imageView);
        }
        else{
            imageView.setImageResource(R.drawable.noimage);
        }



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