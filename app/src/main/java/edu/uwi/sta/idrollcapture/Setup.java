package edu.uwi.sta.idrollcapture;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uwi.sta.idrollcapture.Models.CourseContract;
import edu.uwi.sta.idrollcapture.Models.DBHelper;
import edu.uwi.sta.idrollcapture.Models.courses;

public class Setup extends AppCompatActivity {
int courseID=0;
    int dbnum=0;

    List<courses> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
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

        final EditText name_editText = (EditText) findViewById(R.id.name_editText);
        final EditText code_editText = (EditText) findViewById(R.id.code_editText);


        Button b = (Button) findViewById(R.id.btn_save);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coursename = name_editText.getText().toString();
                String coursecode = code_editText.getText().toString();
                String datecreated = DateFormat.getDateTimeInstance().format(new Date());

                 Boolean duplicatecheck = duplicateCheck(coursename,coursecode);
                if (duplicatecheck== false) {
                    Toast.makeText(Setup.this, "Course already there", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Setup.this, "Course not here ", Toast.LENGTH_SHORT).show();


                    DBHelper mDbHelper = new DBHelper(Setup.this);
                    // Gets the data repository in write mode
                    final SQLiteDatabase db = mDbHelper.getWritableDatabase();

                    DBHelper help = new DBHelper(getBaseContext());
                    courseList = help.getCourse();
//                courses[] customs = new courses[courseList.size()];
//                courseList.toArray(customs);
//                Object last = coursename;
//                for (courses str : courseList) {
//                    if (str.equals(last)) {
//                        Toast.makeText(Setup.this, "Course already exists"+coursename, Toast.LENGTH_SHORT).show();
//                    }
//                }
                    //Toast.makeText(Setup.this, "Course name going in: "+coursename, Toast.LENGTH_SHORT).show();


                    // Create a new map of values, where column names are the keys
                    ContentValues values = new ContentValues();
                    //values.put(CourseContract.CourseEntry.COLUMN_NAME_ID, courseID);
                    values.put(CourseContract.CourseEntry.COLUMN_NAME_COURSE_NAME, coursename);
                    values.put(CourseContract.CourseEntry.COLUMN_NAME_COURSE_CODE, coursecode);
                    values.put(CourseContract.CourseEntry.COLUMN_NAME_DATE_CREATED, datecreated);

                    //Toast.makeText(Setup.this,"Values.put area", Toast.LENGTH_SHORT).show();

// Insert the new row, returning the primary key value of the new row

                    final long newRowId = db.insert(CourseContract.CourseEntry.TABLE_NAME, null, values);
                    //dbnum++;
                    //courseID++;
                    //Toast.makeText(Setup.this,"db.insert area", Toast.LENGTH_SHORT).show();
                    if (newRowId != 0) {
                        Toast.makeText(Setup.this, "RowID:" + newRowId, Toast.LENGTH_SHORT).show();

                        Snackbar.make(v, "Course successfully added", Snackbar.LENGTH_LONG)
                                .setAction("Delete Course", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //SQLiteDatabase .delete("course", KEY_ID+"="+id, null);

                                        String sql = "DELETE FROM " +
                                                " course " +
                                                " WHERE " + "courseID" +
                                                " LIKE " + newRowId + ";";
                                        db.execSQL(sql);
                                        // dbnum--;
//                                    String selection = CourseContract.CourseEntry._ID + " LIKE ?";
//                                    String[] selectionArgs = { String.valueOf(newRowId) };
//                                    db.delete("course", selection, selectionArgs);
                                        Snackbar.make(v, "Course removed", Snackbar.LENGTH_LONG).show();
                                    }
                                })
                                .show();

                    }


                    name_editText.setText("");
                    code_editText.setText("");


                }
            }
        });
    }


    public boolean duplicateCheck(String coursename,String coursecode){
        DBHelper mDbHelper = new DBHelper(Setup.this);
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String selectQuery = "SELECT * FROM course where coursename = '"+ coursename + "'"+" and coursecode = '"+ coursecode + "' ; " ;
        Cursor cursor = db.rawQuery(selectQuery, null);
        List<String> checkList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String db_coursename = cursor.getString(0);
                String db_coursecode = cursor.getString(1);
                if(db_coursename.equals(coursename)&& db_coursecode.equals(coursecode)){
                    Toast.makeText(Setup.this, "comparision "+"\n"+"\n"+"\n" , Toast.LENGTH_SHORT).show();


                    return false;
                }
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return true;
    }

    }

