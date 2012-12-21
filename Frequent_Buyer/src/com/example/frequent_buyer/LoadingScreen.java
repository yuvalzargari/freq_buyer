package com.example.frequent_buyer;


import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoadingScreen extends Activity
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
					UserFunctions userFunction = new UserFunctions();
					if(userFunction.isUserLoggedIn(getApplicationContext()))
					{
						/*
						 * get user details in order to know if he is the owner or the client
						 */
						DatabaseHandler db = new DatabaseHandler(getApplicationContext());
						HashMap<String, String> userDetails = db.getUserDetails();
						Intent activity;
						if(userDetails.get("type").equals("client") == true)
							activity = new Intent(LoadingScreen.this, BusinessMenu.class);
						else
							activity = new Intent(LoadingScreen.this, OwnerMenu.class);

						// Open the activity
						startActivity(activity);
						// Close Loading Screen
						finish();	
					}
					else
					{
						Intent toOpen = new Intent(LoadingScreen.this, Login.class); 
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


}
