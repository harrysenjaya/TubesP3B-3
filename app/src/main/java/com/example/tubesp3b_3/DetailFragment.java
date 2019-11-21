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

    @BindView(R.id.judul)
    TextView judul;

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

    @BindView(R.id.date)
    TextView date;

    @BindView(R.id.total)
    TextView total;

    @BindView(R.id.jumlahChapter)
    TextView jumlahChapter;

    @BindView(R.id.jumlah)
    TextView jumlah;

    @BindView(R.id.chapter)
    TextView chapter;

    @BindView(R.id.Spinner_chapter)
    Spinner spinner;

    @BindView(R.id.btnChapter)
    Button btn_Chapter;

    private FragmentManager fragmentManager;
    private FragmentTransaction ft;

    protected IMainActivity iMainActivity;

    public DetailFragment(){

    }

    public static DetailFragment newInstance(String title, IMainActivity iMainActivity){
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setiMainActivity(iMainActivity);
        return fragment;
    }

    public void setiMainActivity(IMainActivity iMainActivity){
        this.iMainActivity = iMainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_chapter_list, container, false);
        ButterKnife.bind(this, view);
        fragmentManager = this.getActivity().getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        API api = new API( this.getContext(), this.iMainActivity);
        api.getMangaDetail();

        return view;
    }

    public void create(MangaInfo manga){
        this.judul.setText(manga.getTitle());
    }
}
