package com.example.tubesp3b_3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements FragmentListener, IMainActivity {
    MainFragment mainFragment;
    DetailFragment detailFragment;
    FragmentManager fragmentManager;
    DetailFragment detail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Manga[] duar=new Manga[manga.size()];
//        for(int i=0;i<100;i++){
//            Manga boom=new Manga(i,""+i,""+i);
//            duar[i]=boom;
//        }
//        cek=duar;

        this.mainFragment = MainFragment.newInstance("title",this);
        detail=DetailFragment.newInstance("DetailFragment",this);
        //this.detailFragment = DetailFragment.newInstance("title");
        this.fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(R.id.container, this.mainFragment).commit();

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),"you clickked"+ cek[+position].get(),Toast.LENGTH_LONG).show();
//
//            }
//        });

    }



    @Override
    public void changePage(int page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page==1){
            if(this.mainFragment.isAdded()){
                ft.show(this.mainFragment);
            }
            else{
                ft.add(R.id.container,this.mainFragment);
            }
            if(this.detail.isAdded()){
                ft.hide(this.detail);
            }
        }
        else if(page==2){
            if(this.detail.isAdded()){
                ft.show(this.detail);
            }
            else{
                ft.add(R.id.container,this.detail).addToBackStack(null);
            }
            if(this.mainFragment.isAdded()){
                ft.hide(this.mainFragment);
            }
        }
        ft.commit();
    }

    @Override
    public void getMangaList(ArrayList<Manga> manga) {
        Log.d("Manga", manga.size() + "");


        mainFragment.create(manga);
    }

    @Override
    public void getMangaInfo(MangaInfo manga) {
        detailFragment.create(manga);
    }
}
