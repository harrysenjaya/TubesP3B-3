package com.example.tubesp3b_3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentWelcome extends Fragment {
    @BindView(R.id.sasuke)
    ImageView gambarAtas;

    @BindView(R.id.naruto)
    ImageView gambarBawah;

    @BindView(R.id.tulisanManga)
    ImageView tulisan;

    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    private Presenter presenter;

    public FragmentWelcome(){

    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public static FragmentWelcome newInstance(String title, Presenter presenter){
        FragmentWelcome fragment = new FragmentWelcome();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setPresenter(presenter);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.welcome_screen, container, false);
        ButterKnife.bind(this, view);
        fragmentManager = this.getActivity().getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        return view;
    }



}
