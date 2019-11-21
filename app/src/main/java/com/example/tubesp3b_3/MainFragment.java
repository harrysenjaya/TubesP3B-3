package com.example.tubesp3b_3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment  {

    @BindView(R.id.gridView)
    GridView grid;

    private ArrayList<Manga> manga;
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    private Presenter presenter;

    public MainFragment() {

    }

    public static MainFragment newInstance(String title, Presenter presenter) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setPresenter(presenter);
        return fragment;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        fragmentManager = this.getActivity().getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MangaInfo(2,position);
            }
        });
        this.presenter.getMangaList();
        return view;
    }


//    public void onAttach(Context context){
//        super.onAttach(context);
//        if(context instanceof FragmentListener){
//            this.listener=(FragmentListener) context;
//        }
//        else {
//            throw new ClassCastException(context.toString()+"must implement FragmentListener");
//        }
//    }

    public void Create(ArrayList<Manga> manga){
        this.manga = manga;
        GridAdapter adapter = new GridAdapter(this.getActivity(), this.manga);
        grid.setAdapter(adapter);
    }

    public void MangaInfo(int id, int position){
        this.presenter.getMangaInfo(manga.get(position).getId());
        this.presenter.changePage(id);
    }
}
