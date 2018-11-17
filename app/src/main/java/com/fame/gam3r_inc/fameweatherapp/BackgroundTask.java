package com.fame.gam3r_inc.fameweatherapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;
    String city;

    public AsyncResponse asyncResponse;

    public BackgroundTask(AsyncResponse asyncResponse){
        this.asyncResponse = asyncResponse;
    }


    protected String doInBackground(String... params) {


        try {
            city=params[0];



            String json_url = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=9d4d0c42e416497d0447685baa9dcb73";
            URL url = new URL(json_url);

            Log.i("url",json_url);

            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);



            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;


            while ((line = reader.readLine()) != null) {
                sb.append(line);
                break;
                }

            return sb.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {



        asyncResponse.processFinish(result);


        }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {

        super.onProgressUpdate(values);
    }
}

