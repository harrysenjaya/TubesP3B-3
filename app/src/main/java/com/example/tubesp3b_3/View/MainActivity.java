package com.example.tubesp3b_3.View;


import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tubesp3b_3.Interface.IMainActivity;
import com.example.tubesp3b_3.Model.Manga;
import com.example.tubesp3b_3.Model.MangaInfo;
import com.example.tubesp3b_3.Presenter.Presenter;
import com.example.tubesp3b_3.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements IMainActivity {
    private MainFragment mainFragment;
    private DetailFragment detailFragment;
    private FragmentManager fragmentManager;
    private Presenter presenter;
    private MangaFragment mangaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.presenter = new Presenter(this,this);
        this.mainFragment = MainFragment.newInstance("title",this.presenter);
        this.detailFragment = DetailFragment.newInstance("DetailFragment",this.presenter);
        this.mangaFragment = MangaFragment.newInstance("MangaFragment", this.presenter);
        this.fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(R.id.container, this.mainFragment).commit();
    }

    @Override
    public void changePage(int page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page==1){
            if(this.detailFragment.isAdded()){
                ft.hide(this.detailFragment);
            }
            if(this.mangaFragment.isAdded()){
                ft.hide(this.mangaFragment);
            }
            fragmentManager.popBackStack();
            if(this.mainFragment.isAdded()){
                ft.show(this.mainFragment);
            }
            else{
                ft.add(R.id.container,this.mainFragment).addToBackStack(null);
            }
        }
        else if(page==2){
            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }
            if(this.mangaFragment.isAdded()){
                ft.hide(this.mangaFragment);
            }
            fragmentManager.popBackStack();
            if(this.detailFragment.isAdded()){
                ft.show(this.detailFragment);
            }
            else{
                ft.add(R.id.container,this.detailFragment).addToBackStack(null);
            }

        }
        else if(page==3){
            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }
            if(this.detailFragment.isAdded()){
                ft.hide(this.detailFragment);
            }
            fragmentManager.popBackStack();
            if(this.mangaFragment.isAdded()){
                ft.show(this.mangaFragment);
            }
            else{
                ft.add(R.id.container,this.mangaFragment).addToBackStack(null);
            }
        }
        ft.commit();
    }

    @Override
    public void getMangaList(ArrayList<Manga> manga) {
        mainFragment.create(manga);
    }

    @Override
    public void getMangaInfo(MangaInfo manga) {
      detailFragment.create(manga);
    }

    @Override
    public void getMangaPage(ArrayList<String> manga) {
        mangaFragment.create(manga);
    }

}
