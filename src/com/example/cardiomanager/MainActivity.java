package com.example.cardiomanager;

import org.achartengine.GraphicalView;

import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//---clauses---
	MicrophoneRecoder microphoneRecoder;
	AudioFilter audioFilter;
	private LineGraph lineGraph;
	private static GraphicalView gViewGraph;
	private static Thread threadGraph;
	
    //---methods---
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //-creating classes-
        audioFilter = new AudioFilter(MainActivity.this);
        microphoneRecoder = new MicrophoneRecoder(audioFilter);
        
        lineGraph = new LineGraph();
        gViewGraph = lineGraph.getView(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.llGraph);
        layout.addView(gViewGraph);
        
        threadGraph = new Thread() {
        	public void run() {
        		try {
        			while(true) {
                		lineGraph.addData(1);
                		gViewGraph.repaint();
                		Thread.sleep(200);
                	}
        			
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
        		
        	}
        };
        threadGraph.start();
       
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
    	//setProgresBar(0);
    	
    	Button btn = (Button)findViewById(R.id.btnStop);
    	btn.setEnabled(false);
    	btn = (Button)findViewById(R.id.btnStart);
    	btn.setEnabled(true);
    }
    
    public void onBtnUserNameClick(View view) {
    	Intent intent = new Intent(MainActivity.this, UserListActivity.class);
        startActivity(intent);
    }
    
}
