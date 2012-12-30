package com.example.frequent_buyer;

import java.util.HashMap;

import android.content.Context;

public class staticParams 
{
	
	public static String userName;
	public static String userEmail;
	public static String userType;	
	public static String businessName;
	public static String businessMenu;
	public static String businessEvents;
	
	public static int userCounterToBenefit;
	
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
		businessMenu = businessDetails.get("menu");
		businessEvents = businessDetails.get("events");
	}
	
}
