package com.verity.datlashiv.acqent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CActivity extends AppCompatActivity  {

    private RecyclerView recyclerView;
    private ArrayList<Model> models;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);

        getSupportActionBar().setTitle("C Tutorials");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        models = new ArrayList<>();


        try {
            JSONObject jsonObject = new JSONObject(RawJson());
            JSONArray jsonArray = jsonObject.getJSONArray("acqent");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                JSONArray array = object.getJSONArray("c_concepts");
                for (int j = 0; j < array.length(); j++) {
                    JSONObject object1 = array.getJSONObject(j);

                    models.add(new Model(
                            object1.getString("name")
                    ));
                }
                //Log.i("Name", object.getString("name"));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new RecyclerViewAdapter(getApplicationContext(), models);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(CActivity.this, FullScreen.class);
                String na = models.get(position).getName().toString();
                intent.putExtra("names", na);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


    }
    private String RawJson() {
        String JSONString = null;
        StringBuilder builder = new StringBuilder();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.course);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((JSONString = bufferedReader.readLine()) != null) {
                builder.append(JSONString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(builder);
    }
}
