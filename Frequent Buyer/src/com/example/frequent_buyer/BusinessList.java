package com.example.frequent_buyer;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
 
public class BusinessList extends ListActivity {
 
	static final String[] FRUITS = new String[] { "Apple", "Avocado", "Banana",
			"Blueberry", "Coconut", "Durian", "Guava", "Kiwifruit",
			"Jackfruit", "Mango", "Olive", "Pear", "Sugar-apple" };
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 
		// no more this
		// setContentView(R.layout.list_fruit);
		String business[] = null;
		BusinessFunction businessFunction = new BusinessFunction();
		if(businessFunction.businessAvailable(getApplicationContext()) == true)
		{
			DatabaseHandler db = new DatabaseHandler(getApplicationContext());
			business = db.getAllBusinessNames();
		}
		
		
		setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_business_list,business));
 
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    // When clicked, show a toast with the TextView text
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});
 
	}
 
}
