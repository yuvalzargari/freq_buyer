package com.example.frequent_buyer;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class BusinessUsersFunction 
{
	
	private JSONParser jsonParser;
	
	private static String getCouponURL = "http://eliproj1.site88.net/getcouponnum.php";
	
	private static String getCoupon_tag = "getCoupon";
	
	public BusinessUsersFunction() 
	{
		jsonParser = new JSONParser();
	}
	
	/*
	 * get coupon number from the server
	 */
	public JSONObject getCoupon(String email, String businessName)
	{
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", getCoupon_tag));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("businessName", businessName));
		JSONObject json = jsonParser.getJSONFromUrl(getCouponURL, params);
		return json;
	}

}
