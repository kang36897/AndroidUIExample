package com.packtpub.deliverydroid;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ChoiceNoneListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choice_none_list);
		/*
		 * getListView().setAdapter( new ArrayAdapter<String>(this,
		 * R.layout.choice_none_list_item,
		 * getResources().getStringArray(R.array.choice_none)));
		 */
		getListView().setAdapter(
				new ArrayAdapter<String>(this, R.layout.list_item,
						R.id.choice_none_list_item, getResources()
								.getStringArray(R.array.choice_none)));
	}
}
