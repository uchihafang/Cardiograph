package com.example.cardiomanager;

import org.achartengine.GraphicalView;
import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	// ---clauses---
	MicrophoneRecoder microphoneRecoder;
	AudioFilter audioFilter;
	private LineGraph lineGraph;
	private static GraphicalView gViewGraph;
	Thread threadGraphUpdater;
	private int userID = 999;

	// ---methods---
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// -creating classes-
		lineGraph = new LineGraph();
		gViewGraph = lineGraph.getView(this);
		LinearLayout layout = (LinearLayout) findViewById(R.id.llGraph);
		layout.addView(gViewGraph);
		
		//create audio recoder
		audioFilter = new AudioFilter(lineGraph);
		audioFilter.setActivity(this);// TODO del, debug only
		microphoneRecoder = new MicrophoneRecoder(audioFilter);
		
	}

	@Override
	protected void onStart() {
		// set Button name
		String strName = getString(R.string.btnUserNameText);
		DbSQLLite dbSQLLite = new DbSQLLite(this);
		try {
			userID = getIntent().getExtras().getInt("Key_ID");
			strName = dbSQLLite.getUserName(userID);
			Button button = (Button) findViewById(R.id.btnUserName);
			button.setText(strName);
		} catch (Exception e) {
			//
		}
			
		
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.personal:
			Intent intentPrs = new Intent(MainActivity.this,
					PersonalActivity.class);
			intentPrs.putExtra("Key_ID", userID);
			startActivity(intentPrs);
			return true;
		case R.id.about:
			Intent intentAct = new Intent(MainActivity.this,
					AboutActivity.class);
			startActivity(intentAct);
			return true;
		case R.id.menu_settings:
			Intent intentSet = new Intent(MainActivity.this, Preferences.class);
			startActivity(intentSet);
			return true;
		case R.id.send_data:
			Intent intentSend = new Intent(MainActivity.this, DataSender.class);
			startActivity(intentSend);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onBtnStartClick(View view) {
		TextView text;
		text = (TextView) findViewById(R.id.editView);
		text.setText("Started");
		try {
			microphoneRecoder.startRecording();
		} catch (Exception e) {

		}

		Button btn = (Button) findViewById(R.id.btnStart);
		btn.setEnabled(false);
		btn = (Button) findViewById(R.id.btnStop);
		btn.setEnabled(true);
		isOnPause = false;
	}

	boolean isOnPause = false;
	
	public void onBtnStopClick(View view) {
		if(isOnPause) {
			TextView text;
			text = (TextView) findViewById(R.id.editView);
			text.setText("Cleared");
			
			lineGraph.clear();
			
			Button btn = (Button) findViewById(R.id.btnStop);
			btn.setEnabled(false);
			btn = (Button) findViewById(R.id.btnStart);
			btn.setEnabled(true);
			
			isOnPause = false;
		} else
		{
			TextView text;
			text = (TextView) findViewById(R.id.editView);
			text.setText("Stoped");
			try {
				microphoneRecoder.stopRecording();
			} catch (Exception e) {
	
			}
	
			//if(line2)
			//lineGraph.startFilteringLine();
						
			Button btn =  (Button) findViewById(R.id.btnStart);
			btn.setEnabled(true);
			
			isOnPause = true;
		}
	}

	public void onBtnUserNameClick(View view) {
		Intent intent = new Intent(MainActivity.this, UserListActivity.class);
		startActivity(intent);
	}

}
