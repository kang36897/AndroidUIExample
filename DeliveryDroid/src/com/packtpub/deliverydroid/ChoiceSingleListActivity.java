package com.packtpub.deliverydroid;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ChoiceSingleListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choice_single_list);
		getListView().setAdapter(
				new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_single_choice,
						getResources().getStringArray(R.array.choice_single)));
	}
}
