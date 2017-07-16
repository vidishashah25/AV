package com.example.admin.av;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> apis = new ArrayList<>();

    RecyclerView rvVersions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvVersions = (RecyclerView) findViewById(R.id.rvVersions);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(MainActivity.this);

        rvVersions.setLayoutManager(lm);

        String s = ReadFromfile("versions.json", MainActivity.this);

        convertStringToJSON(s);

    }

    public void convertStringToJSON(String s) {

        Log.d("MainActivity","convertString");


        try {

            Log.d("MainActivity","try");

            JSONObject mainobject = new JSONObject(s);
            JSONObject versionObject = mainobject.getJSONObject("AndroidVersions");
            JSONArray versionArray = versionObject.getJSONArray("VersionsList");

            for (int i = 0; i < versionArray.length(); i++) {

                Log.d("MainActivity","for loop");

                JSONObject currentObject = versionArray.getJSONObject(i);
                String versionName = currentObject.getString("Name");
                String versionApi = currentObject.getString("Api");

                names.add(versionName);
                apis.add(versionApi);
            }

            VersionData adapter = new VersionData(MainActivity.this,names,apis);
            rvVersions.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();

            Log.d("MainActivity","catch");

        }

    }


    public String ReadFromfile(String fileName, Context context) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets()
                    .open(fileName, Context.MODE_WORLD_READABLE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();


    }
}
