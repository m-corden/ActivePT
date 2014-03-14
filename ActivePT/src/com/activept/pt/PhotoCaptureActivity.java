package com.activept.pt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class PhotoCaptureActivity extends Activity {

	private static final int CAMERA_RESULT = 1;

	private EditText edtEnterDescription;
	
	// declare a variable that will hold a reference to a the EditText component on the screen.
	private String strEnterDescription;

	private MediaScannerConnection conn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_capture);
        
        //get access to the enter exercise description
        edtEnterDescription = (EditText) findViewById(R.id.edtEnterDescription);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.photo_capture, menu);
        return true;
    }
    //this method will be invoked when Take Photo button is clicked
    public void takePicture(View v) {
    	// use an implicit intent to invoke a camera.
    	Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    	cameraIntent.putExtra(MediaStore.EXTRA_MEDIA_ALBUM, "Active PT");
    	
    	// start this intent and anticipate a result.
    	startActivityForResult(cameraIntent, CAMERA_RESULT);
    	
/*    	setStrEnterDescription(edtEnterDescription.getText().toString());
    	// create an explicit intent. Telling it to move from one screen to the next.
    	Intent takePictureIntent = new Intent(this, GalleryActivity.class);
    	
    	//get data user entered into exercise description field.
    	String exerciseDescription = edtEnterDescription.getText().toString();
    	
    	//pass that data to the next activity.
    	takePictureIntent.putExtra("EXERCISE_DESCRIPTION", exerciseDescription);
    	
    	//start the activity
    	startActivity(takePictureIntent);
*/    }

	public String getStrEnterDescription() {
		return strEnterDescription;
	}


	public void setStrEnterDescription(String strEnterDescription) {
		this.strEnterDescription = strEnterDescription;
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		Log.e("APT", "GOT IMAGE");
		
		if (requestCode == CAMERA_RESULT && resultCode == RESULT_OK) {

			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			Bitmap photo = (Bitmap) data.getExtras().get("data");
			photo.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

			File dir = new File(Environment.getExternalStorageDirectory(), "Active PT");

			if(!dir.exists())
			{
				if(dir.mkdirs())
				{
					Log.e("APT", "1.1.1");
				}
			}
			
			Log.e("APT", "FOLDER EXISTS: " + dir.exists() + " -- " + dir.getAbsolutePath());
			
			try 
			{
				long now = System.currentTimeMillis();
				
				//you can create a new file name "test.jpg" in sdcard folder.
				final File f = new File(dir, now +".jpg");
				f.createNewFile();
				//write the bytes in file

				FileOutputStream fo = new FileOutputStream(f);
				fo.write(bytes.toByteArray());

				// remember close the FileOutput
				fo.close();
				//Save image into your new created album gallery
				
				final PhotoCaptureActivity me = this;
				
				MediaStore.Images.Media.insertImage(getContentResolver(), f.getAbsolutePath(), "Tests", "Description");

				me.conn  = new MediaScannerConnection(this, new MediaScannerConnectionClient()
				{
					public void onMediaScannerConnected() 
					{
						me.conn.scanFile(f.getAbsolutePath(), "image/jpeg");
					}

					public void onScanCompleted(String arg0, Uri uri) 
					{
						Log.e("APT", "G2G: " + arg0 + " -- " + uri);

						me.conn.disconnect();
						

						String where = MediaStore.Images.ImageColumns.DATA + " = ?";
						String[] args = { uri.toString() };
						
						ContentValues values = new ContentValues();
						values.put(MediaStore.Images.ImageColumns.DESCRIPTION, edtEnterDescription.getText().toString());
						
						me.getContentResolver().update(uri, values, null, null);

						Intent galleryIntent = new Intent(me, GalleryActivity.class);
						me.startActivity(galleryIntent);
						
						// me.finish();
					}
				});
				
				conn.connect();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
