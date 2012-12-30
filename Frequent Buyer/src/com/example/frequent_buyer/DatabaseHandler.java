package com.example.frequent_buyer;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper 
{

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "freq_buyer";

	// Login table name
	private static final String TABLE_LOGIN = "users";
	private static final String TABLE_BUSINESS = "business";

	// Login Table Columns names
	private static final String KEY_USER_ID = "id";
	private static final String KEY_USER_NAME = "name";
	private static final String KEY_USER_EMAIL = "email";
	private static final String KEY_USER_TYPE = "type";

	// Business Table Columns names
	private static final String KEY_BUSINESS_ID = "id";
	private static final String KEY_BUSINESS_NAME = "name";
	private static final String KEY_BUSINESS_MENU = "menu";
	private static final String KEY_BUSINESS_EVENTS = "events";
	
	private static final String CREATE_LOGIN_TABLE = "create table if not exists " + TABLE_LOGIN + "("
			+ KEY_USER_ID + " INTEGER PRIMARY KEY,"
			+ KEY_USER_NAME + " TEXT,"
			+ KEY_USER_EMAIL + " TEXT UNIQUE,"
			+ KEY_USER_TYPE + " TEXT" + ")";
	
	private static final String CREATE_BUSINESS_TABLE = "create table if not exists " + TABLE_BUSINESS + "("
			+ KEY_BUSINESS_ID + " INTEGER PRIMARY KEY,"
			+ KEY_BUSINESS_NAME + " TEXT,"
			+ KEY_BUSINESS_MENU + " TEXT,"
			+ KEY_BUSINESS_EVENTS + " TEXT" + ")";

	public DatabaseHandler(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		db.execSQL(CREATE_LOGIN_TABLE);
		db.execSQL(CREATE_BUSINESS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUSINESS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user details in database
	 * */
	public void addUser(String name, String email, String type) 
	{
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_USER_NAME, name); // Name
		values.put(KEY_USER_EMAIL, email); // Email
		values.put(KEY_USER_TYPE, type); // Type

		// Inserting Row
		db.insert(TABLE_LOGIN, null, values);
		db.close(); // Closing database connection
	}

	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getUserDetails()
	{
		HashMap<String,String> user = new HashMap<String,String>();
		String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if(cursor.getCount() > 0)
		{
			user.put("name", cursor.getString(1));
			user.put("email", cursor.getString(2));
			user.put("type", cursor.getString(3));
		}
		cursor.close();
		db.close();
		// return user
		return user;
	}

	/**
	 * Getting user login status
	 * return true if rows are there in table
	 * */
	public int getRowCountUsers() 
	{
		String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();

		// return row count
		return rowCount;
	}
	
	/**
	 * Checking if any business are available
	 * return true if rows are there in table
	 * */
	public int getRowCountBusiness() 
	{
		String countQuery = "SELECT  * FROM " + TABLE_BUSINESS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();

		// return row count
		return rowCount;
	}

	public void addBusiness(JSONArray json_business)
	{
		
		for(int i=0; i < json_business.length(); i++)
		{
			try 
			{
				JSONObject business = json_business.getJSONObject(i);
				String name = business.getString("name");
				String menu = business.getString("menu");
				String events = business.getString("events");
				
				SQLiteDatabase db = this.getWritableDatabase();
				
				ContentValues values = new ContentValues();
				values.put(KEY_BUSINESS_NAME, name); // Name
				values.put(KEY_BUSINESS_MENU, menu); // Email
				values.put(KEY_BUSINESS_EVENTS, events); // Type

				// Inserting Row
				db.insert(TABLE_BUSINESS, null, values);
				db.close(); // Closing database connection
				
			} catch (JSONException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * Returns string array with all business names
	 */
	public String[] getAllBusinessNames()
	{
		String selectQuery = "SELECT  * FROM " + TABLE_BUSINESS;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		int size = cursor.getCount();
		String business[] = new String[size];
		int i = 0;
		// Move to first row
		cursor.moveToFirst();
		do
		{
			if(cursor.getCount() > 0)
			{
				business[i] = cursor.getString(cursor.getColumnIndex(KEY_BUSINESS_NAME));
				i++;
			}
			cursor.moveToNext();
		}
		while(!cursor.isAfterLast());
		cursor.close();
		db.close();
		// return user
		return business;
	}
	
	public HashMap<String, String> getBusinessByName(String name)
	{
		HashMap<String, String> business = new HashMap<String, String>();
		String selectQuery = "SELECT  * FROM WHERE name = " + name + TABLE_BUSINESS;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		cursor.moveToFirst();
		if(cursor.getCount() > 0)
		{
			business.put("name", cursor.getString(1));
			business.put("menu", cursor.getString(2));
			business.put("events", cursor.getString(3));
		}
		cursor.close();
		db.close();
		// return user
		return business;
	}

	/**
	 * Re crate database
	 * Delete login tables and create it again
	 * */
	public void resetLoginTable()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_LOGIN, null, null);
		db.close();
	}
	
	/**
	 * Re crate database
	 * Delete all tables and create it again
	 * */
	public void resetBusinessTable()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_BUSINESS, null, null);
		db.close();
	}

}
