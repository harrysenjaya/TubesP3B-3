package com.example.tubesp3b_3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    @BindView(R.id.gambar)
    ImageView gambar;

    @BindView(R.id.deskription)
    TextView deskription;

    @BindView(R.id.deskripsi)
    TextView deskripsi;

    @BindView(R.id.author)
    TextView author;

    @BindView(R.id.penulis)
    TextView penulis;

    @BindView(R.id.artist)
    TextView artist;

    @BindView(R.id.artis)
    TextView artis;

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.status)
    TextView status;

    @BindView(R.id.setatus)
    TextView setatus;

    @BindView(R.id.category)
    TextView category;

    @BindView(R.id.kategori)
    TextView kategori;

    @BindView(R.id.created)
    TextView created;

    @BindView(R.id.dibuat)
    TextView dibuat;

    @BindView(R.id.chapterDate)
    TextView chapterDate;

    @BindView(R.id.tanggalBab)
    TextView tanggalBab;

    @BindView(R.id.totalChapter)
    TextView totalChapter;

    @BindView(R.id.jumlahChapter)
    TextView jumlahChapter;

    @BindView(R.id.chapter)
    TextView chapter;

    @BindView(R.id.bab)
    TextView bab;

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

    }
}
