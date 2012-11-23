package com.example.frequent_buyer;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
					sleep(2000);
					Intent toOpen = new Intent(LoadingActivity.this, Login.class); 
					startActivity(toOpen);
					finish();
				}
				catch(InterruptedException e)
				{

				}
			}

		};
		timer.start();
	}


}
