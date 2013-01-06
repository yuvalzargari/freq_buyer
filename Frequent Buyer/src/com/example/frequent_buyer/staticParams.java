package com.example.frequent_buyer;

import java.util.HashMap;

import android.content.Context;

public class staticParams 
{
	
	public static String userName = null;
	public static String userEmail = null;
	public static String userType = null;
	
	public static String businessName = null;
	public static String businessLogo = null;
	public static String businessMenu = null;
	public static String businessEvents = null;
	
	public static int userCounterToBenefit;
	
	public static void logoutUser()
	{
		userName = null;
		userEmail = null;
		userType = null;
	}
	
	public static void saveUserDetail(Context context)
	{
		DatabaseHandler db = new DatabaseHandler(context);
		HashMap<String, String> userDetails = db.getUserDetails();
		userName = userDetails.get("name");
		userEmail = userDetails.get("email");
		userType = userDetails.get("type");
	}
	
	public static void  saveBusinessDetail(Context context, String name)
	{
		DatabaseHandler db = new DatabaseHandler(context);
		HashMap<String, String> businessDetails = db.getBusinessByName(name);
		businessName = businessDetails.get("name");
		businessLogo = businessDetails.get("logo");
		businessMenu = businessDetails.get("menu");
		businessEvents = businessDetails.get("events");
	}
	
}
