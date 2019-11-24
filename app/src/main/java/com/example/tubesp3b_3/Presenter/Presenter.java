package com.example.tubesp3b_3.Presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tubesp3b_3.Interface.IMainActivity;
import com.example.tubesp3b_3.Model.Chapter;
import com.example.tubesp3b_3.Model.Manga;
import com.example.tubesp3b_3.Model.MangaInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class Presenter {
    private IMainActivity iMainActivity;
    private Context context;

    protected final String BASE_URL = "https://www.mangaeden.com/api/";

    public Presenter(IMainActivity iMainActivity, Context context){
        this.iMainActivity = iMainActivity;
        this.context = context;
    }

    public void changePage(int id){
        this.iMainActivity.changePage(id);
    }

    public void getMangaList(){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url =BASE_URL+"list/0";
        final ArrayList<Manga> mangaList = new ArrayList();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrayManga = response.getJSONArray("manga");
                    for(int i = 0 ; i<arrayManga.length(); i++){
                        JSONObject mangaJSON = arrayManga.getJSONObject(i);
                        String image = "";
                        if(mangaJSON.has("im") && mangaJSON.getString("im")!= null && !mangaJSON.getString("im").equals("null")) {
                            image = mangaJSON.getString("im");
                        }
                        String title = mangaJSON.getString("t");
                        String id = mangaJSON.getString("i");
                        String status = mangaJSON.getString("s");
                        Date last_chapter_date = null;
                        if(mangaJSON.has("ld") && mangaJSON.getString("ld")!= null && !mangaJSON.getString("ld").equals("null")) {
                            double tempdate = Double.parseDouble(mangaJSON.getString("ld"));
                            long date = (long)tempdate;
                            last_chapter_date = new Date(date*1000);
                        }
                        int hits = Integer.parseInt(mangaJSON.getString("h"));
                        Manga manga = new Manga(image,title,id,status,last_chapter_date,hits);
                        mangaList.add(manga);
                    }
                    sendMangaList(mangaList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR","");
            }
        });
        queue.add(jsonObjectRequest);

    }

    public void getMangaInfo(String id){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url =BASE_URL+"manga/"+id;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject mangaJSON = response;
                    String image = "";
                    if(mangaJSON.has("image") && mangaJSON.getString("image")!= null && !mangaJSON.getString("image").equals("null")) {
                        image = mangaJSON.getString("image");
                    }
                    String title = mangaJSON.getString("title");
                    String artist = mangaJSON.getString("artist");
                    String author = mangaJSON.getString("author");
                    String desc = mangaJSON.getString("description");
                    String status = mangaJSON.getString("status");
                    JSONArray arrayCategory = mangaJSON.getJSONArray("categories");
                    String category = "";
                    for(int i = 0 ; i< arrayCategory.length();i++){
                        if(i== arrayCategory.length()-1){
                            category+=arrayCategory.getString(i);
                        }
                        else {
                            category += arrayCategory.getString(i) + ", ";
                        }
                    }

                    Date last_chapter_date = null;
                    if(mangaJSON.has("last_chapter_date") && mangaJSON.getString("last_chapter_date")!= null && !mangaJSON.getString("last_chapter_date").equals("null")) {
                        double tempdate = Double.parseDouble(mangaJSON.getString("last_chapter_date"));
                        long date = (long)tempdate;
                        last_chapter_date = new Date(date*1000);
                    }
                    Date created = null;
                    if(mangaJSON.has("created") && mangaJSON.getString("created")!= null && !mangaJSON.getString("created").equals("null")) {
                        double tempdate = Double.parseDouble(mangaJSON.getString("created"));
                        long date = (long)tempdate;
                        created = new Date(date*1000);
                    }

                    JSONArray arrayChapter = mangaJSON.getJSONArray("chapters");
                    ArrayList<Chapter> chapters = new ArrayList<>();

                    for(int i = 0 ; i<arrayChapter.length(); i++) {
                        JSONArray chapter = arrayChapter.getJSONArray(i);
                        String chapterNumber = chapter.getString(0);
                        Date chapterDate = null;
                        double tempdate = Double.parseDouble(chapter.getString(1));
                        long date = (long)tempdate;
                        chapterDate = new Date(date*1000);
                        String chapterTitle = chapter.getString(2);
                        String id = chapter.getString(3);
                        Chapter tempChapter = new Chapter(chapterNumber,chapterDate,chapterTitle,id );
                        chapters.add(tempChapter);
                    }
                    MangaInfo mangaInfo = new MangaInfo(image, title, artist, author, desc, status, category, created, last_chapter_date, chapters);
                    sendMangaInfo(mangaInfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR","");
            }
        });
        queue.add(jsonObjectRequest);
    }

    public void getMangaPage(String id){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url =BASE_URL+"chapter/"+id;
        final ArrayList<String> image = new ArrayList();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray arrayImage = response.getJSONArray("images");
                    for(int i = arrayImage.length()-1 ; i>=0; i--){
                        image.add(arrayImage.getJSONArray(i).getString(1));
                    }
                    sendMangaPage(image);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR","");
            }
        });
        queue.add(jsonObjectRequest);

    }

    public void sendMangaList(ArrayList<Manga> manga){
        this.iMainActivity.getMangaList(manga);
    }

    public void sendMangaInfo(MangaInfo manga){
        this.iMainActivity.getMangaInfo(manga);
    }

    public void sendMangaPage(ArrayList<String> manga){
        this.iMainActivity.getMangaPage(manga);
    }



}
