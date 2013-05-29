package com.example.cardiomanager;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class DataSender extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sender);
        
        
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		 
        emailIntent.setType("plain/text");
        // Кому
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
            new String[] { "den3@ukr.net" });
        // Зачем
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
            "android send hello");
        // О чём
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
            "Android thi is cool)");
        // С чем
        /*emailIntent.putExtra(
            android.content.Intent.EXTRA_STREAM,
            Uri.parse("file://"
                + Environment.getExternalStorageDirectory()
                + "/Клипы/SOTY_ATHD.mp4"));
        
        emailIntent.setType("text/video");*/
        // Поехали!
        DataSender.this.startActivity(Intent.createChooser(emailIntent,
            "Отправка письма..."));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_data_sender, menu);
        return true;
    }
}
