package com.example.frequent_buyer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoadingActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		Thread timer = new Thread()
		{
			public void run()
			{
				try
				{
					sleep(1000);
					startActivity(new Intent(LoadingActivity.this, Register.class));
				}
				catch(Exception e){}
			}

		};
		timer.start();
	}
	
}
