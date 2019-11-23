package com.example.tubesp3b_3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment  {

    @BindView(R.id.gridView)
    GridView grid;

    @BindView(R.id.spinnerSort)
    Spinner spinnerSort;

    @BindView(R.id.search)
    SearchView search;

    @BindView(R.id.btnSort)
    Button btn_Sort;

    ArrayList<Manga> nAdapter;
    ArrayList<Manga> mAdapter;

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

    public void Create(ArrayList<Manga> manga){
      /*
        nAdapter.sort(new Comparator<Manga>() {
            @Override
            public int compare(Manga manga, Manga t1) {
                return manga.getTitle().compareTo(t1.getTitle());
            }
        });

        mAdapter.sort(new Comparator<Manga>() {
                          @Override
                          public int compare(Manga manga, Manga t1) {
                              return t1.getTitle().compareTo(manga.getTitle());
                          }
        });
*/

        GridAdapter adapter = new GridAdapter(this.getActivity(), manga);
        grid.setAdapter(adapter);

        String[] options = new String[] {"Hits", "A - Z", "Z - A"};
        ArrayAdapter<String> bAdapter = new ArrayAdapter<String>(this.getContext() , android.R.layout.simple_spinner_dropdown_item, options);
        this.spinnerSort.setAdapter(bAdapter);
       /* this.btn_Sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String spinnerT = spinnerSort.getSelectedItem().toString();

                if(spinnerT.equals("Hits")){
                    GridAdapter adapter = new GridAdapter(getActivity(), manga);
                    grid.setAdapter(adapter);
                }
                else if(spinnerT.equals("A - Z")){
                    newGrid1();
                    GridAdapter adapter = new GridAdapter(getActivity(), nAdapter);
                    grid.setAdapter(adapter);
                }
               else{
                    newGrid2(manga);
                    GridAdapter adapter = new GridAdapter(getActivity(), mAdapter);
                    grid.setAdapter(adapter);
                }

            }
        });*/
    }

   /* public void newGrid1() {
        Comparator<Manga> mangaName = new Comparator<Manga>() {
            @Override
            public int compare(Manga manga, Manga t1) {
                String m1 = manga.getTitle().toUpperCase();
                String m2 = t1.getTitle().toUpperCase();

                return m1.compareTo(m2);
            }
        };
    }
        /*nAdapter.sort(new Comparator<Manga>() {
            @Override
            public int compare(Manga manga, Manga t1) {
                return manga.getTitle().compareTo(t1.getTitle());
            }
        });*/


    public void newGrid2(ArrayList<Manga> manga){
        mAdapter.sort(new Comparator<Manga>() {
            @Override
            public int compare(Manga manga, Manga t1) {
                return t1.getTitle().compareTo(manga.getTitle());
            }
        });
    }



    public void MangaInfo(int id, int position){
        this.presenter.getMangaInfo(position);
        this.presenter.changePage(id);
    }
}
