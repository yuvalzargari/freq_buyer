package com.example.frequent_buyer;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;


@SuppressWarnings("deprecation")
public class BusinessMenu extends TabActivity 
{

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_menu);
 
        TabHost tabHost = getTabHost();
 
        // Tab for Menu
        TabSpec menuspec = tabHost.newTabSpec("Menu");
        // setting Title and Icon for the Tab
        menuspec.setIndicator("     Menu     ", null);
        Intent menuIntent = new Intent(this, TabMenu.class);
        menuspec.setContent(menuIntent);
 
        // Tab for Events
        TabSpec eventsspec = tabHost.newTabSpec("Events");
        eventsspec.setIndicator("     Events     ", null);
        Intent eventsIntent = new Intent(this, TabEvents.class);
        eventsspec.setContent(eventsIntent);
 
        // Tab for Coupon
        TabSpec couponspec = tabHost.newTabSpec("Coupon");
        couponspec.setIndicator("     Coupon     ", null);
        Intent couponIntent = new Intent(this, TabCoupon.class);
        couponspec.setContent(couponIntent);
 
        // Adding all TabSpec to TabHost
        tabHost.addTab(menuspec); // Adding menu tab
        tabHost.addTab(eventsspec); // Adding events tab
        tabHost.addTab(couponspec); // Adding coupon tab
        
    }


}
