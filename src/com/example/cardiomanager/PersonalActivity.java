package com.example.cardiomanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class PersonalActivity extends Activity {

	private EditText edName;
	private EditText edDate;
	private EditText edHeight;
	private EditText edWeight;
	private EditText edInfo;
	
	private int id;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        
        edName = (EditText) findViewById(R.id.edUserName);
        edDate = (EditText) findViewById(R.id.editDateBirth);
        edHeight = (EditText) findViewById(R.id.edHeight);
        edWeight = (EditText) findViewById(R.id.edWeight);
        edInfo = (EditText) findViewById(R.id.edInfo);
        
        //finding user
        id = getIntent().getExtras().getInt("Key_ID");
        if(id != 999)
          	getFromDataBase(id);
        edHeight.setText(Integer.toString(id));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_personal, menu);
        return true;
    }
    
    public void onBtnDoneClick(View view) {
    	DbSQLLite dbSQLLite = new DbSQLLite(PersonalActivity.this);
        SQLiteDatabase db = dbSQLLite.getWritableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(DbSQLLite.USERNAME ,edName.getText().toString());
            cv.put(DbSQLLite.DATEBRTH ,edDate.getText().toString());
            cv.put(DbSQLLite.HEIGHT ,edHeight.getText().toString());
            cv.put(DbSQLLite.WEIGHT ,edWeight.getText().toString());
            cv.put(DbSQLLite.DASEASES ,edInfo.getText().toString());
            
            if(id == 999)
            	db.insert(DbSQLLite.TABLE_NAME,null,cv);
            else
            	db.update(DbSQLLite.TABLE_NAME, cv, DbSQLLite.USER_ID + " = ?",
                        new String[] { String.valueOf(id) });
		} 
        finally {
        	db.close();
		}
    }
    
    private void getFromDataBase(int ID) {
    	DbSQLLite dbSQLLite = new DbSQLLite(PersonalActivity.this);
        SQLiteDatabase db = dbSQLLite.getReadableDatabase();
        try {
        	String[] selectedColumns = new String[] { DbSQLLite.USER_ID,
        			DbSQLLite.USERNAME, DbSQLLite.DATEBRTH, DbSQLLite.HEIGHT, 
        			DbSQLLite.WEIGHT, DbSQLLite.DASEASES };
        	Cursor cursor = db.query(DbSQLLite.TABLE_NAME, selectedColumns,
        			DbSQLLite.USER_ID + "=?",
                    new String[] { String.valueOf(id) }, null, null, null, null);
     
            if (cursor != null){
                cursor.moveToFirst();
                edName.setText(cursor.getString(1));
                edDate.setText(cursor.getString(2));
                edHeight.setText(cursor.getString(3));
                edWeight.setText(cursor.getString(4));
                edInfo.setText(cursor.getString(5));
            }
		} 
        finally {
        	db.close();
		}
    }
}
