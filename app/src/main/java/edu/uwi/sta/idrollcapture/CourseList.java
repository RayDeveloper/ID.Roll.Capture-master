package edu.uwi.sta.idrollcapture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


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

        final ListView listView = (ListView) findViewById(R.id.courseList_view);
        DBHelper help=new DBHelper(getBaseContext());
        courseList =help.getCourse();
        CourseListAdapter adapter = new CourseListAdapter(CourseList.this, courseList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,long arg3){

                TextView coursename_view = (TextView) findViewById(R.id.coursename_txtview);
                TextView coursecode_view = (TextView) findViewById(R.id.coursecode_txtview);

                //textView.getText().toString();
                Intent i = new Intent(CourseList.this, scan_home.class);
                Bundle bundle = new Bundle();
                bundle.putString("coursename", coursename_view.getText().toString()); // place the position of the selected item
                bundle.putString("coursecode", coursecode_view.getText().toString()); // place the position of the selected item

                i.putExtras(bundle);
                startActivity(i);


                //int value = (int)adapter.getItemAtPosition(position);s
//                String selectedFromList =(String) (adapter.getItemAtPosition(position));
                //Toast.makeText(CourseList.this,"Pos:"+position, Toast.LENGTH_SHORT).show();
                //Toast.makeText(CourseList.this,coursecode_view.getText().toString(), Toast.LENGTH_SHORT).show();


                // assuming string and if you want to get the value on click of list item
                // do what you intend to do on click of listview row
            }
        });
    }



}



