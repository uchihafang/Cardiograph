package com.example.cardiomanager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PersonalActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_personal, menu);
        return true;
    }
}
