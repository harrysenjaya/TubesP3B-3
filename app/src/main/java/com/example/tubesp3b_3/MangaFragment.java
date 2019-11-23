package com.example.tubesp3b_3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MangaFragment extends Fragment {

    @BindView(R.id.imageSlider)
    SliderView sliderView;

    private Presenter presenter;
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    private int counter = 0;

    public MangaFragment(){

    }

    public static MangaFragment newInstance(String title, Presenter presenter){
        MangaFragment fragment = new MangaFragment();
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
        View view = inflater.inflate(R.layout.fragment_manga, container, false);
        ButterKnife.bind(this, view);
        fragmentManager = this.getActivity().getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        return view;
    }

    public void Create(ArrayList<String> manga){
        SliderAdapter adapter = new SliderAdapter(this.getContext(),manga);
        sliderView.setSliderAdapter(adapter);    }


}
