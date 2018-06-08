package com.verity.datlashiv.acqent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private ArrayList<Model> models;
    private RecyclerMainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        models = new ArrayList<>();


        try {
            JSONObject jsonObject = new JSONObject(RawJson());
            JSONArray jsonArray = jsonObject.getJSONArray("acqent");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                JSONArray array = object.getJSONArray("course_name");
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

        adapter = new RecyclerMainAdapter(getApplicationContext(), models);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                switch (position) {
                    case 0:
                        Intent javaIntent = new Intent(MainActivity.this, JavaActivity.class);
                        startActivity(javaIntent);
                        break;
                    case 1:
                        Intent phpIntent = new Intent(MainActivity.this, PhpActivity.class);
                        startActivity(phpIntent);
                        break;
                    case 2:
                        Intent cIntent = new Intent(MainActivity.this, CActivity.class);
                        startActivity(cIntent);
                        break;
                    case 3:
                        Intent csIntent = new Intent(MainActivity.this, CsActivity.class);
                        startActivity(csIntent);
                        break;
                    case 4:
                        Intent pythonIntent = new Intent(MainActivity.this, PythonActivity.class);
                        startActivity(pythonIntent);
                        break;
                    case 5:
                        Intent javaSIntent = new Intent(MainActivity.this, JavaScriptActivity.class);
                        startActivity(javaSIntent);
                        break;
                    case 6:
                        Intent htmlIntent = new Intent(MainActivity.this, HtmlActivity.class);
                        startActivity(htmlIntent);
                        break;

                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
