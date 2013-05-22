package com.example.cardiomanager;

public class AudioFilter {
	
	byte Data[];
	MainActivity activity;
	
	public AudioFilter(MainActivity act) {
		activity = act;
	}

	public synchronized void setBuffer(byte bData[]) {
		Data = bData;
		activity.setProgresBar(Data[1]);
	}
	
	public synchronized byte[] getBuffer() {
		// TODO
		return Data;
	}

}
