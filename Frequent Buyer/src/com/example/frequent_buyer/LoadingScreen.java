package com.example.frequent_buyer;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class LoadingScreen extends Activity
{

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_NAME = "name";
	private static String KEY_MENU = "menu";
	private static String KEY_EVENTS = "events";
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		
		

		new ConnectionAsyncTask().execute();

		Thread timer = new Thread()
		{

			public void run()
			{
				try
				{
					sleep(1000);

				}
				catch(InterruptedException e)
				{

				}
			}

		};
//		timer.start();
	}
	
	private class ConnectionAsyncTask extends AsyncTask<Void, Void, JSONObject> 
	{

		BusinessFunction businessFunction;

		@Override
		protected void onPreExecute() 
		{
			businessFunction = new BusinessFunction();
		}
		


		@Override
		protected JSONObject doInBackground(Void... params) 
		{
			JSONObject json = businessFunction.getBusinessList();
			return json;
		}

		@Override
		protected void onPostExecute(JSONObject json) 
		{
			super.onPostExecute(json);

			try 
			{
				if (json.getString(KEY_SUCCESS) != null) 
				{
					String res = json.getString(KEY_SUCCESS);
					if(Integer.parseInt(res) == 1)
					{
						// business successfully retrived in
						// Store all business details in SQLite Database
						DatabaseHandler db = new DatabaseHandler(getApplicationContext());
						JSONArray json_business = json.getJSONArray("business");
						// Clear all previous data in database 
						businessFunction.removeAllBusiness(getApplicationContext());
						// Add all business to the table
						db.addBusiness(json_business);
					}
					else
					{
						// Error in login
					}
				}
			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
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
				staticParams.saveUserDetail(getApplicationContext());
				Intent activity;
				activity = new Intent(LoadingScreen.this, BusinessList.class);
//				if(staticParams.userType.equals("client") == true)
//					activity = new Intent(LoadingScreen.this, BusinessMenu.class);
//				else
//					activity = new Intent(LoadingScreen.this, OwnerMenu.class);

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
	}


}
