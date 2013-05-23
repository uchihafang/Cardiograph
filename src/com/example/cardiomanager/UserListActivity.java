package com.example.cardiomanager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Gallery.LayoutParams;
import android.widget.LinearLayout;
import android.support.v4.app.NavUtils;

public class UserListActivity extends Activity {

	LinearLayout llBtnContainer;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        
        llBtnContainer = (LinearLayout) findViewById(R.id.llButtonContainer);
        CreateButtonList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_user_list, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.button1:
                NavUtils.navigateUpFromSameTask(this); // TODO
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void CreateButtonList() {
    	
    	LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    	layoutParams.gravity =Gravity.LEFT;
    	for (int i = 0; i < 3; i++) {
	    	Button button = new Button(this);      
	    	button.setText("asdas");
	    	button.setLayoutParams(layoutParams);
	    	button.setId(i);
	    	llBtnContainer.addView(button, layoutParams);
    	}
    }
}
