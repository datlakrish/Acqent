package com.verity.datlashiv.acqent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class FullScreen extends AppCompatActivity {

    private TextView text;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen);

        getSupportActionBar().setTitle("FullScreen");


        text = findViewById(R.id.textsss);

        Intent intent = getIntent();
        String name = intent.getStringExtra("names");

        text.setText(name);

    }
}
