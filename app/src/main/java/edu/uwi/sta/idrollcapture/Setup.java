package edu.uwi.sta.idrollcapture;

import android.content.ContentValues;
import android.content.Intent;
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
import java.util.Date;

import edu.uwi.sta.idrollcapture.Models.CourseContract;
import edu.uwi.sta.idrollcapture.Models.DBHelper;

public class Setup extends AppCompatActivity {
int courseID=0;
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


                DBHelper mDbHelper = new DBHelper(Setup.this);
                // Gets the data repository in write mode
                final SQLiteDatabase db = mDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                //values.put(CourseContract.CourseEntry.COLUMN_NAME_ID, courseID);
                values.put(CourseContract.CourseEntry.COLUMN_NAME_COURSE_NAME, coursename);
                values.put(CourseContract.CourseEntry.COLUMN_NAME_COURSE_CODE, coursecode);
                values.put(CourseContract.CourseEntry.COLUMN_NAME_DATE_CREATED, datecreated);

                //Toast.makeText(Setup.this,"Values.put area", Toast.LENGTH_SHORT).show();

// Insert the new row, returning the primary key value of the new row

                final long newRowId = db.insert(CourseContract.CourseEntry.TABLE_NAME, null, values);
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
                                            " LIKE " + CourseContract.CourseEntry._ID + ";";
                                    db.execSQL(sql);
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
        });

    }




}
