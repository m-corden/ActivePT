package com.activept.pt;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class GalleryActivity extends Activity {

	
	private Bitmap exerciseImage; 
	private ImageView imgExercise;
	private String exerciseDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		//retrieve any data that was passed in and was associated with name "EXERCISE_DESCRIPTION"
		exerciseDescription = getIntent().getStringExtra("EXERCISE_DESCRIPTION");
		
		//associate layout with this activity
		setContentView(R.layout.activity_gallery);
/*		
		// get a reference to the image view that will display a exercise photo.
		imgExercise = (ImageView) findViewById((Integer) R.id.imgExercise);

		//create a collection to hold the exercises
		ArrayList<Exercise> allExercises = new ArrayList<Exercise>();
		
		if (exerciseDescription.contains("leglift")) {
			//create a leg lift
			Exercise leglift = new Exercise();
			allExercises.add(leglift);
			
		}
*/		
		/* else if (requestCode == CAMERA_RESULT) {
			Intent data;
			// we are here because we have received a result from the camera.
			exerciseImage = (Bitmap) data.getExtras().get("data");
			
			imgExercise.setImageBitmap(exerciseImage);
		} */
	}
}
