package com.example.frequent_buyer;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CouponChanger extends Activity
{

	// JSON Response node names
	private static String KEY_SUCCESS = "success";

	EditText newNumToCopon,newBenefit;
	Button setCopon,subtract ;
	Editable NumToCopon;
	String Benefit, email, businessName;

	ProgressDialog dialog;

	protected CharSequence result;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coponchanger);
		newBenefit = (EditText)findViewById(R.id.setbenefitcoponchangerID);
		newNumToCopon = (EditText)findViewById(R.id.setnumcoponchangerID);
		setCopon = (Button)findViewById(R.id.setcoponbuttunID);
		subtract = (Button)findViewById(R.id.subtractButtunID);
		businessName = staticParams.businessName;
		email = staticParams.consumerEmail;
		NumToCopon = newNumToCopon.getText();
		Benefit = newBenefit.getText().toString();


		setCopon.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				setDataonserver();
			}
		});

		subtract.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) {
				new ConnectionAsyncTask().execute();
			}

		});
	}
	private void subTractOne() 
	{
		Runnable r= new Runnable() 
		{
			public void run() 
			{
				try
				{
					BusinessUsersFunction businessUsersFunction = new BusinessUsersFunction();
					businessUsersFunction.substractCoupon(email, businessName);
				}
				catch (Exception e) 
				{
					Log.e("my app", e.toString());
				}
			}
		} ;
		Thread t = new Thread(r);
		t.start();
	}
	private void setDataonserver() 
	{
		List<NameValuePair> params;
		String url = "http://eliproj1.site88.net/public_html/setCuopon.php";
		params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", "setCoupon"));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("businessName", businessName));
		params.add(new BasicNameValuePair("setCoupon", NumToCopon.toString()));
		params.add(new BasicNameValuePair("benefit", Benefit));
		Toast.makeText(getApplicationContext(), email+" "+businessName+" "+NumToCopon.toString()+" "+Benefit, Toast.LENGTH_LONG).show();
		sendAndResive(url,params);
	}

	private void sendAndResive(final String url, final List<NameValuePair> params) 
	{
		Runnable r= new Runnable() 
		{
			public void run() 
			{
				try
				{
					BusinessUsersFunction businessUsersFunction = new BusinessUsersFunction();
					businessUsersFunction.substractCoupon(email, businessName);
				}
				catch (Exception e) 
				{
					Log.e("my app", e.toString());
				}
			}
		} ;
		Thread t = new Thread(r);
		t.start();
	}


	/* 
	 * A thread to connect to the server to try to login
	 */
	private class ConnectionAsyncTask extends AsyncTask<Void, Void, JSONObject> 
	{

		BusinessUsersFunction businessUsersFunction;

		@Override
		protected void onPreExecute() 
		{
			businessUsersFunction = new BusinessUsersFunction();
			dialog = ProgressDialog.show(CouponChanger.this, "", "Loading...");
			dialog.show();
		}

		@Override
		protected JSONObject doInBackground(Void... urls) 
		{	
			JSONObject json = businessUsersFunction.substractCoupon(email, businessName);
			return json;
		}

		@Override
		protected void onPostExecute(JSONObject json) 
		{
			super.onPostExecute(json);

			try 
			{
				if(json == null)
				{

				}
				else if (json.getString(KEY_SUCCESS) != null) 
				{
					String res = json.getString(KEY_SUCCESS);
					if(Integer.parseInt(res) == 1)
					{
						// user successfully logged in
						// Store user details in SQLite Database
						JSONObject json_consumer = json.getJSONObject("consumer");

						staticParams.userCounterToBenefit = json_consumer.getInt("coupon");



						if(dialog.isShowing()) 
						{
							dialog.dismiss();
						}
					}
					else
					{
						
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
