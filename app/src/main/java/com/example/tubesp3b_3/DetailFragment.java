package com.example.tubesp3b_3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailFragment extends Fragment{

    @BindView(R.id.gambar)
    ImageView gambar;

    @BindView(R.id.deskription)
    TextView deskripsi;

    @BindView(R.id.artist)
    TextView artis;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.status)
    TextView status;

    @BindView(R.id.category)
    TextView category;

    @BindView(R.id.author)
    TextView penulis;

    @BindView(R.id.created)
    TextView created;

    @BindView(R.id.chapterDate)
    TextView chapterDate;

    @BindView(R.id.totalChapter)
    TextView totalChapter;

    @BindView(R.id.chapter)
    Spinner chapter;

    @BindView(R.id.read)
    Button read;


    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    private Presenter presenter;

    public DetailFragment(){

    }

    public static DetailFragment newInstance(String title, Presenter presenter){
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setPresenter(presenter);
        return fragment;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_chapter_list, container, false);
        ButterKnife.bind(this, view);
        fragmentManager = this.getActivity().getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        return view;
    }

    public void Create(MangaInfo manga){
        if(!manga.getImage().equals("")) {
            Glide.with(this).load("https://cdn.mangaeden.com/mangasimg/" + manga.getImage()).into(gambar);
        }
        else{
            gambar.setImageResource(R.drawable.noimage);
        }
        this.title.setText(manga.getTitle());
        this.artis.setText(manga.getArtist());
        this.penulis.setText(manga.getAuthor());
        this.deskripsi.setText(manga.getDesc());
        this.status.setText(manga.getStatus());
        this.category.setText(manga.getCategory());
        Date date =manga.getCreated();
        if(date!=null) {
            String temp = date+"";
            String[] tempsplit = temp.split(" ");
            this.created.setText(tempsplit[2] + " " + tempsplit[1] + " " + tempsplit[5]);
        }
        else{
            this.created.setText("-");
        }

        Date lastChapter = manga.getLast_chapter_date();
        if(lastChapter!=null) {
            String temp = lastChapter+"";
            String[] tempsplit = temp.split(" ");
            this.chapterDate.setText(tempsplit[2] + " " + tempsplit[1] + " " + tempsplit[5]);
        }
        else{
            this.chapterDate.setText("-");
        }

        this.totalChapter.setText(manga.getChapter().size()+"");
        ArrayList<String> numberChapter = new ArrayList<>();
        for(int i = 0; i<manga.getChapter().size(); i++){
            Date last_updated = manga.getChapter().get(i).getLastUpdated();
            String temp = last_updated+"";
            String[] tempsplit = temp.split(" ");
            if(manga.getChapter().get(i).getChapter().equals(manga.getChapter().get(i).getTitle())) {
                numberChapter.add(manga.getChapter().get(i).getChapter() + " (" + tempsplit[2] + " " + tempsplit[1] + " " + tempsplit[5] + ")");
            }
            else{
                numberChapter.add(manga.getChapter().get(i).getChapter() + " - " + manga.getChapter().get(i).getTitle() + " (" + tempsplit[2] + " " + tempsplit[1] + " " + tempsplit[5] + ")");
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item,numberChapter);
        this.chapter.setAdapter(adapter);

        this.read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = chapter.getSelectedItemPosition();
                Read(manga.getChapter().get(index).getId());
            }
        });

    }

    public void Read(String id){
        this.presenter.getMangaPage(id);
        this.presenter.changePage(3);
    }
}
