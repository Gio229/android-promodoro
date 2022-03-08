package com.example.promodoro.Model;

import android.content.Context;
import android.os.Environment;
import android.util.JsonWriter;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.promodoro.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonDataMaker {

    private static final String FILENAME = "myData.json";

    private static final String FOLDERNAME = "dataPromodoro";

    private File directory;

    private AppCompatActivity activity;

    public JsonDataMaker(AppCompatActivity activity){
        this.activity = activity;
        directory = activity.getFilesDir();
    }

    public ArrayList<TafModel> getJsonData(){


        ArrayList<TafModel> tafList = new ArrayList<TafModel>();
        String data = getTextFromStorage();

        try{

            if(data != null){
                JSONArray myActivities = new JSONArray(data);

                tafList = new Gson().fromJson(myActivities.toString(), new TypeToken<List<TafModel>>(){}.getType());
            }



        }catch (Exception e){
            e.printStackTrace();
        }
        return tafList;
    }

    public TafModel getJsonOneActivity(int index){

        ArrayList<TafModel> tafList = new ArrayList<TafModel>();
        TafModel myActivity = null;
        String data = getTextFromStorage();
        try{

            /*InputStream is = this.activity.getAssets().open("myData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            data = new String(buffer, "UTF-8");*/

            /*

            */

            JSONArray myActivities = new JSONArray(/*this.getTextFromStorage()*/data);

            JSONObject myActivityJSON = myActivities.getJSONObject(index);

            myActivity = new Gson().fromJson(myActivityJSON.toString(), TafModel.class);

        }catch (Exception e){
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
            outputStreamWriter.close();

            FileOutputStream fos = this.activity.openFileOutput("myData.json", this.activity.MODE_PRIVATE);
            fos.write(data.getBytes(StandardCharsets.UTF_8), 0, data.length());
            fos.close();*/

            this.setTextInStorage(data);


        }
        catch (Exception e) {
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

    /*-----------------------------------------------------------*/



    private static File createOrGetFile(File destination, String fileName, String folderName){

        File folder = new File(destination, folderName);

        return new File(folder, fileName);
    }


    // ----------------------------------
    // READ & WRITE ON STORAGE
    // ----------------------------------

    private  String readOnFile(Context context, File file){

        String result = null;
        if (file.exists()) {
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(file));
                try {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();
                    while (line != null) {
                        sb.append(line);
                        sb.append("\n");
                        line = br.readLine();
                    }
                    result = sb.toString();
                }
                finally {
                    br.close();
                }
            }
            catch (IOException e) {
                //Toast.makeText(context, context.getString(R.string.error_happened), Toast.LENGTH_LONG).show();
            }
            /*try{
                InputStream is = new FileInputStream(file);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                result = new String(buffer, "UTF-8");

            }catch (Exception e){

            }*/
        }

        return result;
    }

    // ---

    private static void writeOnFile(Context context, String text, File file){

        try {
            file.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(file);
            Writer w = new BufferedWriter(new OutputStreamWriter(fos));

            try {
                w.write(text);
                w.flush();
                fos.getFD().sync();
            } finally {
                w.close();
                //Toast.makeText(context, context.getString(R.string.saved), Toast.LENGTH_LONG).show();
            }

        } catch (IOException e) {
            //Toast.makeText(context, context.getString(R.string.error_happened), Toast.LENGTH_LONG).show();
        }
    }


    public String getTextFromStorage(){


        File file = createOrGetFile(this.directory, this.FILENAME, this.FOLDERNAME);


        return readOnFile(this.activity, file);

    }

    public  void setTextInStorage(String text){


        File file = createOrGetFile(this.directory, this.FILENAME, this.FOLDERNAME);


        writeOnFile(this.activity, text, file);

    }


    public File getDirectory() {
        return directory;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }
}

