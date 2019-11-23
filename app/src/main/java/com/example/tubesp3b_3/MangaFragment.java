package com.example.tubesp3b_3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MangaFragment extends Fragment {
    private Chapter ch;

    @BindView(R.id.mangaPhoto)
    PhotoView mangaPhoto;

    ViewPager viewPager;
    private Presenter presenter;

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
        LinearLayout view =(LinearLayout) inflater.inflate(R.layout.fragment_manga, container, false);

        this.mangaPhoto.setImageResource(R.drawable.noimage);
        this.viewPager = new ViewPager((this.getActivity())){
            @Override
            public boolean onInterceptTouchEvent(MotionEvent ev){
                try{
                    return super.onInterceptTouchEvent(ev);
                } catch (Exception e){
                    return false;
                }
            }
            };
        view.addView(this.viewPager);
        return view;
    }


}
