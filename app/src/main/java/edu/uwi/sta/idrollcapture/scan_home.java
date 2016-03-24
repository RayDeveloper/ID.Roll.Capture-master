package edu.uwi.sta.idrollcapture;

/**
 * Created by Raydon on 3/18/2016.
 */
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

import java.util.Arrays;

public class scan_home extends AppCompatActivity  {
    Button button;
    String coursename;
    String coursecode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){
//        if (bundle.containsKey("coursename")) {
            coursename = bundle.getString("coursename");
             coursecode = bundle.getString("coursecode");

            TextView coursename_txtview = (TextView) findViewById(R.id.coursename_txtview);
            coursename_txtview.setText(coursename);
            TextView coursecode_txtview = (TextView) findViewById(R.id.coursecode_txtview);
            coursecode_txtview.setText(coursecode);


            // Toast.makeText(scan_home.this, coursename, Toast.LENGTH_SHORT).show();

        }


        final ImageButton StartCamera = (ImageButton) findViewById(R.id.StartCamera);
        StartCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(scan_home.this, ContinuousCaptureActivity.class);
                startActivity(intent);


            }


        });

        final ImageButton Register = (ImageButton) findViewById(R.id.Register);
        Register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(scan_home.this, Register.class);
                startActivity(intent);
                Bundle bundle = new Bundle();
                bundle.putString("coursename",coursename); // place the position of the selected item
                bundle.putString("coursecode", coursecode); // place the position of the selected item
                intent.putExtras(bundle);
                startActivity(intent);

//                Bundle b=MainActivity.this.getIntent().getExtras();
//                String[] array=b.getStringArray("ARRAY_LIST");
//                String str = Arrays.toString(array);
//                Toast.makeText(MainActivity.this,"Register so far\n"+str, Toast.LENGTH_SHORT).show();
            }

        });





//        final ImageButton Cam = (ImageButton) findViewById(R.id.Cam);
//
//
//        Cam.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, ContinuousCaptureActivity.class);
//                startActivity(intent);
//            }
//
//        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


//    boolean doubleBackToExitPressedOnce = false;

//    @Override
//    public void onBackPressed() {
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Press BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
//    }



}