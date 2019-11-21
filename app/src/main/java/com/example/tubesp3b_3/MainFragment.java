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

public class MainFragment extends Fragment  {

    @BindView(R.id.gridView)
    GridView grid;

    private ArrayList<Manga> manga;

    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    public FragmentListener listener;

    protected IMainActivity iMainActivity;
    public MainFragment() {

    }

    public static MainFragment newInstance(String title, IMainActivity iMainActivity) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setInterface(iMainActivity);
        return fragment;
    }

  public void setInterface(IMainActivity iMainActivity) {
    this.iMainActivity = iMainActivity;
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
//                Toast.makeText(view.getContext(), "you clicked"+ cek[+position].getId(), Toast.LENGTH_SHORT).show();
                listener.changePage(2);
            }
            });

        API api = new API( this.getContext(), this.iMainActivity);
        api.getMangaList();
        return view;
    }

    public void adapter() {

    }

    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.listener=(FragmentListener) context;
        }
        else {
            throw new ClassCastException(context.toString()+"must implement FragmentListener");
        }
    }

    public void create(ArrayList<Manga> manga){
        this.manga = manga;
        gridAdapter adapter = new gridAdapter( this.getActivity(), this.manga);
        grid.setAdapter(adapter);

    }
}
