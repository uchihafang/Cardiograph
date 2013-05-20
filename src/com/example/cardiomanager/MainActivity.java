package com.example.cardiomanager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	MicrophoneRecoder microphoneRecoder;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //creating clases
        microphoneRecoder = new MicrophoneRecoder();
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
    }
}
