package com.example.cardiomanager;

import android.app.Activity;
import android.widget.Toast;

public class AudioFilter {

	short Data[];
	LineGraph lineGraph;
	int bufferSize;

	public AudioFilter(LineGraph graph) {
		lineGraph = graph;

	}

	public void setBufferSize(int size) {
		bufferSize = size;
	}

	public synchronized void setBuffer(short sData[]) {
		Data = sData;
		int S = 0;
		
		// infiltered
		/*for(int j = 0; j < bufferSize; j += 100) {
			S = Data[j];
			lineGraph.addData(S);
		}*/
		
		//aprocsimation
		for (int i = 50; i < 70; i++) {
			S += Data[i];
		}
		S /= 30 - 10;
		Math.abs(S);
		lineGraph.addData(S);
		
		
		
	}

	public synchronized short[] getBuffer() {
		// TODO
		return Data;
	}

	Activity activity;

	public void setActivity(Activity act) {
		activity = act;
	}

	public synchronized void showMassage(int massageN) {

		Toast toast = Toast.makeText(activity.getApplicationContext(),
				"Пора покормить кота!", Toast.LENGTH_SHORT);
		switch (massageN) {
		case 1:
			toast.setText("Initialization Start");
			break;
		case 2:
			toast.setText("Initialization Finish");
			break;
		case 3:
			toast.setText("Object created");
			break;
		case 4:
			toast.setText("Thread started");
			break;
		case 5:
			toast.setText("Initialization Feild");
			break;
		case 6:
			toast.setText("Data read");
			break;
		case 7:
			toast.setText("Configuration OK");
			break;
		case 8:
			toast.setText("Configuration Failed");
			break;
		}
		toast.show();
	}

	/*
	 * public synchronized void showFormat(int r, int c, int m ) {
	 * 
	 * Toast toast = Toast.makeText(activity.getApplicationContext(),
	 * Integer.toString(r)+" "+Integer.toString(c)+" "+Integer.toString(m),
	 * Toast.LENGTH_SHORT); toast.show(); }
	 */

}
