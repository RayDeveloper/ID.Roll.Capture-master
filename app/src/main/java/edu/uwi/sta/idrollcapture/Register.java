package edu.uwi.sta.idrollcapture;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    String coursename;
    String coursecode;
    TextView coursename_txtview;
    TextView coursecode_txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        Bundle bundle = getIntent().getExtras();
        if(bundle !=null) {
//        if (bundle.containsKey("coursename")) {
             coursename = bundle.getString("coursename");
             coursecode = bundle.getString("coursecode");

             coursename_txtview = (TextView) findViewById(R.id.coursename_reg);
            coursename_txtview.setText(coursename);
             coursecode_txtview = (TextView) findViewById(R.id.coursecode_reg);
            coursecode_txtview.setText(coursecode);
        }
    }


}
