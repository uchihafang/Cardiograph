package com.example.cardiomanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Preferences extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.activity_preferences);
		
	}

	@Override
	protected void onDestroy() {
		// download settings

		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		if (prefs.contains("SampleRates")) {
			MicrophoneRecoder.setRECORDER_SAMPLERATE(prefs.getInt(
					"SampleRates", 8000));
		}
		
		//if (prefs.contains("Channels")) {
		/*	MicrophoneRecoder.setRECORDER_CHANNELS(prefs.getBoolean("Channels",
					false));*/
		//}
		/*
		 * if(prefs.contains("Encoding")) {
		 * MicrophoneRecoder.setRECORDER_AUDIO_ENCODING
		 * (prefs.getBoolean("Encoding", false)); }
		 */
		
		super.onDestroy();
	}
}
