package com.packtpub.deliverydroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BurgerAdapter extends BaseAdapter {
	private Burger[] burgers;

	public BurgerAdapter(Burger... burgers) {
		this.burgers = burgers;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return burgers.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return burgers[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewGroup item = getViewGroup(convertView, parent);
		TextView counterTextView = (TextView) item.findViewById(R.id.counter);
		TextView textView = (TextView) item.findViewById(R.id.text);

		Burger burger = burgers[position];
		counterTextView.setVisibility(burger.count == 0 ? View.INVISIBLE
				: View.VISIBLE);
		counterTextView.setText(Integer.toString(burger.count));
		textView.setText(burger.name);
		return item;
	}

	private ViewGroup getViewGroup(View reuse, ViewGroup parent) {
		if (reuse instanceof ViewGroup) {
			return (ViewGroup) reuse;
		}

		Context context = parent.getContext();
		LayoutInflater layoutInflater = LayoutInflater.from(context);
		View item = layoutInflater.inflate(R.layout.burger_item, null);
		return (ViewGroup) item;
	}

}
