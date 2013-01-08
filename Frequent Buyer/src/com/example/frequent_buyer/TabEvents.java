package com.example.frequent_buyer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
 

public class TabEvents extends Activity 
{
	
	// Dashboard Logout button
	Button btn_logout = (Button) findViewById(R.id.btn_logout);
 
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_events);
        
        
        // Loader image - will be shown before loading image
        int loader = R.drawable.loader;
 
        // Imageview to show
        ImageView image = (ImageView) findViewById(R.id.image);
 
        // Image url
        String image_url = staticParams.businessEvents;
 
        // ImageLoader class instance
        ImageLoader imgLoader = new ImageLoader(getApplicationContext());
 
        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView
        imgLoader.DisplayImage(image_url, loader, image);
        
		// Listening to Logout button click
		btn_logout.setOnClickListener(new View.OnClickListener() 
		{

			public void onClick(View view) 
			{
				// Logout and launch the login screen
				UserFunctions userFunctions = new UserFunctions();
				userFunctions.logoutUser(getApplicationContext());
				Intent activity = new Intent(getApplicationContext(), Login.class);
				startActivity(activity);
				finish();
			}
		});
    }
 
}

