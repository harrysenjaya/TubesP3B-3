package com.example.tubesp3b_3;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements IMainActivity {

    @BindView(R.id.gridView)
    GridView grid;

    private Manga cek[];
    private ArrayList<Manga> manga;

    private FragmentManager fragmentManager;
    private FragmentTransaction ft;

    public MainFragment() {

    }

    public static MainFragment newInstance(String title) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("title", title);

        return fragment;
    }

//  public void setPresenter(MainPresenter presenter) {
//
//  }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        ButterKnife.bind(this, view);
        fragmentManager = this.getActivity().getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(), "you clicked"+ cek[+position].getId(), Toast.LENGTH_SHORT).show();
            }
            });


        PostCalculateTask postCalculateTask = new PostCalculateTask( this.getActivity(), this);
        postCalculateTask.getMangaList();
        return view;
    }

    public void adapter() {

    }


    @Override
    public void getMangaList(ArrayList<Manga> manga) {
        Log.d("Manga", manga.size() + "");

        this.manga = manga;
        gridAdapter adapter = new gridAdapter( this.getActivity(), this.manga);
        grid.setAdapter(adapter);
    }
}
