package com.example.cardiomanager;

import android.app.Activity;
import android.widget.Toast;

public class Messenger {

	public Messenger() {
		// TODO Auto-generated constructor stub
	}

	static void showMessege(Activity activity, String string) {
		if(activity != null) {
			Toast toast = Toast.makeText(activity.getApplicationContext(), string,
					Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	static void showMessege(Activity activity, int messegeId) {
		Toast toast = Toast.makeText(activity.getApplicationContext(), "Text",
				Toast.LENGTH_SHORT);
		switch (messegeId) {
		case 51:
			toast.setText("Saved");
			break;
		case 52:
			toast.setText("Error: Empty field!");
			break;
		case 53:
			toast.setText("New user added");
			break;

		}
		toast.show();

	}
}
