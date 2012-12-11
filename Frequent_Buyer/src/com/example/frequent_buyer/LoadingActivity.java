package com.example.frequent_buyer;


import java.util.HashMap;

import com.sender.StatPram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoadingActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		Thread timer = new Thread()
		{
			public void run()
			{
				try
				{
					sleep(1000);
					/* 
					 * if the user details are saved(already logged in before) then
					 * automatically login
					 */
					DatabaseHandler db = new DatabaseHandler(getApplicationContext());
					if(db.getRowCount() > 0)
					{
						/*
						 * get user details in order to know if he is the owner or the client
						 */
						HashMap<String, String> userDetails = db.getUserDetails();
						Intent activity;
						if(userDetails.get("type").equals("client") == true)
							activity = new Intent(LoadingActivity.this, BusinessMenu.class);
						else
							activity = new Intent(LoadingActivity.this, OwnerMenu.class);
						// Open the activity
						startActivity(activity);
						// Close Loading Screen
						finish();
					}
					else
					{
						Intent toOpen = new Intent(LoadingActivity.this, Login.class); 
						startActivity(toOpen);
						finish();
					}
				}
				catch(InterruptedException e)
				{

				}
			}

		};
		timer.start();
	}
	/*
	 *  check if commit work
	 * 	need to change to someting like that is more simple and quick from sqllite 
	 * 
	 *  	
	 *  private void saveUser(){

		Boolean exists = false;
		
		//check if user already exists
		for (int i = 0; i < StatPram.numOfUsers; i++){
			if( settings.getString("user"+i, "false").compareTo(mailText) == 0)
				exists  = true;
		}
		
		//if not exists then add to list
		if (!exists){
			editor.putString("user"+StatPram.numOfUsers,mailText);
			StatPram.numOfUsers++;
			editor.putInt("numOfUsers", StatPram.numOfUsers);

			// Commit the edits!
			editor.commit();
		}
	}


	//gets the email and password from edit texts  
	private void getText(){
		passText = pass.getText().toString();
		mailText = mail.getText().toString();
	}


	//saves mail and password if checkbox is true
	private void savePref()
	{
		editor.putString("mail", mailText);
		editor.putString("pass", passText);

		// Commit the edits!
		editor.commit();
	}


	//last user that saved his mail
	private void setSavedUser()
	{
		mailPref = settings.getString("mail", "false");
		passPref = settings.getString("pass", "false");
		if (mailPref.compareTo("false")!=0 && passPref.compareTo("false")!=0){
			mail.setText(mailPref);
			pass.setText(passPref);
		}
	}

	 *  
	 *  
	 *  
	 *  */
}
