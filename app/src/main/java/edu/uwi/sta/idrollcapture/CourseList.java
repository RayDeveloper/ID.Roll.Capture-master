package edu.uwi.sta.idrollcapture;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import edu.uwi.sta.idrollcapture.Models.CourseContract;
import edu.uwi.sta.idrollcapture.Models.CourseListAdapter;
import edu.uwi.sta.idrollcapture.Models.DBHelper;
import edu.uwi.sta.idrollcapture.Models.SqlHandler;
import edu.uwi.sta.idrollcapture.Models.courses;

public class CourseList extends AppCompatActivity {
    SqlHandler sqlHandler;
    List<courses> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        sqlHandler = new SqlHandler(this);

        ListView listView = (ListView) findViewById(R.id.courseList_view);
        DBHelper help=new DBHelper(getBaseContext());
        courseList =help.getCourse();
        CourseListAdapter adapter = new CourseListAdapter(CourseList.this, courseList);
        listView.setAdapter(adapter);
        //listView.setOnItemClickListener(this);
    }



}



