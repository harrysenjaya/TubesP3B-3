package com.example.tubesp3b_3;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements IMainActivity {
    MainFragment mainFragment;
    DetailFragment detailFragment;
    FragmentManager fragmentManager;
    Presenter presenter;
    MangaFragment mangaFragment;

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
        changePage(1);

    }

    @Override
    public void changePage(int page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page==1){
            if(this.mainFragment.isAdded()){
                ft.show(this.mainFragment);
            }
            else{
                ft.add(R.id.container,this.mainFragment).addToBackStack(null);
            }
            if(this.detailFragment.isAdded()){
                ft.hide(this.detailFragment);
            }
            if(this.mangaFragment.isAdded()){
                ft.hide(this.mangaFragment);
            }
        }
        else if(page==2){
            if(this.detailFragment.isAdded()){
                ft.show(this.detailFragment);
            }
            else{
                ft.add(R.id.container,this.detailFragment).addToBackStack(null);
            }
            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }
            if(this.mangaFragment.isAdded()){
                ft.hide(this.mangaFragment);
            }
        }
        else if(page==3){
            if(this.mangaFragment.isAdded()){
                ft.show(this.mangaFragment);
            }
            else{
                ft.add(R.id.container,this.mangaFragment).addToBackStack(null);
            }
            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }
            if(this.detailFragment.isAdded()){
                ft.hide(this.detailFragment);
            }
        }

        ft.commit();
    }

    @Override
    public void getMangaList(ArrayList<Manga> manga) {
        mainFragment.Create(manga);
    }

    @Override
    public void getMangaInfo(MangaInfo manga) {
      detailFragment.Create(manga);
    }

    @Override
    public void getMangaPage(ArrayList<String> manga) {
        mangaFragment.Create(manga);
    }

}
