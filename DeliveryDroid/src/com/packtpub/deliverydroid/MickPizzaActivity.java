package com.packtpub.deliverydroid;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

public class MickPizzaActivity extends ExpandableListActivity  {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pizza_list);
		ExpandableListView listView = getExpandableListView();
		listView.setAdapter(new PizzaToppingAdapter(new ToppingCatagory(
				"Fruit", new PizzaTopping("Ananas"), new PizzaTopping("Apple"),
				new PizzaTopping("Banana")), new ToppingCatagory("Meat",
				new PizzaTopping("Ham"), new PizzaTopping("Bacon"),
				new PizzaTopping("Beef Mince"), new PizzaTopping("Ribs"),
				new PizzaTopping("Lamb"), new PizzaTopping("Pepperoni"),
				new PizzaTopping("Chorize"))));
		listView.setOnChildClickListener(this);

	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		// TODO Auto-generated method stub
		Log.v("kang","kang-onChildClick()");
		PizzaToppingAdapter adapter=(PizzaToppingAdapter) parent.getExpandableListAdapter();
		PizzaTopping pizzaTopping=(PizzaTopping) adapter.getChild(groupPosition, childPosition);
		pizzaTopping.flag=(pizzaTopping.flag+1)%3;
		adapter.notifyDataSetChanged();
		return true;
	}
	
	
	
}

class PizzaTopping {
	final static int OFF = 0;
	final static int ON = 1;
	final static int EXTRA = 2;

	String name;
	int flag;

	public PizzaTopping(String name) {
		this.name = name;
		flag = PizzaTopping.OFF;
	}
}

class ToppingCatagory {
	final String name;
	PizzaTopping[] catagories;

	public ToppingCatagory(String name, PizzaTopping... catagories) {
		this.name = name;
		this.catagories = catagories;
	}
}