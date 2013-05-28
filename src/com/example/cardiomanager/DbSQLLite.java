package com.example.cardiomanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbSQLLite extends SQLiteOpenHelper {

	//DataBasePropetis
	private static final int DB_VERSION = 1;  
	private static final String DB_NAME = "CardioBase";

	public static final String TABLE_NAME = "users";
	public static final String USERNAME = "username";
	public static final String DATEBRTH = "datebrth";
	public static final String HEIGHT = "height";
	public static final String WEIGHT = "weight";
	public static final String DASEASES = "DESEASES";
	public static final String CREATE_TABLE = "create table " + TABLE_NAME + " ( _id integer primary key autoincrement, "
	      + USERNAME + " TEXT NOT NULL, " + DATEBRTH + " TEXT, " + HEIGHT + " INTEGER, " + WEIGHT + " INTEGER, " + DASEASES + " TEXT)";

	public DbSQLLite(Context context) {
	    super(context, DB_NAME, null, DB_VERSION);
	    
	  }

	  @Override
	  public void onCreate(SQLiteDatabase sqLiteDatabase) {
	    sqLiteDatabase.execSQL(CREATE_TABLE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
		  // uncomment to delete data 
		  //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		  //onCreate(sqLiteDatabase);
		  
	  }

}