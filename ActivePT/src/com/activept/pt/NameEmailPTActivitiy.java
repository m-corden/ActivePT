package com.activept.pt;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class NameEmailPTActivitiy extends Activity {

	// declare a variable that will hold a reference to a the EditText component on the screen.
	EditText Name;
	private String strName;
	
	// declare a variable that will hold a reference to a the EditText component on the screen.
	EditText Email;
	private String strEmail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//associate the layout with this activity.
		setContentView(R.layout.activity_ptname_email);
		
		//get access to the enter exercise description
      	EditText edtName = (EditText) findViewById(R.id.edtName);
      	
        //get access to the enter exercise description
      	EditText edtEmail = (EditText) findViewById(R.id.edtEmail);

	}
	
	public String getStrName() {
		return strName;
	}

	public void setStrNamen(String strName) {
		this.strName = strName;
		
	}
}