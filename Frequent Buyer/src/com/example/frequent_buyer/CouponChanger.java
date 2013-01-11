package com.example.frequent_buyer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CouponChanger extends Activity
{

	EditText newNumToCopon,newBenefit;
	Button setCopon,subtract ;
	Editable NumToCopon;
	String Benefit, email, businessname;

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

		subtract.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View v) {
				businessname = staticParams.businessName;
				email = staticParams.consumerEmail;
				subTractOne();
			}

		});
	}
	private void subTractOne() {
		List<NameValuePair> params;
		String url = "http://eliproj1.site88.net/public_html/subtractCuopon.php";
		params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", "subtractCuopon"));
		params.add(new BasicNameValuePair("email", email));	
		sendAndResive(url,params , getApplicationContext());
	}
	private void setDataonserver() 
	{
		List<NameValuePair> params;
		String url = "http://eliproj1.site88.net/public_html/setCuopon.php";
		params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", "setCoupon"));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("businessName", businessname));
		params.add(new BasicNameValuePair("setCoupon", NumToCopon.toString()));
		params.add(new BasicNameValuePair("benefit", Benefit));
		
		sendAndResive(url,params,getApplicationContext());
	}

	private void sendAndResive(final String url, final List<NameValuePair> params, final Context context) 
	{
		Toast.makeText(context, email, Toast.LENGTH_LONG).show();
		Thread t = new Thread()
		{
			public void run() 
			{
				try
				{
					Log.e("my app 1",url );
					HttpPost httpPost = new HttpPost(url);
					Log.e("my app 2",email);
					HttpClient httpClient = new DefaultHttpClient();
					httpPost.setEntity(new UrlEncodedFormEntity(params));
					Log.e("my app 3",params.toString());
					HttpResponse httpResponse = httpClient.execute(httpPost);
					Log.e("my app 4",httpResponse.toString());
					StatusLine statusLine = httpResponse.getStatusLine();
					if(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK)
					{
						InputStream inps = httpResponse.getEntity().getContent();
						InputStreamReader inp = new InputStreamReader(inps, Charset.forName("UTF-8"));
						BufferedReader rd = new BufferedReader(inp);
						StringBuilder stringBuilder = new StringBuilder();
						String bufferedStrChunk = null;
						while ((bufferedStrChunk = rd.readLine()) != null) 
						{
							stringBuilder.append(bufferedStrChunk);
						}
						result = stringBuilder.toString();
					}
					Log.e("my app 5",result.toString());
					
					
//					JSONParser j= new JSONParser();
//					JSONObject k= j.getJSONFromUrl(url, params);
//					Log.e("my app", k.toString(1));
				}
				catch (Exception e) 
				{
					Log.e("my app", e.toString());
				}
			}
		} ;
		t.start();
		try {
			t.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
