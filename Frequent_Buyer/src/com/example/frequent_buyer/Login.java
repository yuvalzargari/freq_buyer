package com.example.frequent_buyer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login extends Activity 
{
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        
        registerScreen.setOnClickListener(new View.OnClickListener() 
        {
 
            public void onClick(View v) 
            {
                // Switching to Register screen
                Intent ToOpen = new Intent(getApplicationContext(), Register.class);
                startActivity(ToOpen);
            }
        });
    }

}
