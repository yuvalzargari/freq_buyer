package com.example.frequent_buyer;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends Activity
{

	Button btnRegister;
	TextView btnLinkToLogin;
	EditText inputFullName;
	EditText inputEmail;
	EditText inputPassword;
	TextView registerErrorMsg;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_TYPE = "type";
	

	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		// Importing all assets like buttons, text fields
		inputFullName = (EditText) findViewById(R.id.reg_fullname);
		inputEmail = (EditText) findViewById(R.id.reg_email);
		inputPassword = (EditText) findViewById(R.id.reg_password);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		btnLinkToLogin = (TextView) findViewById(R.id.link_to_login);
		registerErrorMsg = (TextView) findViewById(R.id.register_error);

		btnRegister.setOnClickListener(new View.OnClickListener() 
		{

			public void onClick(View view) 
			{
				String email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				String fullname = inputFullName.getText().toString();
				new ConnectionAsyncTask().execute(email, password, fullname);
				//JSONObject json = userFunction.loginUser(email, password);
				// check for login response
			}
		});

		btnLinkToLogin.setOnClickListener(new View.OnClickListener() 
		{

			public void onClick(View arg0) 
			{
				Intent login = new Intent(Register.this, Login.class);
				startActivity(login);
				finish();
			}
		});
	}

	private class ConnectionAsyncTask extends AsyncTask<String, Void, JSONObject> 
	{

		UserFunctions userFunction;

		protected void onPreExecute() 
		{
			userFunction = new UserFunctions();
		}

		@Override
		protected JSONObject doInBackground(String... urls) 
		{	
			String email = urls[0];
			String password = urls[1];
			String fullname = urls[2];
			UserFunctions userFunction = new UserFunctions();
			JSONObject JSON = userFunction.registerUser(fullname, email, password);
			return JSON;
		}

		@Override
		protected void onPostExecute(JSONObject json) 
		{
			super.onPostExecute(json);
			try 
			{
				if (json.getString(KEY_SUCCESS) != null) 
				{
					registerErrorMsg.setText("");
					String res = json.getString(KEY_SUCCESS);
					if(Integer.parseInt(res) == 1)
					{
						// user successfully registred
						// Store user details in SQLite Database
						DatabaseHandler db = new DatabaseHandler(getApplicationContext());
						JSONObject json_user = json.getJSONObject("user");

						// Clear all previous data in database
						userFunction.logoutUser(getApplicationContext());
						db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json_user.getString(KEY_TYPE));                        

						Intent businessMenu = new Intent(Register.this, OwnerMenu.class);
						startActivity(businessMenu);



						// Close Login Screen
						Register.this.finish();
					}
					else
					{
						// Error in login
						registerErrorMsg.setText("Error occured in registration");
					}
				}
			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
		}

	}
	/*
	 *  
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
