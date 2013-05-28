package com.example.cardiomanager;

import org.achartengine.GraphicalView;
import android.os.Bundle;
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
	Thread threadGraphUpdater;
	
    //---methods---
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //-creating classes-
        lineGraph = new LineGraph();
        gViewGraph = lineGraph.getView(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.llGraph);
        layout.addView(gViewGraph);
        		
        audioFilter = new AudioFilter(lineGraph);
        audioFilter.setActivity(this);// TODO del, debug only
        microphoneRecoder = new MicrophoneRecoder(audioFilter);
        
        lineGraph.addData(100);
        lineGraph.addData(-50);
        lineGraph.addData(10);
        
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
    	try {
    		microphoneRecoder.startRecording();
    	} catch(Exception e)
    	{
    		
    	}
    	
    	/*if(threadGraphUpdater == null) {
	    	threadGraphUpdater = new Thread() {
				public void run() {
					while(true) {
						try {
							Thread.sleep(100);
							gViewGraph.repaint();
							
						}catch(InterruptedException e){
							
						}
					}
				}
			};
    	}
    	threadGraphUpdater.start();*/
    	
    	Button btn = (Button)findViewById(R.id.btnStart);
    	btn.setEnabled(false);
    	btn = (Button)findViewById(R.id.btnStop);
    	btn.setEnabled(true);
    }
    
    public void onBtnStopClick(View view) {
    	TextView text;
    	text = (TextView) findViewById(R.id.editView);
    	text.setText("Stoped");
    	try {
    		microphoneRecoder.stopRecording();
    	} catch(Exception e)
    	{
    		
    	}
    	
    	/*if(threadGraphUpdater.isAlive()) {
	    	threadGraphUpdater.stop();
	    }*/
    	
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
