package com.activept.pt;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
		
		String where = MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME + " = ?";
		String[] args = { "Active PT" };
		
		Cursor c = this.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, where, args, null);
		
		while(c.moveToNext())
		{
			Log.e("APT", "--------");
			
			for (int i = 0; i < c.getColumnCount(); i++)
			{
				Log.e("APT", c.getColumnName(i) + ": " + c.getString(i));
				
			}
		}
		
		ListView list = (ListView) this.findViewById(R.id.images_list);
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row_picture, c, new String[0], new int[0], 0)
		{
			public void bindView (View view, Context context, Cursor cursor)
			{
				ImageView image = (ImageView) view.findViewById(R.id.image_preview);
				image.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))));
				
				TextView description = (TextView) view.findViewById(R.id.image_description);
				description.setText(cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DESCRIPTION)));
			}
			
		};
		
		list.setAdapter(adapter);
		
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
