package com.example.cardiomanager;

import android.widget.Toast;

public class AudioFilter {
	
	byte Data[];
	LineGraph lineGraph;
	
	public AudioFilter(LineGraph graph) {
		lineGraph = graph;
			
	}

	public synchronized void setBuffer(byte bData[]) {
		Data = bData;
		lineGraph.addData(Data[1]);
	}
	
	public synchronized byte[] getBuffer() {
		// TODO
		return Data;
	}
	
	/*public synchronized void showMassage(int massageN) {
		
		Toast toast = Toast.makeText(activity.getApplicationContext(), 
				   "���� ��������� ����!", Toast.LENGTH_SHORT);
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
		}
		toast.show();
	}
	
	public synchronized void showFormat(int r, int c, int m ) {
		
		Toast toast = Toast.makeText(activity.getApplicationContext(), 
				   Integer.toString(r)+" "+Integer.toString(c)+" "+Integer.toString(m), Toast.LENGTH_SHORT);
		toast.show();
	}*/

}
