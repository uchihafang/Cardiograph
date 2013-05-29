package com.example.cardiomanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery.LayoutParams;
import android.widget.LinearLayout;

public class UserListActivity extends Activity {

	LinearLayout llBtnContainer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);
		// getActionBar().setDisplayHomeAsUpEnabled(true);

		llBtnContainer = (LinearLayout) findViewById(R.id.llButtonContainer);
		CreateButtonList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_user_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			Intent intentSet = new Intent(UserListActivity.this,
					Preferences.class);
			startActivity(intentSet);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void CreateButtonList() {

		DbSQLLite dbSQLLite = new DbSQLLite(UserListActivity.this);// create
																	// database
																	// class
		SQLiteDatabase db = dbSQLLite.getWritableDatabase(); // open database
		String selectQuery = "SELECT  * FROM " + DbSQLLite.TABLE_NAME;// set
																		// query
		Cursor cursor = db.rawQuery(selectQuery, null);// get data from base
		// create visual container
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		layoutParams.gravity = Gravity.LEFT;
		if (cursor.moveToFirst()) {// if query not empty
			do {
				Button button = new Button(this);// create button
				button.setText(cursor.getString(1));// set Name
				button.setLayoutParams(layoutParams);// set visual params
				button.setId(cursor.getInt(0));// set ID
				button.setOnClickListener(new View.OnClickListener() {// btnNew
																		// OnClick
					public void onClick(View v) {
						Intent intent = new Intent(UserListActivity.this,
								MainActivity.class);
						intent.putExtra("Key_ID", v.getId());
						startActivity(intent);
					}
				});
				llBtnContainer.addView(button, layoutParams);// add on Activity
			} while (cursor.moveToNext());// while exists
		}
		cursor.close();
		db.close();
		Button button = new Button(this);// create button for addNew
		button.setText("Add new User");// set Name //TODO add string to values
		button.setLayoutParams(layoutParams);// set visual params
		button.setId(99);// set ID
		llBtnContainer.addView(button, layoutParams);// add on Activity
		button.setOnClickListener(new View.OnClickListener() {// btnNew OnClick
			public void onClick(View v) {
				Intent intent = new Intent(UserListActivity.this,
						PersonalActivity.class);
				int id = 999;
				intent.putExtra("Key_ID", id);
				startActivity(intent);
			}
		});
		
	}
}
