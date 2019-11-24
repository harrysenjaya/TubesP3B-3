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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


class GridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Manga> original;
    private ArrayList<Manga> manga;
    private LayoutInflater inflater;
    private OnItemClick onItemClick;

    public GridAdapter(Context context, ArrayList<Manga> manga) {
        this.context = context;
        this.original = manga;
        this.manga = manga;
    }

    public void setItem(ArrayList<Manga> manga){
        this.manga = manga;
        notifyDataSetChanged();
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

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(manga.get(position));
            }
        });

        ImageView imageView=convertView.findViewById(R.id.image_grid);
        TextView title=convertView.findViewById(R.id.titleGrid);
        TextView status = convertView.findViewById(R.id.statusGrid);
        TextView lastChapterDate = convertView.findViewById(R.id.chapterDateGrid);
        if(!this.manga.get(position).getImage().equals("")) {
            Glide.with(convertView).load("https://cdn.mangaeden.com/mangasimg/" + this.manga.get(position).getImage()).into(imageView);
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

    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    public ArrayList<Manga> sortByAtoZ(){
        Collections.sort(manga, Manga.atoZComparator);
        notifyDataSetChanged();
        return manga;
    }

    public ArrayList<Manga> sortByZtoA(){
        Collections.sort(manga, Manga.ztoAComparator);
        notifyDataSetChanged();
        return manga;
    }

    public ArrayList<Manga> sortByHits(){
        Collections.sort(manga, Manga.hitsComparator);
        notifyDataSetChanged();
        return manga;
    }

    public ArrayList<Manga> searchManga(String title){
        this.manga = this.original;
        ArrayList<Manga> newArr = new ArrayList<>();
        int count = title.length();

        for(int i=0; i<manga.size();i++){
            if(manga.get(i).getTitle().length()>=count) {
                if (manga.get(i).getTitle().substring(0, title.length()).equalsIgnoreCase(title)) {
                    newArr.add(manga.get(i));
                }
            }
        }

        return newArr;
    }
}