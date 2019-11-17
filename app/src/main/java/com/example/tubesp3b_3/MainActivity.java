package com.example.tubesp3b_3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements IMainActivity{
    GridView gridView;
    Manga cek[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        Manga[] duar=new Manga[100];
//        for(int i=0;i<100;i++){
//            Manga boom=new Manga(i,""+i,""+i);
//            duar[i]=boom;
//        }
//        cek=duar;
//        gridView=findViewById(R.id.gridView);
//
//        gridAdapter adapter= new gridAdapter(MainActivity.this,this.cek);
//        gridView.setAdapter(adapter);
//
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(),"you clickked"+ cek[+position].getNama(),Toast.LENGTH_LONG).show();
//
//            }
//        });


        PostCalculateTask postCalculateTask = new PostCalculateTask(this,this);
        postCalculateTask.getMangaList();
    }
}
