package com.example.cardiomanager;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//---clauses---
	MicrophoneRecoder microphoneRecoder;
	AudioFilter audioFilter;
	
	//---values---
	private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();
    
    //---methods---
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //-creating classes-
        audioFilter = new AudioFilter(MainActivity.this);
        microphoneRecoder = new MicrophoneRecoder(audioFilter);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onBtnStartClick(View view) {
    	TextView text;
    	text = (TextView) findViewById(R.id.editView);
    	text.setText("Started");
    	microphoneRecoder.startRecording();
    	
    	Button btn = (Button)findViewById(R.id.btnStart);
    	btn.setEnabled(false);
    	btn = (Button)findViewById(R.id.btnStop);
    	btn.setEnabled(true);
    }
    
    public void onBtnStopClick(View view) {
    	TextView text;
    	text = (TextView) findViewById(R.id.editView);
    	text.setText("Stoped");
    	microphoneRecoder.stopRecording();
    	setProgresBar(0);
    	
    	Button btn = (Button)findViewById(R.id.btnStop);
    	btn.setEnabled(false);
    	btn = (Button)findViewById(R.id.btnStart);
    	btn.setEnabled(true);
    }
    
    public synchronized void setProgresBar(int value)
    {
    	mProgressStatus = (int)(value);///2.55);
    	// Update the progress bar
        mHandler.post(new Runnable() {
            public void run() {
                mProgress.setProgress(mProgressStatus);
                
                TextView ed = (TextView) findViewById(R.id.editView);
                ed.setText(Integer.toString(mProgressStatus));
            }
        });
    }
}
