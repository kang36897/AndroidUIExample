package com.packtpub.deliverydroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebSettings.TextSize;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SelectRestaurantActivity extends Activity implements
		OnItemClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant_list);
		
		
		
		ListView listView = (ListView) findViewById(R.id.restaurant);
		TextView header=(TextView) getLayoutInflater().inflate(R.layout.menu_item, listView,false);
		header.setText("Where should we order from ?");
		header.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
		listView.addHeaderView(header,null,true);
		listView.setAdapter(new ArrayAdapter<String>(this, R.layout.menu_item,
				getResources().getStringArray(R.array.restaurants)));
	    listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Log.v("kang","kang-onItemClick()-position="+position);
		switch (position) {
		case 1:
			startActivity(new Intent(this, TheBurgerPlaceActivity.class));
			break;
		case 2:
			startActivity(new Intent(this,MickPizzaActivity.class));
			break;
		default:
			break;
		}
	}

}

class Burger {
	final String name;
	int count = 0;

	public Burger(String name) {
		this.name = name;
	}
}