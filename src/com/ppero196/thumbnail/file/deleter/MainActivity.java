package com.ppero196.thumbnail.file.deleter;


import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;


import java.io.File;
import java.text.NumberFormat;
import java.util.Calendar;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.os.StatFs;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends SherlockActivity {
	
	//the statistics of the SD card
		private StatFs stats;
		//the state of the external storage
		private String externalStorageState;
		
		//the total size of the SD card
		@SuppressWarnings("unused")
		private double totalSize;
		//the available free space
		private double freeSpace;
		
		//a String to store the SD card information
		private String outputInfo;
		//a TextView to output the SD card information
		private TextView tv_info;
		
		private TextView tv_info1;
		
		//set the number format output
		private NumberFormat numberFormat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_info = (TextView)findViewById(R.id.info);
        
        //get external storage (SD card) state
        externalStorageState = Environment.getExternalStorageState();
        
        //checks if the SD card is attached to the Android device 
        if(externalStorageState.equals(Environment.MEDIA_MOUNTED)
        		|| externalStorageState.equals(Environment.MEDIA_UNMOUNTED)
        		|| externalStorageState.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
        {
        	//obtain the stats from the root of the SD card.
        	stats = new StatFs(Environment.getExternalStorageDirectory().getPath());
        	
        	//Add 'Total Size' to the output string:
        	outputInfo = "";
        	
        	//total usable size
        	totalSize = stats.getBlockCount() * stats.getBlockSize();
        	
        	//initialize the NumberFormat object
        	numberFormat = NumberFormat.getInstance();
        	//disable grouping
        	numberFormat.setGroupingUsed(false);
        	//display numbers with two decimal places
        	numberFormat.setMaximumFractionDigits(2); 
        	
        	
        	//available free space
        	freeSpace = stats.getAvailableBlocks() * stats.getBlockSize();
        	
        	//Output the SD card's available free space in gigabytes, megabytes, kilobytes and bytes
        	outputInfo += "Size in gigabytes before deletion: " + numberFormat.format((freeSpace / (double)1073741824)) + " GB \n"; 
        	
       	 	//output the SD card info
       	 	tv_info.setText(outputInfo);
        }
        else //external storage not found
        {
        }
    }
        	
        

    @Override
     public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.menu, menu);   
        return true;
    }
    public void deletefile(View view) {
    	File dir = new File("sdcard//DCIM/.thumbnails");
    	if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                new File(dir, children[i]).delete();
            }
        }
    	File dir1 = new File("/sdcard-ext/DCIM/.thumbnails");
    	if (dir1.isDirectory()) {
            String[] children = dir1.list();
            for (int i = 0; i < children.length; i++) {
                new File(dir1, children[i]).delete();
            }
        }
    	Toast.makeText(getApplicationContext(), 
                "Thumbnail files deleted!", Toast.LENGTH_LONG).show();
tv_info1 = (TextView)findViewById(R.id.info1);
        
        //get external storage (SD card) state
        externalStorageState = Environment.getExternalStorageState();
        
        //checks if the SD card is attached to the Android device 
        if(externalStorageState.equals(Environment.MEDIA_MOUNTED)
        		|| externalStorageState.equals(Environment.MEDIA_UNMOUNTED)
        		|| externalStorageState.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
        {
        	//obtain the stats from the root of the SD card.
        	stats = new StatFs(Environment.getExternalStorageDirectory().getPath());
        	
        	//Add 'Total Size' to the output string:
        	outputInfo = "";
        	
        	//total usable size
        	totalSize = stats.getBlockCount() * stats.getBlockSize();
        	
        	//initialize the NumberFormat object
        	numberFormat = NumberFormat.getInstance();
        	//disable grouping
        	numberFormat.setGroupingUsed(false);
        	//display numbers with two decimal places
        	numberFormat.setMaximumFractionDigits(2); 
        	
        	
        	//available free space
        	freeSpace = stats.getAvailableBlocks() * stats.getBlockSize();
        	
        	//Output the SD card's available free space in gigabytes, megabytes, kilobytes and bytes
        	outputInfo += "Size in gigabytes after deletion: " + numberFormat.format((freeSpace / (double)1073741824)) + " GB \n"; 
        	
       	 	//output the SD card info
       	 	tv_info1.setText(outputInfo);
        }
        else //external storage not found
        {
        }
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
    

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case R.id.Credits:
            WebView webview1 = new WebView(this);
       	 setContentView(webview1);
       	webview1.loadUrl("file:///android_res/raw/credits.html"); 
        	return true;
    	
    case R.id.Recover:
    	sendBroadcast( new Intent(Intent.ACTION_MEDIA_MOUNTED, 
                Uri.parse("file://" + Environment.getExternalStorageDirectory()))
    );
    	return true;
	}
		return true;
    }}
