package com.ppero196.thumbnail.file.deleter;

import java.io.File;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
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
}
