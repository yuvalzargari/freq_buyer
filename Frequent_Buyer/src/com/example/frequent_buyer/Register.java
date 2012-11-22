package com.example.frequent_buyer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Register extends Activity
{
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
 
        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);

        loginScreen.setOnClickListener(new View.OnClickListener() 
        {
 
            public void onClick(View arg0) 
            {
                finish();
            }
        });
    }
}
