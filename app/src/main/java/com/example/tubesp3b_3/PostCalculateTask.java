package com.example.tubesp3b_3;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;


public class PostCalculateTask {
    protected final String BASE_URL = "https://www.mangaeden.com/api/";
    protected IMainActivity ui;
    protected Context context;
    protected Gson gson;

    public PostCalculateTask(Context context, IMainActivity ui){
        this.context = context;
        this.ui = ui;
        this.gson = new Gson();
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
                        String image = mangaJSON.getString("im");
                        String title = mangaJSON.getString("t");
                        String id = mangaJSON.getString("i");
                        String status = mangaJSON.getString("s");
                        String category = mangaJSON.getString("c");
                        Date last_chapter_date = null;
                        if(mangaJSON.has("ld") && mangaJSON.getString("ld")!= null) {
                            double tempdate = Double.parseDouble(mangaJSON.getString("ld"));
                            long date = (long)tempdate;
                            last_chapter_date = new Date(date);
                            Log.d("DATE",last_chapter_date+"");
                        }
                        int hits = Integer.parseInt(mangaJSON.getString("h"));
                        Manga manga = new Manga(image,title,id,status,category,last_chapter_date,hits);
                        mangaList.add(manga);
                    }
                    Log.d("LIST",mangaList.size()+"");
                    sendData(mangaList);
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

    public void sendData(ArrayList<Manga> manga){
        this.ui.getMangaList(manga);

    }


}
