package com.example.cardiomanager;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	MicrophoneRecoder microphoneRecoder;
	
	private ProgressBar mProgress;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //creating clases
        microphoneRecoder = new MicrophoneRecoder();
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onBtnStartClick(View view) {
    	TextView text;
    	text = (TextView) findViewById(R.id.editView);
    	text.setText("Started");
    	microphoneRecoder.startRecording();
    }
    
    public void onBtnStopClick(View view) {
    	TextView text;
    	text = (TextView) findViewById(R.id.editView);
    	text.setText("Stoped");
    	microphoneRecoder.stopRecording();
    	setProgresBar(0);
    }
    
    public void setProgresBar(int value)
    {
    	mProgressStatus = value/10;
    	// Update the progress bar
        mHandler.post(new Runnable() {
            public void run() {
                mProgress.setProgress(mProgressStatus);
            }
        });
    }
}
