package com.activept.pt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class PhotoCaptureActivity extends Activity {
	
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
    
    public void takePicture(View v) {
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
