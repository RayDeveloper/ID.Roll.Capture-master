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
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import edu.uwi.sta.idrollcapture.Models.CourseContract;
import edu.uwi.sta.idrollcapture.Models.DBHelper;

public class CourseList extends AppCompatActivity {
String[] CourseName;
String[] CourseCode;
    private ListView lv;

    private ArrayAdapter<Map> adapter;
    private ArrayList<Map> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        setUpAdapter();
        loadData();
        Toast.makeText(CourseList.this,"After loadData()", Toast.LENGTH_LONG).show();

        setUpUI();


        setUpAdapterListeners();

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//                Toast.makeText(CourseList.this,"Welcome", Toast.LENGTH_LONG).show();

//        //dbArray=getCourses();
//        //String str = Arrays.toString(dbArray);
//        DBHelper mDbHelper = new DBHelper(CourseList.this);
//        SQLiteDatabase db = mDbHelper.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT coursename,coursecode FROM course", null);
//        //int count = cursor.getColumnCount();
//        ArrayList<String> name = new ArrayList<String>();
//        ArrayList<String> code = new ArrayList<String>();
//
//        Toast.makeText(CourseList.this,"About to enter while loop cursor", Toast.LENGTH_LONG).show();
//
//        while (cursor != null && cursor.moveToFirst()) {
//            name.add(cursor.getString(cursor.getColumnIndex("coursename")));
//            code.add(cursor.getString(cursor.getColumnIndex("coursecode")));
//
//            cursor.moveToNext();
//        }
//        cursor.close();
//        String[]toastName=name.toArray(new String[name.size()]);
//        String str = Arrays.toString(toastName);
//        String[]toastCode=code.toArray(new String[code.size()]);
//        String str2 = Arrays.toString(toastCode);
//
//        Toast.makeText(CourseList.this,str, Toast.LENGTH_LONG).show();
//        Toast.makeText(CourseList.this,str2, Toast.LENGTH_LONG).show();


//        ListView lv = (ListView)findViewById(R.id.courseList_view);
//        VivzAdapter adapter=new VivzAdapter(CourseList.this,name.toArray(new String[name.size()]),code.toArray(new String[code.size()]));
//        lv.setAdapter(adapter);


   }
    protected void setUpAdapterListeners(){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.i("ItemsActivity", "Click Detected");
//                Intent i = new Intent(CourseList.this, ItemDetailActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("itemid", position); // place the position of the selected item
//                i.putExtras(bundle);
//                startActivity(i);
            }
        });

    }
    protected void setUpAdapter(){
        VivzAdapter adapter = new VivzAdapter(CourseList.this,items);
        lv.setAdapter(adapter);

    }

    protected void setUpUI(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         lv=(ListView)findViewById(R.id.courseList_view);
        VivzAdapter adapter = new VivzAdapter(CourseList.this,items);
        lv.setAdapter(adapter);

//        // Implement action to direct to cart when action button pressed
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ItemsActivity.this, CartActivity.class));
//            }
//        });

    }

    protected  void loadData(){
        final Context context = this;
        Toast.makeText(this,"Inside loadData()",Toast.LENGTH_SHORT).show();

        Thread thd = new Thread(new Runnable(){
            public void run(){
                DBHelper mDbHelper = new DBHelper(CourseList.this);
                SQLiteDatabase db = mDbHelper.getReadableDatabase();
                ListView lv = (ListView)findViewById(R.id.courseList_view);
                ArrayList<Map> items = new ArrayList<>();
                // Retrieve Values from Database
                Cursor i = db.query(CourseContract.CourseEntry.TABLE_NAME,
                        new String[]{CourseContract.CourseEntry.COLUMN_NAME_COURSE_NAME,
                                CourseContract.CourseEntry.COLUMN_NAME_COURSE_CODE,
                                CourseContract.CourseEntry.COLUMN_NAME_DATE_CREATED,
                                },
                        null,null,null,null, null);
                while (i.moveToNext()){
                   // int id = i.getInt(i.getColumnIndex(CourseContract.CourseEntry.COLUMN_NAME_ID));
                    String course_name = i.getString(i.getColumnIndex(CourseContract.CourseEntry.COLUMN_NAME_COURSE_NAME));
                    String course_code = i.getString(i.getColumnIndex(CourseContract.CourseEntry.COLUMN_NAME_COURSE_CODE));
                    String datecreated = i.getString(i.getColumnIndex(CourseContract.CourseEntry.COLUMN_NAME_DATE_CREATED));

                    // Store data in a single map, so each map has data about one item
                    Map map = new HashMap();
                    map.put("course_name", course_name );
                    map.put("course_code", course_code);
                    map.put("datecreated", datecreated);
                    // Add the map containing individual item data into the arraylist
                    items.add(map);

                }
                i.close();
                lv.post(new Runnable(){
                    public void run(){
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });

        thd.start();
    }



    }


class VivzAdapter extends ArrayAdapter<Map> {
    Context context;
    int[] images;
    String[] CourseName;
    String[] CourseCode;
    String[] des;
    VivzAdapter(Context c,ArrayList<Map> courses){
         super(c, 0, courses);
    }


    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View row=inflater.inflate(R.layout.course_view, parent, false);
        View row = LayoutInflater.from(getContext()).inflate(R.layout.course_view, null);
        Map item = getItem(position);
        String code = (String)item.get("course_code");
        String name = (String)item.get("course_name");
        //String price = (String)item.get("datecreated");

               Toast.makeText(getContext(),"INside getView", Toast.LENGTH_LONG).show();


        TextView Course_Name=(TextView) row.findViewById(R.id.courseview);
        TextView Course_Code=(TextView) row.findViewById(R.id.codeview);

        Course_Name.setText(name);
        Course_Code.setText(code);


        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(context, SelectDate.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("itemid", position); // place the position of the selected item
//                i.putExtras(bundle);
//                context.startActivity(i);
//                //Toast.makeText(context,des[position], Toast.LENGTH_LONG).show();
            }
        });

        return row;
    }


}
