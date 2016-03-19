package edu.uwi.sta.idrollcapture;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


import edu.uwi.sta.idrollcapture.Models.CourseContract;
import edu.uwi.sta.idrollcapture.Models.CourseListAdapter;
import edu.uwi.sta.idrollcapture.Models.DBHelper;
import edu.uwi.sta.idrollcapture.Models.SqlHandler;
import edu.uwi.sta.idrollcapture.Models.courses;

public class CourseList extends AppCompatActivity {
    SqlHandler sqlHandler;
    List<courses> courseList;
    TextView coursename_view;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {

                 coursename_view = (TextView) findViewById(R.id.coursename_txtview);
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

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int pos, long id) {
                int newpos=pos+1;

                DBHelper mDbHelper = new DBHelper(CourseList.this);
                final SQLiteDatabase db = mDbHelper.getWritableDatabase();

                String sql = "DELETE FROM " +
                        " course " +
                        " WHERE " +"courseID"+
                        " LIKE " + newpos + ";";
                db.execSQL(sql);
                restartActivity();
                //Toast.makeText(CourseList.this,"Course deleted at :\n"+"POS: "+newpos+"\n"+"ID: "+id, Toast.LENGTH_SHORT).show();

                Toast.makeText(CourseList.this,"Course deleted at:"+newpos, Toast.LENGTH_SHORT).show();


                return true;
            }
        });
    }

    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}



