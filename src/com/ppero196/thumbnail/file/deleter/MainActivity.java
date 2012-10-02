package com.ppero196.thumbnail.file.deleter;

import java.io.File;
import java.util.Calendar;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    public void deletefile(View view) {
    	File dir = new File(Environment.getExternalStorageDirectory()+"/DCIM/.thumbnails");
    	if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                new File(dir, children[i]).delete();
            }
        }
    	Toast.makeText(getApplicationContext(), 
                "Thumbnail files deleted!", Toast.LENGTH_LONG).show();
    }
    public void reminder(View view) {
    	Calendar cal = Calendar.getInstance();              
    	Intent intent = new Intent(Intent.ACTION_EDIT);
    	intent.setType("vnd.android.cursor.item/event");
    	intent.putExtra("beginTime", cal.getTimeInMillis());
    	intent.putExtra("allDay", true);
    	intent.putExtra("rrule", "FREQ=MONTHLY");
    	intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
    	intent.putExtra("title", "Clear thumbnails!!!");
    	startActivity(intent);
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Credits:
            	WebView webview = new WebView(this);
              	 setContentView(webview);
              	 webview.loadUrl("file:///android_res/raw/credits.html");
                return true;
        }
		return true;
    }
}
