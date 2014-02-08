package com.activept.pt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class PhotoCaptureActivity extends Activity {

	private static final int CAMERA_RESULT = 1;

	private EditText edtEnterDescription;
	
	// declare a variable that will hold a reference to a the EditText component on the screen.
	EditText EnterDescription;
	private String strEnterDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_capture);
        
        //get access to the enter exercise description
      	EditText edtEnterDescription = (EditText) findViewById(R.id.edtEnterDescription);
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
    	
    	// start this intent and anticipate a result.
    	startActivityForResult(cameraIntent, CAMERA_RESULT);
    	
    	setStrEnterDescription(EnterDescription.getText().toString());
    	// create an explicit intent. Telling it to move from one screen to the next.
    	Intent takePictureIntent = new Intent(this, GalleryActivity.class);
    	
    	//get data user entered into exercise description field.
    	String exerciseDescription = edtEnterDescription.getText().toString();
    	
    	//pass that data to the next activity.
    	takePictureIntent.putExtra("EXERCISE_DESCRIPTION", exerciseDescription);
    	
    	//start the activity
    	startActivity(takePictureIntent);
    }

	public String getStrEnterDescription() {
		return strEnterDescription;
	}


	public void setStrEnterDescription(String strEnterDescription) {
		this.strEnterDescription = strEnterDescription;
	}

}
