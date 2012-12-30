package com.example.frequent_buyer;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

public class BusinessFunction 
{

	
	private JSONParser jsonParser;
	
	private static String business_tag = "business";
	private static String getBusinessURL = "http://eliproj1.site88.net/getBusiness.php";
	
	
	// constructor
	public BusinessFunction()
	{
		jsonParser = new JSONParser();
	}
	
	public JSONObject getBusinessList()
	{
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", business_tag));
		JSONObject json = jsonParser.getJSONFromUrl(getBusinessURL, params);
		return json;
	}
	
	public void removeAllBusiness(Context context)
	{
		DatabaseHandler db = new DatabaseHandler(context);
		db.resetBusinessTable();
	}
	
	public boolean businessAvailable(Context context)
	{
		DatabaseHandler db = new DatabaseHandler(context);
		int count = db.getRowCountBusiness();
		if(count > 0){
			// user logged in
			return true;
		}
		return false;
	}
	
	
	
}
