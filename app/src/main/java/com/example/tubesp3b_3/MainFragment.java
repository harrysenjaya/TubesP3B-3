package com.example.tubesp3b_3;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment  {

    @BindView(R.id.gridView)
    GridView grid;

    @BindView(R.id.btnSort)
    Button btn_Sort;

    @BindView(R.id.etSearch)
    SearchView et_Search;

    @BindView(R.id.sortSpinner)
    Spinner spinnerSort;



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

        this.et_Search.setEnabled(false);
        this.presenter.getMangaList();


        return view;
    }

    public void Create(ArrayList<Manga> manga){
        GridAdapter adapter = new GridAdapter(this.getActivity(), manga);
        grid.setAdapter(adapter);


        this.btn_Sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = spinnerSort.getSelectedItem().toString();

                if(text.equals("Hits")){
                    presenter.sortByHits(manga);
                    GridAdapter adapter1 = new GridAdapter(getActivity(), manga);
                    grid.setAdapter(adapter1);
                }
                else if(text.equals("A - Z")){
                    presenter.sortByAtoZ(manga);
                    GridAdapter adapter1 = new GridAdapter(getActivity(), manga);
                    grid.setAdapter(adapter1);
                }
                else{
                    presenter.sortByZtoA(manga);
                    GridAdapter adapter1 = new GridAdapter(getActivity(), manga);
                    grid.setAdapter(adapter1);
                }
            }
        });

        et_Search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                GridAdapter adapter2 = new GridAdapter(getActivity(), presenter.searchManga(newText));
                grid.setAdapter(adapter2);
                return false;
            }
        });

    }

    public void MangaInfo(int id, int position){
        this.presenter.getMangaInfo(position);
        this.presenter.changePage(id);
    }

}
