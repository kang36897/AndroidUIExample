package com.packtpub.deliverydroid;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class TheBurgerPlaceActivity extends ListActivity implements
		OnItemClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new BurgerAdapter(new Burger("Plain old Burger"),
				new Burger("Cheese Burger"), new Burger("Chicken Burger"),
				new Burger("Breakfast Burger"), new Burger("Hawaiian Burger"),
				new Burger("Fish Burger"), new Burger("Vegatarian Burger"),
				new Burger("Lamb Burger"), new Burger("Rare Tuna Steak Burger")));
		getListView().setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		/*
		 * method one BurgerAdapter burgerAdapter = (BurgerAdapter)
		 * parent.getAdapter(); Burger burger = (Burger)
		 * burgerAdapter.getItem(position); burger.count++;
		 * burgerAdapter.notifyDataSetInvalidated();
		 */

		BurgerAdapter burgerAdapter = (BurgerAdapter) parent.getAdapter();
		Burger burger = (Burger) burgerAdapter.getItem(position);
		burger.count++;
		burgerAdapter.notifyDataSetChanged();

	}

}
