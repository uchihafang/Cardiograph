package com.example.cardiomanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
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
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        
        edName = (EditText) findViewById(R.id.edUserName);
        edDate = (EditText) findViewById(R.id.editDateBirth);
        edHeight = (EditText) findViewById(R.id.edHeight);
        edWeight = (EditText) findViewById(R.id.edWeight);
        edInfo = (EditText) findViewById(R.id.edInfo);
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
            db.insert(DbSQLLite.TABLE_NAME,null,cv);
		} 
        finally {
        	db.close();
		}
    }
    
    //private void getFromDataBase(int ID) {
    	/*DbSQLLite dbSQLLite = new DbSQLLite(PersonalActivity.this);
        SQLiteDatabase db = dbSQLLite.getReadableDatabase();
        try {
            ContentValues cv = new ContentValues();
            cv.put(dbSQLLite.USERNAME ,edName.getText().toString());
            cv.put(dbSQLLite.DATEBRTH ,edDate.getText().toString());
            cv.put(dbSQLLite.HEIGHT ,edHeight.getText().toString());
            cv.put(dbSQLLite.WEIGHT ,edInfo.getText().toString());
            cv.put(dbSQLLite.DASEASES ,edName.getText().toString());
            db.insert(dbSQLLite.TABLE_NAME,null,cv);
		} 
        finally {
        	db.close();
		}
        
        
        String whereBatch = DbSQLLite.CONTACTS_ID_NAME + " = ?";
        Cursor c = database.query(ContactsDBHelper.CONTACTS_TABLE_NAME, selectedColumns, whereBatch, new String[]{ Long.toString(id) }, null, null, ContactsDBHelper.CONTACTS_USER_NAME);            
        Contact contact = null;
        if(c.getCount() > 0)
        {
               c.moveToFirst();                                       
               contact = cursorToContact(c);
        }*/
    //}
}
