package com.example.tubesp3b_3.Interface;

import com.example.tubesp3b_3.Model.Manga;
import com.example.tubesp3b_3.Model.MangaInfo;

import java.util.ArrayList;

public interface IMainActivity {
    void changePage(int page);
    void getMangaList(ArrayList<Manga> manga);
    void getMangaInfo(MangaInfo manga);
    void getMangaPage(ArrayList<String> manga);
}
