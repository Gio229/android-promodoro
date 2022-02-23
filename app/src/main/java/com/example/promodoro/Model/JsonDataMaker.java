package com.example.promodoro.Model;

import androidx.appcompat.app.AppCompatActivity;

import com.example.promodoro.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonDataMaker {

    private AppCompatActivity activity;

    public JsonDataMaker(AppCompatActivity activity){
        this.activity = activity;
    }

    public ArrayList<TafModel> getJsonData(){

        ArrayList<TafModel> tafList = new ArrayList<TafModel>();
        String data = null;
        try{

            InputStream is = this.activity.getAssets().open("myData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            data = new String(buffer, "UTF-8");

            JSONArray myActivities = new JSONArray(data);

            tafList = new Gson().fromJson(myActivities.toString(), new TypeToken<List<TafModel>>(){}.getType());

        }catch (IOException | JSONException e){
            e.printStackTrace();
        }
        return tafList;
    }

    public TafModel getJsonOneActivity(int index){

        ArrayList<TafModel> tafList = new ArrayList<TafModel>();
        TafModel myActivity = null;
        String data = null;
        try{

            InputStream is = this.activity.getAssets().open("myData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            data = new String(buffer, "UTF-8");

            JSONArray myActivities = new JSONArray(data);

            JSONObject myActivityJSON = myActivities.getJSONObject(index);

            myActivity = new Gson().fromJson(myActivityJSON.toString(), TafModel.class);

        }catch (IOException | JSONException e){
            e.printStackTrace();
        }
        return myActivity;
    }

}
