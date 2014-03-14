package com.activept.pt;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class GalleryActivity extends Activity {

	
	private String exerciseDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		//retrieve any data that was passed in and was associated with name "EXERCISE_DESCRIPTION"
		String exerciseDescrption = getIntent().getStringExtra("EXERCISE_DESCRIPTION");
		
		//associate layout with this activity
		setContentView(R.layout.activity_gallery);
	}
		//create a collection to hold the exercises
		ArrayList<Exercise> allExercises = new ArrayList<Exercise>();
		
		exerciseDescription = null;
		if (exerciseDescription.contains("leglift")) {
		//create a leg lift
		Exercise leglift = new Exercise();
		allExercises.add(leglift);
		
		}
	}
}
