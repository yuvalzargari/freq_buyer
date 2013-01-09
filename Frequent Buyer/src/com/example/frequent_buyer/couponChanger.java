package com.example.frequent_buyer;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class couponChanger extends Activity
{

	EditText newNumToCopon,newBenefit;
	Button setCopon;
	Editable NumToCopon;
	String Benefit, email, businessname;
	List<NameValuePair> params;
	private JSONParser jsonParser;
	private String url = "http://eliproj1.site88.net/setCuopon.php";

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coponchanger);
		jsonParser = new JSONParser();
		newBenefit = (EditText)findViewById(R.id.setbenefitcoponchangerID);
		newNumToCopon = (EditText)findViewById(R.id.setnumcoponchangerID);
		setCopon = (Button)findViewById(R.id.setcoponbuttunID);

		setCopon.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v) 
			{
				NumToCopon = newNumToCopon.getText();
				Benefit = newBenefit.getText().toString();
				email = staticParams.consumerEmail;
				businessname = staticParams.businessName;
				setDataonserver();
			}
		});
	}
	private void setDataonserver() 
	{
		params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", "setCoupon"));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("businessName", businessname));
		params.add(new BasicNameValuePair("setCoupon", NumToCopon.toString()));
		params.add(new BasicNameValuePair("benefit", Benefit));
		
		Toast.makeText(getApplicationContext(), email+" "+businessname+" "+NumToCopon.toString()+" "+Benefit, Toast.LENGTH_LONG).show();
		
		sendAndResive();
	}

	private void sendAndResive()
	{
		Runnable runnable = new Runnable() 
		{
			public void run() {
				try {
					//jsonParser.getJSONFromUrl(url, params);
					HttpClient httpClient = new DefaultHttpClient();
					HttpConnectionParams.setConnectionTimeout(httpClient.getParams(), 5000);
					HttpPost httpPost = new HttpPost(url);
					httpPost.setEntity(new UrlEncodedFormEntity(params));

					HttpResponse httpResponse = httpClient.execute(httpPost);
					//HttpEntity httpEntity = httpResponse.getEntity();
				} catch (Exception e) {}
			}
		};
		Thread mythread = new Thread(runnable);
		mythread.start();
	}


}
