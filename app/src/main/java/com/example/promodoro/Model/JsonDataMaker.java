package com.example.promodoro.Model;

import android.content.Context;
import android.util.JsonWriter;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.promodoro.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public String test(){

        String data = null;
        try{

            InputStream is = this.activity.getAssets().open("myData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            data = new String(buffer, "UTF-8");


        }catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

    public boolean writeJson(String data){
        try {
            /*OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.activity.openFileOutput("test.txt", this.activity.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();*/

            FileOutputStream fos = this.activity.openFileOutput("myData.json", this.activity.MODE_PRIVATE);
            fos.write(data.getBytes(StandardCharsets.UTF_8), 0, data.length());
            fos.close();


        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
            return false;
        }
        return true;

        /*String FILE_NAME = "myData.json";
        File file = new File(this.activity.getExternalFilesDir("internal"), FILE_NAME);
        FileOutputStream out = null;
        try{

            out = new FileOutputStream(file, true);
            JsonWriter f = new JsonWriter(new OutputStreamWriter(out));

            f.beginObject().name("id").value("c'est bon yes");

            f.endObject().close();
            f.flush();

        }catch (Exception e){

            e.printStackTrace();
            return false;

        }

        return true;*/
    }

}

