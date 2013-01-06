package com.example.frequent_buyer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
 

public class TabMenu extends Activity 
{
	
    
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        
 
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
    }
}

