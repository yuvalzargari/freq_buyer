package com.example.frequent_buyer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class BusinessList extends ListActivity 
{


	// JSON Response node names
	private static String KEY_SUCCESS = "success";

	// Progress Dialog when loading from the database
	ProgressDialog dialog;
	
	// Adapter that holds the data showed in the list
	ArrayAdapter<String> mAdapter;

	// String that the adapter takes the information from
	String business[] = null;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		new ConnectionAsyncTask().execute();
	}

	@Override
	public void onBackPressed() 
	{
		/*
		 * Show a dialog to confirm if the user want to exit from the application
		 */
		new AlertDialog.Builder(this)
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle("Closing Frequent Buyer")
		.setMessage("Are you sure you want exit?")
		.setPositiveButton("Yes", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which) {
				finish();    
			}

		})
		.setNegativeButton("No", null)
		.show();
	}

	/*
	 * Thread that handles the communication with the server
	 * Get information about the business stored in the server
	 */
	private class ConnectionAsyncTask extends AsyncTask<Void, Void, JSONObject> 
	{

		BusinessFunction businessFunction;

		@Override
		protected void onPreExecute() 
		{
			businessFunction = new BusinessFunction();
			dialog = ProgressDialog.show(BusinessList.this, "", "Loading...");
			dialog.show();
		}



		@Override
		protected JSONObject doInBackground(Void... params) 
		{
			// Get all business that are saved on the server
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
						
						BusinessFunction businessFunction = new BusinessFunction();
						if(businessFunction.businessAvailable(getApplicationContext()) == true)
						{
							// saves all the business names in the string
							business = db.getAllBusinessNames();
						}
						else
						{
							business = new String[1];
							business[0] = "No business found!";
						}
						
						// push the data into the adapter and how it in the list
						mAdapter = new ArrayAdapter<String>(BusinessList.this, android.R.layout.simple_list_item_1, business);
						setListAdapter(mAdapter);
						ListView listView = getListView();
						listView.setCacheColorHint(0);
						listView.setBackgroundResource(R.drawable.background);
						listView.setTextFilterEnabled(true);

						listView.setOnItemClickListener(new OnItemClickListener() 
						{
							public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
								// When clicked, show a toast with the TextView text
								staticParams.saveBusinessDetail(getApplicationContext(), ((TextView) view).getText().toString());
								Intent activity;
								if(staticParams.userType.equals("client") == true)
									activity = new Intent(BusinessList.this, BusinessMenu.class);
								else
									activity = new Intent(BusinessList.this, OwnerMenu.class);
								startActivity(activity);
							}
						});

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
			if(dialog.isShowing()) 
			{
				dialog.dismiss();
			}
		}
	}


}
