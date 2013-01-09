package com.example.frequent_buyer;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class BusinessUsersFunction 
{
	
	private JSONParser jsonParser;
	
	private static String getCouponURL = "http://eliproj1.site88.net/getCoupon.php";
	private static String getConsumerClubListURL = "http://eliproj1.site88.net/getConsumerClubList.php";
	
	private static String getCoupon_tag = "getCoupon";
	private static String getConsumerClubList_tag = "getConsumerClubList";
	
	
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
	
	/*
	 * get consumer club of the owner business
	 */
	public JSONObject getConsumerClubList(String email)
	{
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", getConsumerClubList_tag));
		params.add(new BasicNameValuePair("email", email));
		JSONObject json = jsonParser.getJSONFromUrl(getConsumerClubListURL, params);
		return json;
	}

}
