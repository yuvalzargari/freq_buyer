package com.example.frequent_buyer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
 

public class TabCoupon extends Activity 
{
	TextView number_lest_to_benefit;
	TextView text_of_benefit;
	String email = staticParams.userEmail;
	String business = staticParams.businessName;
	String benefit_name;
	int num_lest_to_benefit;
	
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_coupon);
        number_lest_to_benefit = (TextView)findViewById(R.id.number_to_coupon_id);
        text_of_benefit = (TextView)findViewById(R.id.benefit_text_id);
       
        // get from server
        get_number_lest_to_benefit(email, business);
       
        staticParams.userCounterToBenefit = num_lest_to_benefit;
        String changeScreen = Integer.toString( num_lest_to_benefit);
       
        //seting screen
        number_lest_to_benefit.setText(changeScreen);
        text_of_benefit.setText(benefit_name);
        //setContentView(R.layout.tab_coupon);
    }

    // get from the server 
	private void get_number_lest_to_benefit(String user_email , String business_selected)
	{
		benefit_name = "hamburger";
		num_lest_to_benefit = 2;
	}
 
}

