package com.example.tubesp3b_3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment {

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
        this.et_Search.setEnabled(false);
        this.presenter.getMangaList();

        return view;
    }

    public void create(ArrayList<Manga> manga){
        GridAdapter adapter = new GridAdapter(this.getActivity(), manga);
        grid.setAdapter(adapter);

        adapter.setOnItemClick(new OnItemClick() {
            @Override
            public void onItemClick(Manga manga) {
                mangaInfo(2, manga.getId());
            }
        });

        this.btn_Sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = spinnerSort.getSelectedItem().toString();
                if(text.equals("Hits")){
                    adapter.sortByHits();
                }
                else if(text.equals("A - Z")){
                    adapter.sortByAtoZ();
                }
                else{
                    adapter.sortByZtoA();
                }
            }
        });

        et_Search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newText) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Manga> temp = adapter.searchManga(newText);
                adapter.setItem(temp);
                return false;
            }
        });

    }

    public void mangaInfo(int id, String idManga){
        this.presenter.getMangaInfo(idManga);
        this.presenter.changePage(id);
    }


}
