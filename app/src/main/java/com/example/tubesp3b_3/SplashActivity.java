package com.example.tubesp3b_3;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
    private final int timer = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);
        //startHeavyProcess();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            }, timer);
    }

   /* private void startHeavyProcess(){
        new longOperate().execute("");
    }

    private class longOperate extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params){
            for(int i=0; i<5; i++){
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    Thread.interrupted();
                }
            }
            return "";
        }

        @Override
        protected void onPostExecute(String res){
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        protected void onPreExecute(){}
        @Override
        protected void onProgressUpdate(Void... values){}

    }*/
}